package com.kael.hr.service.impl;

import com.kael.hr.entity.Position;
import com.kael.hr.exception.HrException;
import com.kael.hr.mapper.PositionMapper;
import com.kael.hr.service.PositionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author kael
 * @Description
 * @Date 2020/8/6 0006
 */
@Slf4j
@Service
public class PositionServiceImpl implements PositionService {
    @Resource
    PositionMapper positionMapper;

    @Override
    public List<Position> findAllPositions() {
        return positionMapper.findAllPositions();
    }

    @Override
    public void savePosition(Position pos) {
        Position position1 = positionMapper.findPosByName(pos.getName());
        if (position1 != null) {
            log.error("={}=职位已存在",pos.getName());
            throw new HrException("该职位已存在");
        }
        Date date = new Date();
        pos.setCreateDate(date);
        pos.setEnabled(1);
        positionMapper.savePosition(pos);
    }

    @Override
    public void deletePosById(Integer id) {
        // 检查该职位是否还有员工
        Long employeeCount = positionMapper.getPosEmployeeCount(id);
        if (employeeCount==0) {
            positionMapper.deletePosById(id);
        } else {
            log.error("={}=职位下还有关联员工",id);
            throw new HrException("该职位还有关联员工");
        }
    }

    @Override
    public void updatePos(Position pos) {
        positionMapper.updatePos(pos);
    }

    @Override
    public void updatePosStatue(Integer id, Byte enabled) {
        positionMapper.updatePosStatue(id,enabled);
    }
}
