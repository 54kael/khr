package com.kael.hr.service.impl;

import com.kael.hr.entity.Nation;
import com.kael.hr.mapper.NationMapper;
import com.kael.hr.service.NationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author kael
 * @Description
 * @Date 2020/8/9 0009
 */
@Service
public class NationServiceImpl implements NationService {
    @Resource
    NationMapper nationMapper;
    @Override
    public List<Nation> getAllNations() {
        return nationMapper.findAllNations();
    }
}
