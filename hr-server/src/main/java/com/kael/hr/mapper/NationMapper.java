package com.kael.hr.mapper;

import com.kael.hr.entity.Nation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NationMapper {
    /**
     * 获取所有民族
     * @return 所有民族列表
     */
    List<Nation> findAllNations();

    /**
     * 根据民族id获取民族
     * @param id 民族id
     * @return 民族名
     */
    String findNationNameById(@Param("id") Integer id);
}
