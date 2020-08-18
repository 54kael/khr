package com.kael.hr.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kael.hr.entity.Politics;
import com.kael.hr.mapper.PoliticsMapper;
import com.kael.hr.service.PoliticsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author kael
 * @Description
 * @Date 2020/8/9 0009
 */
@Slf4j
@Service
public class PoliticsServiceImpl implements PoliticsService {
    @Resource
    StringRedisTemplate stringRedisTemplate;
    @Resource
    PoliticsMapper politicsMapper;
    @Override
    public List<Politics> getAllPolitics() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Politics> allPolitics = politicsMapper.findAllPolitics();
        String s = stringRedisTemplate.opsForValue().get("khr:allPolitics");
        if (s==null || "".equals(s)) {
            log.info("政治面貌redis缓存为空");
            allPolitics = politicsMapper.findAllPolitics();
            stringRedisTemplate.opsForValue().set("khr:allPolitics",objectMapper.writeValueAsString(allPolitics));
            return allPolitics;
        } else {
            log.info("政治面redis貌缓存不为空");
            return objectMapper.readValue(s,List.class);
        }

    }
}
