package com.kael.hr.mapper;

import com.kael.hr.entity.Nation;

import java.util.List;

public interface NationMapper {
    /**
     * 获取所有民族
     * @return 所有民族列表
     */
    List<Nation> findAllNations();
}
