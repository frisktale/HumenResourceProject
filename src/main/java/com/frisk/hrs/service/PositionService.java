package com.frisk.hrs.service;

import com.frisk.hrs.pojo.PositionCustom;

import java.util.List;

/**
 * @author frisktale
 * @date 2018/10/9
 */
public interface PositionService {
    public PositionCustom getPositionById(Integer id) throws Exception;

    public Integer addPosition(Integer depId, String posName) throws Exception;

    public Integer updatePosition(PositionCustom positionCustom) throws Exception;

    public Integer deletePosition(List<Integer> ids) throws Exception;

    public Integer recoverDeletePosition(List<Integer> ids) throws Exception;

    public List<PositionCustom> showDeletePosition() throws Exception;

    public Integer cleanDeletePosition() throws Exception;
}
