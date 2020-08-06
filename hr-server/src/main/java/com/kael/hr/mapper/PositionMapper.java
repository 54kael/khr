package com.kael.hr.mapper;

import com.kael.hr.entity.Position;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PositionMapper {
    /**
     * 获取所有职位
     * @return 所有职位列表
     */
    List<Position> findAllPositions();

    /**
     * 保存职位
     * @param position 需要保存的职位对象
     */
    void savePosition(@Param("p") Position position);

    /**
     * 删除根据职位
     * @param id 需要删除的职位id
     */
    void deletePosById(@Param("id") Integer id);

    /**
     * 更新职位
     * @param position 需要更新的职位
     */
    void updatePos(@Param("p") Position position );

    /**
     * 根据职位id获取员工人数
     * @param posId 职位id
     * @return 员工数
     */
    Long getPosEmployeeCount(@Param("posId") Integer posId);

    /**
     * 根据id更新职位状态
     * @param id 职位id
     * @param enabled 职位状态
     */
    void updatePosStatue(@Param("id") Integer id,@Param("enabled") Byte enabled);

    /**
     * 根据职位名称查找职位
     * @param name 职位名称
     * @return 职位
     */
    Position findPosByName(@Param("name") String name);
}
