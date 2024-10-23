package com.zenith.qa.model.vo;

import cn.hutool.json.JSONUtil;
import com.zenith.qa.model.dto.question.QuestionContentDTO;
import com.zenith.qa.model.entity.Question;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 题目视图
 *
 * @author zenith
 */
@Data
public class QuestionVO implements Serializable {

    /**
     * id
     */
    private Long id;


    /**
     * 题目内容
     */
    private List<QuestionContentDTO> questionContent;

    /**
     * 应用 id
     */
    private Long appId;

    /**
     * 创建用户 id
     */
    private Long userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 标签列表
     */
    private List<String> tagList;

    /**
     * 创建用户信息
     */
    private UserVO user;

    /**
     * 封装类转对象
     *
     * @param questionVO
     * @return
     */
    public static Question voToObj(QuestionVO questionVO) {

        if (questionVO == null) {
            return null;
        }
        Question question = new Question();
        BeanUtils.copyProperties(questionVO, question);
        List<QuestionContentDTO> questionContent = questionVO.getQuestionContent();
        question.setQuestionContent(JSONUtil.toJsonStr(questionContent));
        return question;
    }

    /**
     * 对象转封装类
     *
     * @param question
     * @return
     */
    public static QuestionVO objToVo(Question question) {

        if (question == null) {
            return null;
        }
        QuestionVO questionVO = new QuestionVO();
        BeanUtils.copyProperties(question, questionVO);
        String questionContent = question.getQuestionContent();
        if(!StringUtils.isBlank(questionContent)){
            questionVO.setQuestionContent(JSONUtil.toList(questionContent, QuestionContentDTO.class));
        }
        return questionVO;
    }

}
