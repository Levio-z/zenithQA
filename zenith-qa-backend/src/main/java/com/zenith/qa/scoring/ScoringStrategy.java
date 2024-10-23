package com.zenith.qa.scoring;

import com.zenith.qa.model.entity.App;
import com.zenith.qa.model.entity.UserAnswer;

import java.util.List;

/**
 * Scoring Strategy Mode
 *
 * @author zenith
 * @date 2024/10/23
 */
public interface ScoringStrategy {

    /**
     * perform scoring
     *
     * @param choices
     * @param app
     * @return
     * @throws Exception
     */
    UserAnswer doScore(List<String> choices, App app) throws Exception;
}