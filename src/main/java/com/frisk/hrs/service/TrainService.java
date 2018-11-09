package com.frisk.hrs.service;

import com.frisk.hrs.pojo.TrainCustom;

import java.util.List;

/**
 * @author frisktale
 * @date 2018/10/9
 */
public interface TrainService {
    public TrainCustom getTrainById(Integer id) throws Exception;


    public List<TrainCustom> getTrainsByPositionId(Integer id) throws Exception;

    public List<TrainCustom> getTrainsByDepId(Integer id) throws Exception;

    public Integer addTrain(TrainCustom trainCustom) throws Exception;

    public Integer updateTrain(TrainCustom trainCustom) throws Exception;

    public Integer deleteTrain(List<Integer> ids) throws Exception;

    public List<TrainCustom> getUnHappenedTrainByEmpId(Integer id) throws Exception;

    public List<TrainCustom> getUnHappenedTrainByDepIdAndPosId(Integer depId,Integer posId) throws Exception;
}
