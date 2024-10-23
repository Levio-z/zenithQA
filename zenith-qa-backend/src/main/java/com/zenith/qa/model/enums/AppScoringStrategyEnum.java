package com.zenith.qa.model.enums;

import org.apache.commons.lang3.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 评分策略业务类型枚举
 *
 * @author zenith
 */
public enum AppScoringStrategyEnum {
    CUSTOM(0, "自定义"),
    AI(1, "AI");

    private final int code;

    private final String description;

    AppScoringStrategyEnum(int code, String description) {

        this.code = code;
        this.description = description;
    }

    public static List<Integer> getCodes() {

        return Arrays.stream(values()).map(AppScoringStrategyEnum::getCode).collect(Collectors.toList());
    }

    public static AppScoringStrategyEnum getEnumByCode(Integer code) {

        if (ObjectUtils.isEmpty(code)) {
            return null;
        }
        for (AppScoringStrategyEnum strategy : AppScoringStrategyEnum.values()) {
            if (strategy.code == code) {
                return strategy;
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
