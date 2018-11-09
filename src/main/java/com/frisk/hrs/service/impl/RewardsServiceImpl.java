package com.frisk.hrs.service.impl;

import com.frisk.hrs.mapper.RewardsMapper;
import com.frisk.hrs.pojo.Rewards;
import com.frisk.hrs.pojo.RewardsFind;
import com.frisk.hrs.service.RewardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author frisktale
 * @date 2018/10/13
 */
@Service
public class RewardsServiceImpl implements RewardsService {

    @Autowired
    @Qualifier("rewardsMapper")
    private RewardsMapper rewardsMapper;

    @Override
    public Integer addReward(Rewards rewards) throws Exception {
        Integer line = rewardsMapper.insertRewards(rewards);
        return line;
    }

    @Override
    public Rewards findRewardById(Integer id) throws Exception {
        Rewards reward = rewardsMapper.findRewardsByIdBase(id);
        return reward;
    }

    @Override
    public Integer updateReward(Rewards rewards) throws Exception {
        Integer row = rewardsMapper.updateRewards(rewards);
        return row;
    }

    @Override
    public List<Rewards> findRewardsByEmpIdAndMonth(Integer eId, Date date) throws Exception {
        RewardsFind rewardsFind = new RewardsFind();
        rewardsFind.andEmpIdIs(eId);
        rewardsFind.andStartTimeInYearMonthIs(date);
        List<Rewards> rewardsList = rewardsMapper.findRewardsByCustom(rewardsFind);
        return rewardsList;
    }

    @Override
    public List<Rewards> findRewardsByEmpIdAndDay(Integer eId, Date date) throws Exception {

        RewardsFind rewardsFind = new RewardsFind();
        rewardsFind.andEmpIdIs(eId);
        rewardsFind.andStartTimeInYearMonthDayIs(date);
        List<Rewards> rewardsList = rewardsMapper.findRewardsByCustom(rewardsFind);
        return rewardsList;
    }

    @Override
    public Double findRewardsMoneyByEmpIdAndMonth(Integer eId, Date date) throws Exception {
        RewardsFind rewardsFind = new RewardsFind();
        rewardsFind.andEmpIdIs(eId);
        rewardsFind.andStartTimeInYearMonthIs(date);
        Double totalMoney = rewardsMapper.getTotalMoneyByCustom(rewardsFind);
        return totalMoney;
    }

    @Override
    public Integer deleteRewardsByIds(List<Integer> ids) throws Exception {
        Integer row = rewardsMapper.deleteList(ids);
        return row;
    }
}
