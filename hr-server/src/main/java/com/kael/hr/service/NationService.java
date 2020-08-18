package com.kael.hr.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kael.hr.entity.Nation;

import java.util.List;

public interface NationService {
    /**
     * 获取所有民族
     * @return 所有民族列表
     */
    List<Nation> getAllNations() throws JsonProcessingException;
}
