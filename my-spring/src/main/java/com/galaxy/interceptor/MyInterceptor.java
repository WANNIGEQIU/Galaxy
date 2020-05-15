package com.galaxy.interceptor;

import com.galaxy.controller.TestController;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 方式1 实现 HandlerInterceptor 接口
 * 方式2 继承 HandlerInterceptorAdapter
 */
public class MyInterceptor implements HandlerInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyInterceptor.class);
    @Autowired
    TestController testController;
    /**
     * 在业务处理器处理请求之前被调用。预处理，可以进行编码、安全控制、权限校验等处理
     * 进行逻辑判断，如果ok就返回true，不行就返回false，返回false就不会处理请求
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOGGER.info("preHandle。。。。。。。");
       return true;
    }

    /**
     * 在业务处理器处理请求执行完成后，生成视图之前执行
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        LOGGER.info("postHandle。。。。。。。");

    }

    /**
     * 在 DispatcherServlet 完全处理完请求后被调用，可用于清理资源等
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LOGGER.info("afterCompletion。。。。。。。");

    }
}
