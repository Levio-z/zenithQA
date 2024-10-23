package com.zenith.qa.scoring;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zenith.qa.model.dto.question.QuestionContentDTO;
import com.zenith.qa.model.entity.App;
import com.zenith.qa.model.entity.Question;
import com.zenith.qa.model.entity.ScoringResult;
import com.zenith.qa.model.entity.UserAnswer;
import com.zenith.qa.model.vo.QuestionVO;
import com.zenith.qa.service.QuestionService;
import com.zenith.qa.service.ScoringResultService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * Customize the score policy mode
 *
 * @author zenith
 * @date 2024/10/23
 */
@ScoringStrategyConfig(appType = 0, scoringStrategy = 0)
public class CustomScoreScoringStrategyImpl implements ScoringStrategy {

    @Resource
    private QuestionService questionService;

    @Resource
    private ScoringResultService scoringResultService;

    public static Integer getMaxScore(List<String> answerList, List<QuestionContentDTO> questionContent, List<ScoringResult> scoringResultList) {
        // 初始化一个Map，用于存储每个选项的计数
        Integer totalScore = 0;

        // 遍历题目列表
        for (QuestionContentDTO question : questionContent) {
            // 遍历答案列表
            for (int i = 0; i < answerList.size(); i++) {
                // 遍历题目中的选项
                for (QuestionContentDTO.Option option : question.getOptions()) {
                    // 如果答案和选项的key匹配
                    if (option.getKey().equals(answerList.get(i))) {
                        // 获取选项的result属性
                        totalScore += Optional.ofNullable(option.getScore()).orElse(0);
                    }
                }
            }
        }

        // 返回最高分数和最高分数对应的评分结果
        return totalScore;
    }

    @Override
    public UserAnswer doScore(List<String> choices, App app) throws Exception {

        // 1）根据 id 查询到题目和题目结果信息
        Long appId = app.getId();
        Integer appType = app.getAppType();
        Question question = questionService.getOne(Wrappers.lambdaQuery(Question.class).eq(Question::getAppId, app.getId()));
        List<ScoringResult> scoringResultList = scoringResultService.list(Wrappers.lambdaQuery(ScoringResult.class).eq(ScoringResult::getAppId, appId).orderByDesc(ScoringResult::getResultScoreRange));

        // 2）根据用户选择的答案和题目结果信息，计算出用户得分
        QuestionVO questionVO = QuestionVO.objToVo(question);
        questionVO.getQuestionContent();
        Integer score = getMaxScore(choices, questionVO.getQuestionContent(), scoringResultList);

        // 找到第一个分数大于得分结果的结果对象
        ScoringResult result = scoringResultList.stream().filter(item -> item.getResultScoreRange() <= score).findFirst().orElse(scoringResultList.get(0));

        // 3）构造结果对象，返回答案对象
        //将结果对象中的数据填充到UserAnswer中
        UserAnswer userAnswer = new UserAnswer();
        userAnswer.setAppId(appId);
        userAnswer.setAppType(appType);
        userAnswer.setChoices(JSONUtil.toJsonStr(choices));
        userAnswer.setResultId(result.getId());
        userAnswer.setResultName(result.getResultName());
        userAnswer.setResultDesc(result.getResultDesc());
        userAnswer.setResultPicture(result.getResultPicture());
        userAnswer.setResultScore(score);
        return userAnswer;
    }

}
