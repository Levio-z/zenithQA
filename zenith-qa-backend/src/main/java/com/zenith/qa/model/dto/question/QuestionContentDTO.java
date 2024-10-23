package com.zenith.qa.model.dto.question;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionContentDTO {

    /**
     * 题目标题
     */
    private String title;

    /**
     * 选项列表
     */
    private List<Option> options;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Option {

        /**
         * 测评类使用的结果
         */
        private String result;

        /**
         * 得分类使用的分数
         */
        private Integer score;

        /**
         * 选项内容
         */
        private String value;

        /**
         * 选项 key
         */
        private String key;

    }

}
