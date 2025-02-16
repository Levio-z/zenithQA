package com.zenith.qa.model.dto.useranswer;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 创建用户答案请求
 *
 * @author zenith
 */
@Data
public class UserAnswerAddRequest implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 应用 id
     */
    private Long appId;

    /**
     * 用户答案（JSON 数组）
     */
    private List<String> choices;

}