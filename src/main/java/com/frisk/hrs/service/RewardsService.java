package com.frisk.hrs.service;

import com.frisk.hrs.pojo.Rewards;

import java.util.Date;
import java.util.List;

/**
 * @author frisktale
 * @date 2018/10/13
 */
public interface RewardsService {

    public Integer addReward(Rewards rewards) throws Exception;

    public Rewards findRewardById(Integer id) throws Exception;

    public Integer updateReward(Rewards rewards) throws Exception;

    public List<Rewards> findRewardsByEmpIdAndMonth(Integer eId, Date date) throws Exception;

    public List<Rewards> findRewardsByEmpIdAndDay(Integer eId, Date date) throws Exception;

    public Double findRewardsMoneyByEmpIdAndMonth(Integer eId, Date date) throws Exception;

    public Integer deleteRewardsByIds(List<Integer> ids) throws Exception;
}
