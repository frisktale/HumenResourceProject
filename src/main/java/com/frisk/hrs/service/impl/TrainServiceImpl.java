package com.frisk.hrs.service.impl;

import com.frisk.hrs.mapper.TrainMapper;
import com.frisk.hrs.pojo.TrainCustom;
import com.frisk.hrs.pojo.TrainFind;
import com.frisk.hrs.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author frisktale
 * @date 2018/10/9
 */
@Service
public class TrainServiceImpl implements TrainService {

    @Autowired
    @Qualifier("trainMapper")
    private TrainMapper trainMapper;

    @Override
    public TrainCustom getTrainById(Integer id) throws Exception {
        TrainCustom trainCustom = trainMapper.findTrainById(id);
        return trainCustom;
    }

    @Override
    public List<TrainCustom> getTrainsByPositionId(Integer id) throws Exception {
        TrainFind trainFind = new TrainFind();
        trainFind.andPositionIdIdIs(id);
        List<TrainCustom> trains = trainMapper.findTrainByCustomer(trainFind);
        return trains;
    }

    @Override
    public List<TrainCustom> getTrainsByDepId(Integer id) throws Exception {
        TrainFind trainFind = new TrainFind();
        trainFind.andDepartmentIdIs(id);
        List<TrainCustom> trains = trainMapper.findTrainByCustomer(trainFind);
        return trains;
    }

    @Override
    public Integer addTrain(TrainCustom trainCustom) throws Exception {
        Integer line = trainMapper.insertTrain(trainCustom);
        return line;
    }

    @Override
    public Integer updateTrain(TrainCustom trainCustom) throws Exception {
        Integer row = trainMapper.updateTrain(trainCustom);
        return row;
    }

    @Override
    public Integer deleteTrain(List<Integer> ids) throws Exception {
        Integer row = trainMapper.deleteList(ids);
        return row;
    }

    @Override
    public List<TrainCustom> getUnHappenedTrainByEmpId(Integer id) throws Exception {
        List<TrainCustom> notHappenedTrainByEmpId = trainMapper.findNotHappenedTrainByEmpId(id);
        return notHappenedTrainByEmpId;
    }

    @Override
    public List<TrainCustom> getUnHappenedTrainByDepIdAndPosId(Integer depId, Integer posId) throws Exception {
        TrainFind trainFind = new TrainFind();
        trainFind.andDepartmentIdIs(depId);
        trainFind.andPositionIdIdIs(posId);
        List<TrainCustom> notHappenedTrainByCustomer = trainMapper.findNotHappenedTrainByCustomer(trainFind);
        return notHappenedTrainByCustomer;
    }
}
