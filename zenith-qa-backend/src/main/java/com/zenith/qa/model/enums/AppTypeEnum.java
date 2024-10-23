package com.zenith.qa.model.enums;

import org.apache.commons.lang3.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 应用业务类型枚举
 *
 * @author zenith
 */
public enum AppTypeEnum {
    CATEGORY(0, "得分类"),
    ASSESSMENT(1, "测评类");

    private final int code;

    private final String description;

    AppTypeEnum(int code, String description) {

        this.code = code;
        this.description = description;
    }

    public static List<Integer> getCodes() {

        return Arrays.stream(values()).map(AppTypeEnum::getCode).collect(Collectors.toList());
    }

    public static AppTypeEnum getEnumByCode(Integer code) {

        if (ObjectUtils.isEmpty(code)) {
            return null;
        }
        for (AppTypeEnum type : AppTypeEnum.values()) {
            if (type.code == code) {
                return type;
            }
        }
        return null;
    }

    public int getCode() {

        return code;
    }

    public String getDescription() {

        return description;
    }
}
