package com.frisk.hrs.mapper;


import com.frisk.hrs.pojo.Rewards;
import com.frisk.hrs.pojo.RewardsFind;

import java.util.List;

public interface RewardsMapper {
    public Rewards findRewardsByIdBase(Integer id) throws Exception;

    public List<Rewards> findRewardsByCustom(RewardsFind rewardsFind) throws Exception;

    public Double getTotalMoneyByCustom(RewardsFind rewardsFind) throws Exception;

    public Integer insertRewards(Rewards rewards)throws Exception;

    public Integer updateRewards(Rewards rewards)throws Exception;

    public Integer deleteList(List<Integer> ids)throws Exception;

    public List<Rewards> showDelete()throws Exception;

    public Integer recoverRewards(List<Integer> ids)throws Exception;

    public Integer cleanRewards()throws Exception;
}