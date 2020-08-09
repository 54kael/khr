package com.kael.hr.service.impl;

import com.kael.hr.entity.Politics;
import com.kael.hr.mapper.PoliticsMapper;
import com.kael.hr.service.PoliticsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author kael
 * @Description
 * @Date 2020/8/9 0009
 */
@Service
public class PoliticsServiceImpl implements PoliticsService {
    @Resource
    PoliticsMapper politicsMapper;
    @Override
    public List<Politics> getAllPolitics() {
        return politicsMapper.findAllPolitics();
    }
}
