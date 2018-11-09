package com.frisk.hrs.service.impl;

import com.frisk.hrs.mapper.EmployeeMapper;
import com.frisk.hrs.mapper.PositionMapper;
import com.frisk.hrs.pojo.Department;
import com.frisk.hrs.pojo.Position;
import com.frisk.hrs.pojo.PositionCustom;
import com.frisk.hrs.pojo.PositionFind;
import com.frisk.hrs.service.EmployeeService;
import com.frisk.hrs.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author frisktale
 * @date 2018/10/9
 */
@Service
public class PositionServiceImpl implements PositionService {
    @Autowired
    @Qualifier("positionMapper")
    private PositionMapper positionMapper;


    @Autowired
    private EmployeeService employeeService;

    @Override
    public PositionCustom getPositionById(Integer id) throws Exception {
        PositionCustom position = positionMapper.findPositionById(id);
        return position;
    }

    @Override
    public Integer addPosition(Integer depId, String posName) throws Exception {
        PositionFind positionFind = new PositionFind();
        positionFind.andNameIs(posName);
        List<PositionCustom> positionByCustomer = positionMapper.findPositionByCustomer(positionFind);
        if (positionByCustomer.size()>0){
            return 0;
        }
        Position position = new Position();
        position.setCreateTime(new Date());
        position.setName(posName);
        Department department = new Department();
        department.setId(depId);
        PositionCustom positionCustom = new PositionCustom();
        positionCustom.setDepartment(department);
        positionCustom.setPosition(position);
        Integer line = positionMapper.insertPosition(positionCustom);
        return line;
    }

    @Override
    public Integer updatePosition(PositionCustom positionCustom) throws Exception {
        PositionFind positionFind = new PositionFind();
        positionFind.andNameIs(positionCustom.getPosition().getName());
        List<PositionCustom> positionByCustomer = positionMapper.findPositionByCustomer(positionFind);
        if (positionByCustomer.size()>0){
            return 0;
        }
        Integer row = positionMapper.updatePosition(positionCustom);
        return row;
    }

    @Override
    public Integer deletePosition(List<Integer> ids) throws Exception {
        for (Integer id : ids) {
            if (employeeService.getEmpByPosId(id).size()!=0){
                return 0;
            }
        }
        Integer row = positionMapper.deleteList(ids);
        return row;
    }

    @Override
    public Integer recoverDeletePosition(List<Integer> ids) throws Exception {
        Integer row = positionMapper.recoverPosition(ids);
        return row;
    }

    @Override
    public List<PositionCustom> showDeletePosition() throws Exception {
        List<PositionCustom> showDelete = positionMapper.showDelete();
        return showDelete;
    }

    @Override
    public Integer cleanDeletePosition() throws Exception {
        Integer row = positionMapper.cleanPosition();
        return row;
    }
}
