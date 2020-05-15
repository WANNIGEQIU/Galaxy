package com.galaxy.api.enums;

public enum CountryEnum {
    ONE(1, "齐国"),
    TWO(2, "楚国"),
    THREE(3, "燕国"),
    FOUR(4, "赵国"),
    FIVE(5, "魏国"),
    SIX(6, "韩国"),
    ;


    private Integer index;
    private String name;

    public static CountryEnum countryFor(int index) {
        CountryEnum[] enums = CountryEnum.values();
        for (int i = 0; i < enums.length; i++) {
            if (enums[i].getIndex() == index) {
                return enums[i];
            }
        }
        return null;
    }


    CountryEnum(Integer index, String name) {
        this.index = index;
        this.name = name;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
