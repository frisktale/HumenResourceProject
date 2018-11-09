package com.frisk.hrs.mapper;


import com.frisk.hrs.pojo.Train;
import com.frisk.hrs.pojo.TrainCustom;
import com.frisk.hrs.pojo.TrainFind;

import java.util.List;

public interface TrainMapper {
    public Train findTrainByIdBase(Integer id) throws Exception;

    public TrainCustom findTrainById(Integer id) throws Exception;

    public List<TrainCustom> findTrainByCustomer(TrainFind trainFind) throws Exception;

    public Integer insertTrain(TrainCustom trainCustom) throws Exception;

    public Integer updateTrain(TrainCustom trainCustom) throws Exception;

    public Integer deleteList(List<Integer> ids) throws Exception;

    public List<TrainCustom> showDelete() throws Exception;

    public Integer recoverTrain(List<Integer> ids) throws Exception;

    public Integer cleanTrain() throws Exception;

    public List<Train> findTrainByEmpId(Integer id) throws Exception;

    public List<Train> findNotHappenedTrainByEmpIdBase(Integer id) throws Exception;

    public List<TrainCustom> findNotHappenedTrainByEmpId(Integer id) throws Exception;

    public List<TrainCustom> findNotHappenedTrainByCustomer(TrainFind trainFind) throws Exception;

}