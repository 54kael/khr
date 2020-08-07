package com.kael.hr.service.impl;

import com.kael.hr.entity.JobLevel;
import com.kael.hr.exception.HrException;
import com.kael.hr.mapper.JobLevelMapper;
import com.kael.hr.service.JobLevelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author kael
 * @Description
 * @Date 2020/8/7 0007
 */
@Slf4j
@Service
public class JobLevelServiceImpl implements JobLevelService {

    @Resource
    JobLevelMapper jobLevelMapper;
    @Override
    public List<JobLevel> findAllJobLevels() {
        return jobLevelMapper.findAllJobLevels(null);
    }

    @Override
    public void saveJobLevel(JobLevel jobLevel) {
        jobLevel.setEnabled(1);
        List<JobLevel> jobLevels = jobLevelMapper.findAllJobLevels(jobLevel.getName());
        // 判断职称是否重复
        if (jobLevels != null && jobLevels.size() != 0) {
            for (JobLevel jobLevel1 : jobLevels) {
                if (jobLevel.equals(jobLevel1)) {
                    log.error("={}-{}=该职称已存在", jobLevel.getName(), jobLevel.getTitleLevel());
                    throw new HrException("该职称已存在");
                }
            }
        }
        Date date = new Date();
        jobLevel.setCreateDate(date);
        jobLevelMapper.saveJobLevel(jobLevel);
    }

    @Override
    public void updateJobLevel(JobLevel jobLevel) {
        List<JobLevel> jobLevels = jobLevelMapper.findAllJobLevels(jobLevel.getName());
        if (jobLevels != null && jobLevels.size() != 0) {
            for (JobLevel jobLevel1 : jobLevels) {
                if (jobLevel.equals(jobLevel1)) {
                    log.error("={}-{}=该职称已存在", jobLevel.getName(), jobLevel.getTitleLevel());
                    throw new HrException("该职称已存在");
                }
            }
        }
        jobLevelMapper.updateJobLevel(jobLevel);
    }

    @Override
    public void deletedJobLevel(Integer id) {
        long employeeCount = jobLevelMapper.getCountEmployeeByJobLevelId(id);

        if (employeeCount>0) {
            throw new HrException("还有员工使用该职称");
        }
        jobLevelMapper.deletedJobLevel(id);
    }
}
