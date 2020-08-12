package com.kael.hr.mapper;

import com.kael.hr.entity.Politics;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PoliticsMapper {
    /**
     * 获取所有政治面貌
     * @return 所有政治面貌
     */
    List<Politics> findAllPolitics();

    /**
     * 根据id获取政治面貌名
     * @return 政治面貌名
     */
    String findPoliticNameById(@Param("id") Integer id);
}
