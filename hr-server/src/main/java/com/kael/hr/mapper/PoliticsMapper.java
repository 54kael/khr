package com.kael.hr.mapper;

import com.kael.hr.entity.Politics;

import java.util.List;

public interface PoliticsMapper {
    /**
     * 获取所有政治面貌
     * @return 所有政治面貌
     */
    List<Politics> findAllPolitics();
}
