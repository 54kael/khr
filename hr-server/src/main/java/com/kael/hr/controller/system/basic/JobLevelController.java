package com.kael.hr.controller.system.basic;

import com.kael.hr.entity.JobLevel;
import com.kael.hr.responst.Result;
import com.kael.hr.service.JobLevelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author kael
 * @Description
 * @Date 2020/8/7 0007
 */
@Slf4j
@Api(tags = "职称管理")
@RestController
@RequestMapping("/system/basic/jobLevel")
public class JobLevelController {
    @Autowired
    JobLevelService jobLevelServiceImpl;

    @ApiOperation("获取所有职位")
    @GetMapping
    public Result getAllJobLevels(){
        List<JobLevel> jobLevels = jobLevelServiceImpl.findAllJobLevels();
        return Result.ok().data("jobLevels",jobLevels);
    }

    @ApiOperation("添加、更新职称")
    @PostMapping
    public Result putJobLevel(@RequestBody JobLevel jobLevel) {
        Integer jobLevelId = jobLevel.getId();
        if (jobLevelId==null) {
            log.info("职称id为空，添加职称");
            jobLevelServiceImpl.saveJobLevel(jobLevel);
        } else {
            if (jobLevel.getEnabled()==null) {
                log.warn("更新信息的职位状态为空");
            }
            log.info("更新职称{}的信息",jobLevelId);
            jobLevelServiceImpl.updateJobLevel(jobLevel);
        }
        return Result.ok();
    }

    @ApiOperation("删除职称")
    @DeleteMapping("/{id}")
    public Result deletedJobLevel(@PathVariable("id") Integer id) {
        jobLevelServiceImpl.deletedJobLevel(id);
        return Result.ok();
    }

}
