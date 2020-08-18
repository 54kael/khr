package com.kael.hr.controller.system.basic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kael.hr.entity.Department;
import com.kael.hr.entity.vo.DeptParam;
import com.kael.hr.responst.Result;
import com.kael.hr.service.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author kael
 * @Description
 * @Date 2020/8/5 0005
 */

@Slf4j
@Api(tags = "部门管理")
@RestController
@RequestMapping("/system/basic/department")
public class DepartmentController {
    @Resource
    DepartmentService departmentService;

    @ApiOperation("根据父级id查找子部门")
    @GetMapping("/{parentId}")
    public Result getDepartmentsByParentId(@PathVariable("parentId")Integer parentId) throws JsonProcessingException {
        List<Department> departmentList = departmentService.findAllDepartmentsByParentId(parentId);
        return Result.ok().data("departments",departmentList);
    }

    @ApiOperation("添加、更新部门")
    @PostMapping
    public Result saveDepartment(@RequestBody @Validated DeptParam department) {
        Integer id = department.getId();
        if (id==null) {
            log.info("部门id为空，添加部门");
            departmentService.saveDepartment(department);
        } else {
            log.info("部门id为{},更新部门名字",id);
            departmentService.updateDepartment(department);
        }

        return Result.ok();
    }

    @ApiOperation("删除部门")
    @DeleteMapping("/{id}")
    public Result deleteDepartment(@PathVariable("id") Integer id) {
        departmentService.deletedDepartment(id);
        return Result.ok();
    }
}
