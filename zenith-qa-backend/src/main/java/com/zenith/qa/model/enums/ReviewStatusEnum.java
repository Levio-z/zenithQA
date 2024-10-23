package com.zenith.qa.model.enums;

import org.apache.commons.lang3.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 审核状态业务类型枚举
 *
 * @author zenith
 */
public enum ReviewStatusEnum {
    PENDING(0, "待审核"),
    APPROVED(1, "通过"),
    REJECTED(2, "拒绝");

    private final int code;

    private final String description;

    ReviewStatusEnum(int code, String description) {

        this.code = code;
        this.description = description;
    }

    public static List<Integer> getCodes() {

        return Arrays.stream(values()).map(ReviewStatusEnum::getCode).collect(Collectors.toList());
    }

    public static ReviewStatusEnum getEnumByCode(Integer code) {

        if (ObjectUtils.isEmpty(code)) {
            return null;
        }
        for (ReviewStatusEnum status : ReviewStatusEnum.values()) {
            if (status.code == code) {
                return status;
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
