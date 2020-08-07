package com.kael.hr.service;

import com.kael.hr.entity.JobLevel;

import java.util.List;

/**
 * @Author kael
 * @Description
 * @Date 2020/8/7 0007
 */
public interface JobLevelService {
    /**
     * 获取所有职称
     * @return 所有职称
     */
    List<JobLevel> findAllJobLevels();

    /**
     * 添加职称
     * @param jobLevel 职称
     */
    void saveJobLevel(JobLevel jobLevel);

    /**
     * 更新职称
     * @param jobLevel 职称
     */
    void updateJobLevel(JobLevel jobLevel);

    /**
     * 删除职称
     * @param id 职称id
     */
    void deletedJobLevel(Integer id);

}
