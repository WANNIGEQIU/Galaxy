package com.galaxy.mvc.servlet;

import com.galaxy.mvc.annotations.*;
import com.galaxy.mvc.util.StringUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.URL;
import java.util.*;


public class DispatcherServlet extends HttpServlet {
    // CLASS
    List<String> classList = new ArrayList<>();
    // beans
    Map<String, Object> beans = new HashMap<>();
    // mapping
    Map<String, Object> mapping = new HashMap<>();
    // handle
    Map<String, Object> handle = new HashMap<>();


    @Override
    public void init(ServletConfig config) throws ServletException {
        // 扫描包
        String packageName = readConfigName();
        basePackageScan(packageName);
        // 初始化
        doInstance();
        // DI
        doAutowired();
        // mapping
        doMapping();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String contextPath = req.getContextPath();
        String requestURI = req.getRequestURI();
        String path = requestURI.replaceAll(contextPath, "");
        Method method = (Method) mapping.get(path);
        String pathKey = path.split("/")[1];
        Object instance = handle.get("/" + pathKey);
        Object[] objects = doParam(method, req, resp);


        try {
            Object invoke = method.invoke(instance, objects);
            doWriter(method, invoke, req, resp);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    protected void doWriter(Method method, Object val, HttpServletRequest request, HttpServletResponse response) {
        String contextPath = request.getContextPath();
        String serverName = request.getServerName();
        int localPort = request.getLocalPort();

        if (method.isAnnotationPresent(GoResponseBody.class)) {
            try {
                PrintWriter writer = response.getWriter();
                writer.println(val);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            StringBuilder stringBuilder = new StringBuilder("http://");
            stringBuilder.append(serverName).append(":").append(localPort)
                    .append(contextPath).append("/").append(val).append(".jsp");

            try {
                response.sendRedirect(stringBuilder.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    /**
     * 从配置文件加载 配置文件没有配置就扫描整个包路径
     * 可以java配置扫描路径
     * 必须有个@Configuration 指定的配置类
     *
     * @return
     */
    protected String readConfigName() {
        Properties properties = new Properties();
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("application.properties");
        try {
            properties.load(resourceAsStream);
            String string = (String) properties.get("mvc.path");
            return string;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void basePackageScan(String fileName) {
        String finalFileName = fileName.replaceAll("\\.", "/");
        URL resource = this.getClass().getClassLoader().getResource(finalFileName);
        File file = new File(resource.getFile());

        for (String path : file.list()) {
            File file1 = new File(file + "/" + path);
            if (file1.isDirectory()) {
                basePackageScan(fileName + "." + path);
            } else {
                classList.add(fileName + "." + path.replaceAll(".class", ""));

            }
        }
    }

    protected void doInstance() {

        classList.forEach(className -> {
            try {
                Class<?> aClass = Class.forName(className);
                String beanName = checkType(aClass);
                if (!aClass.isAnnotationPresent(Retention.class)) {
                    Object instance = aClass.getDeclaredConstructor().newInstance();
                    beans.put(beanName, instance);
                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        });
    }

    /**
     * 判断类型
     *
     * @param c
     * @return
     */

    private String checkType(Class c) {
        if (c.isAnnotationPresent(GoController.class)) {
            GoController annotation = (GoController) c.getAnnotation(GoController.class);
            // true 类型 false 名称
            if (StringUtil.isEmpty(annotation.value())) {
                return c.getTypeName();
            } else {
                return annotation.value();
            }
        }
        if (c.isAnnotationPresent(GoService.class)) {
            GoService annotation = (GoService) c.getAnnotation(GoService.class);
            // true 类型 false 名称
            if (StringUtil.isEmpty(annotation.value())) {
                return c.getTypeName();
            } else {
                return annotation.value();
            }
        }

        return null;
    }

    /**
     * 映射
     */
    private void doMapping() {
        beans.forEach((k, v) -> {
            if (v.getClass().isAnnotationPresent(GoRequestMapping.class)) {
                String mappingPath = v.getClass().getAnnotation(GoRequestMapping.class).value();
                Method[] methods = v.getClass().getMethods();
                for (Method method : methods) {
                    if (method.isAnnotationPresent(GoRequestMapping.class)) {
                        String value = method.getAnnotation(GoRequestMapping.class).value();
                        mapping.put(mappingPath + value, method);
                    } else {
                        mapping.put(mappingPath, method);
                    }
                    handle.put(mappingPath, v);
                }
            }

        });
    }

    private void doAutowired() {
        beans.forEach((k, v) -> {
            Field[] declaredFields = v.getClass().getDeclaredFields();
            for (Field declaredField : declaredFields) {
                if (declaredField.isAnnotationPresent(GoAutowired.class)) {
                    // 获取field实例
                    String fieldName = declaredField.getType().getName();
                    Object instance = checkAuto(fieldName, declaredField);
                    if (instance == null) {
                        throw new NullPointerException();
                    }
                    declaredField.setAccessible(true);
                    try {

                        declaredField.set(v, instance);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }

            }

        });
    }

    private Object[] doParam(Method method, HttpServletRequest request, HttpServletResponse response) {
        String req = "javax.servlet.http.HttpServletRequest";
        String res = "javax.servlet.http.HttpServletResponse";
        int reqs = -1;
        int resp = -1;
        int count = 0;
        // 获取参数
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            if (req.equals(parameters[i].getType().getName())) {
                reqs = i;
            } else if (res.equals(parameters[i].getType().getName())) {
                resp = i;
            } else {
                count++;
            }


        }
        Object[] args = new Object[count];
        for (int i = 0; i < count; i++) {
            if (parameters[i].isAnnotationPresent(GoRequestParameter.class)) {
                GoRequestParameter annotation = parameters[i].getAnnotation(GoRequestParameter.class);
                String value = annotation.value();
                // 参数名
                String paramName = parameters[i].getName();
                if (StringUtil.isEmpty(value)) {
                    String parameterVal = request.getParameter(paramName);
                    args[i] = parameterVal;

                } else {
                    String parameter = request.getParameter(value);
                    paramName = parameter;
                    args[i] = parameter;

                }

            } else {

                String parameter = request.getParameter(parameters[i].getName());
                args[i] = parameter;


            }
        }

        if (reqs != -1)
            count++;

        if (resp != -1)
            count++;

        Object[] objects = Arrays.copyOf(args, count);
        if (reqs > resp) {
            objects[count - resp] = request;
            objects[count - reqs] = response;
        } else {
            objects[count - resp] = response;
            objects[count - reqs] = request;
        }


        return objects;
    }

    private Object checkAuto(String classPath, Field field) {
        Object instance = new Object();
        String value = field.getAnnotation(GoAutowired.class).value();
        if (StringUtil.isEmpty(value)) {
            instance = beans.get(classPath);
        } else {
            instance = beans.get(value);
        }
        return instance;
    }

}
