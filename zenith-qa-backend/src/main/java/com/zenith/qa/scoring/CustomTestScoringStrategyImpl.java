package com.zenith.qa.scoring;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zenith.qa.model.dto.question.QuestionContentDTO;
import com.zenith.qa.model.entity.App;
import com.zenith.qa.model.entity.Question;
import com.zenith.qa.model.entity.ScoringResult;
import com.zenith.qa.model.entity.UserAnswer;
import com.zenith.qa.model.vo.QuestionVO;
import com.zenith.qa.model.vo.ScoringResultVO;
import com.zenith.qa.service.QuestionService;
import com.zenith.qa.service.ScoringResultService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Customize the test policy mode
 *
 * @author zenith
 * @date 2024/10/23
 */
@ScoringStrategyConfig(appType = 1, scoringStrategy = 0)
public class CustomTestScoringStrategyImpl implements ScoringStrategy {

    @Resource
    private QuestionService questionService;

    @Resource
    private ScoringResultService scoringResultService;

    public static ScoringResult getMaxScore(List<String> answerList, List<QuestionContentDTO> questionContent, List<ScoringResult> scoringResultList) {
        // 初始化一个Map，用于存储每个选项的计数
        Map<String, Integer> optionCount = new HashMap<>();

        // 遍历题目列表
        for (QuestionContentDTO question : questionContent) {
            // 遍历答案列表
            for (int i = 0; i < answerList.size(); i++) {
                // 遍历题目中的选项
                for (QuestionContentDTO.Option option : question.getOptions()) {
                    // 如果答案和选项的key匹配
                    if (option.getKey().equals(answerList.get(i))) {
                        // 获取选项的result属性
                        String result = option.getResult();

                        // 如果result属性不在optionCount中，初始化为0
                        optionCount.putIfAbsent(result, 0);

                        // 在optionCount中增加计数
                        optionCount.put(result, optionCount.get(result) + 1);
                    }
                }
            }
        }

        // 初始化最高分数和最高分数对应的评分结果
        int maxScore = 0;
        ScoringResult maxScoreResult = scoringResultList.get(0);

        // 遍历评分结果列表
        for (ScoringResult result : scoringResultList) {
            ScoringResultVO scoringResultVO = ScoringResultVO.objToVo(result);

            // 计算当前评分结果的分数
            int score = scoringResultVO.getResultProp().stream().mapToInt(prop -> optionCount.getOrDefault(prop, 0)).sum();

            // 如果分数高于当前最高分数，更新最高分数和最高分数对应的评分结果
            if (score > maxScore) {
                maxScore = score;
                maxScoreResult = result;
            }
        }

        // 返回最高分数和最高分数对应的评分结果
        return maxScoreResult;


    }

    @Override
    public UserAnswer doScore(List<String> choices, App app) throws Exception {
        // 1）根据 id 查询到题目和题目结果信息
        Long appId = app.getId();
        Integer appType = app.getAppType();
        Question question = questionService.getOne(Wrappers.lambdaQuery(Question.class).eq(Question::getAppId, app.getId()));
        List<ScoringResult> scoringResultList = scoringResultService.list(Wrappers.lambdaQuery(ScoringResult.class).eq(ScoringResult::getAppId, appId));

        // 2）根据用户选择的答案和题目结果信息，计算出用户得分
        QuestionVO questionVO = QuestionVO.objToVo(question);
        questionVO.getQuestionContent();

        // 3）构造结果对象，返回答案对象
        ScoringResult result = getMaxScore(choices, questionVO.getQuestionContent(), scoringResultList);

        //将结果对象中的数据填充到UserAnswer中
        UserAnswer userAnswer = new UserAnswer();
        userAnswer.setAppId(appId);
        userAnswer.setAppType(appType);
        userAnswer.setChoices(JSONUtil.toJsonStr(choices));
        userAnswer.setResultId(result.getId());
        userAnswer.setResultName(result.getResultName());
        userAnswer.setResultDesc(result.getResultDesc());
        userAnswer.setResultPicture(result.getResultPicture());
        return userAnswer;
    }

}