package com.kael.hr.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kael.hr.entity.Position;

import java.util.List;

public interface PositionService {

    /**
     * 获取所有职位
     * @return 所有职位列表
     */
    List<Position> findAllPositions() throws JsonProcessingException;

    /**
     * 保存职位
     * @param position 需要保存的职位对象
     */
    void savePosition(Position position);

    /**
     * 删除根据职位
     * @param id 需要删除的职位id
     */
    void deletePosById(Integer id);

    /**
     * 更新职位
     * @param position 需要更新的职位
     */
    void updatePos(Position position );

    /**
     * 更新职位状态
     * @param enabled 职位状态
     */
    void updatePosStatue(Integer id,Byte enabled);
}
