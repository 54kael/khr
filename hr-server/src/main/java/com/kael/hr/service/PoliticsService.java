package com.kael.hr.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kael.hr.entity.Politics;

import java.util.List;

/**
 * @Author kael
 * @Description
 * @Date 2020/8/9 0009
 */
public interface PoliticsService {
    /**
     * 获取所有政治面貌
     * @return 所有政治面貌
     */
    List<Politics> getAllPolitics() throws JsonProcessingException;
}
