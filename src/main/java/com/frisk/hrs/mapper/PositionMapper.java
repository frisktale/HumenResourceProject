package com.frisk.hrs.mapper;


import com.frisk.hrs.pojo.Position;
import com.frisk.hrs.pojo.PositionCustom;
import com.frisk.hrs.pojo.PositionFind;

import java.util.List;

public interface PositionMapper {

    public List<Position> findPositionByDepartmentBase(Integer id)throws Exception;

    public PositionCustom findPositionById(Integer id)throws Exception;
    public Position findPositionByIdBase(Integer id)throws Exception;

    public List<PositionCustom> findPositionByCustomer(PositionFind PositionFind)throws Exception;

    public Integer insertPosition(PositionCustom PositionCustom)throws Exception;

    public Integer updatePosition(PositionCustom PositionCustom)throws Exception;

    public Integer deleteList(List<Integer> ids)throws Exception;

    public List<PositionCustom> showDelete()throws Exception;

    public Integer recoverPosition(List<Integer> ids)throws Exception;

    public Integer cleanPosition()throws Exception;

}