package com.kael.hr.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kael.hr.entity.Nation;
import com.kael.hr.mapper.NationMapper;
import com.kael.hr.service.NationService;
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
public class NationServiceImpl implements NationService {
    @Resource
    NationMapper nationMapper;
    @Resource
    StringRedisTemplate stringRedisTemplate;
    @Override
    public List<Nation> getAllNations() throws JsonProcessingException {
        List<Nation> allNations;
        ObjectMapper objectMapper = new ObjectMapper();
        String s = stringRedisTemplate.opsForValue().get("khr:allNations");
        if (s==null || "".equals(s)) {
            log.info("民族redis缓存为空");
            allNations=nationMapper.findAllNations();
            stringRedisTemplate.opsForValue().set("khr:allNations",objectMapper.writeValueAsString(allNations));
            return allNations;
        } else {
            log.info("民族redis缓存不为空");
            return objectMapper.readValue(s,List.class);
        }
    }
}
