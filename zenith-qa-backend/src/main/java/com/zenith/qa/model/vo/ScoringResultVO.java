package com.zenith.qa.model.vo;

import cn.hutool.json.JSONUtil;
import com.zenith.qa.model.entity.ScoringResult;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 评分结果表视图
 *
 * @author zenith
 */
@Data
public class ScoringResultVO implements Serializable {

    /**
     * id
     */
    private Long id;


    /**
     * 结果名称，如物流师
     */
    private String resultName;

    /**
     * 结果描述
     */
    private String resultDesc;

    /**
     * 结果图片
     */
    private String resultPicture;

    /**
     * 结果属性集合 JSON，如 [I,S,T,J]
     */
    private List<String> resultProp;

    /**
     * 结果得分范围，如 80，表示 80及以上的分数命中此结果
     */
    private Integer resultScoreRange;

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
     * 创建用户信息
     */
    private UserVO user;

    /**
     * 封装类转对象
     *
     * @param ScoringResultVO
     * @return
     */
    public static ScoringResult voToObj(ScoringResultVO ScoringResultVO) {

        if (ScoringResultVO == null) {
            return null;
        }
        ScoringResult ScoringResult = new ScoringResult();
        BeanUtils.copyProperties(ScoringResultVO, ScoringResult);
        ScoringResult.setResultProp(JSONUtil.toJsonStr(ScoringResultVO.getResultProp()));
        return ScoringResult;
    }

    /**
     * 对象转封装类
     *
     * @param ScoringResult
     * @return
     */
    public static ScoringResultVO objToVo(ScoringResult ScoringResult) {

        if (ScoringResult == null) {
            return null;
        }
        ScoringResultVO ScoringResultVO = new ScoringResultVO();
        BeanUtils.copyProperties(ScoringResult, ScoringResultVO);
        ScoringResultVO.setResultProp(JSONUtil.toList(ScoringResult.getResultProp(), String.class));
        return ScoringResultVO;
    }

}
