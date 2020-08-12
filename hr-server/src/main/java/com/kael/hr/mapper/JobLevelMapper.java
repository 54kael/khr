package com.kael.hr.mapper;

import com.kael.hr.entity.JobLevel;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface JobLevelMapper {
    /**
     * 根据职称名字获取所有职称
     * @return 职称列表
     */
    List<JobLevel> findAllJobLevels(@Param("name") String name);

    /**
     * 添加职称
     * @param jobLevel 职称
     */
    void saveJobLevel(@Param("jobLevel") JobLevel jobLevel);

    /**
     * 更新职称
     * @param jobLevel 职称
     */
    void updateJobLevel(@Param("jobLevel") JobLevel jobLevel);

    /**
     * 获取职称下面有多少员工
     * @param JobLevelId 职称id
     * @return 员工数量
     */
    long getCountEmployeeByJobLevelId(@PathVariable("jobLevelId") Integer JobLevelId);

    /**
     * 删除职称
     * @param id 职称id
     */
    void deletedJobLevel(@Param("id") Integer id);

    /**
     * 根据职称id查找职称名称
     * @param id 职称id
     * @return 职称名字
     */
    String findJobLevelNameById(@Param("id") Integer id);

}
