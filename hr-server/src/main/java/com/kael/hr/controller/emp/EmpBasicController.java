package com.kael.hr.controller.emp;

import com.kael.hr.entity.*;
import com.kael.hr.entity.vo.FindEmployeeCondition;
import com.kael.hr.responst.Result;
import com.kael.hr.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author kael
 * @Description
 * @Date 2020/8/9 0009
 */
@Slf4j
@Api(tags = "员工管理")
@RestController
@RequestMapping("/employee/basic")
public class EmpBasicController {
    @Resource
    EmployeeService employeeServiceImpl;
    @Resource
    NationService nationServiceImpl;
    @Resource
    PoliticsService politicsServiceImpl;
    @Resource
    DepartmentService departmentServiceImpl;
    @Resource
    JobLevelService jobLevelServiceImpl;
    @Resource
    PositionService positionServiceImpl;

    @ApiOperation("基本信息选项初始化")
    @GetMapping("/basicInfo")
    public Result initEmpBasicInfo(){
        List<Nation> nations = nationServiceImpl.getAllNations();
        List<Politics> politics = politicsServiceImpl.getAllPolitics();
        List<Position> positions = positionServiceImpl.findAllPositions();
        List<JobLevel> jobLevels = jobLevelServiceImpl.findAllJobLevels();
        List<Department> departments = departmentServiceImpl.findAllDepartmentsByParentId(-1);
        Map<String,Object> map = new HashMap<>();
        map.put("nations",nations);
        map.put("politics",politics);
        map.put("positions",positions);
        map.put("jobLevels",jobLevels);
        map.put("departments",departments);
        return Result.ok().data(map);
    }

    @ApiOperation("条件分页查询员工")
    @PostMapping("/{page}")
    public Result getEmployeeByPageCondition(@PathVariable("page") Integer page, @RequestBody(required = false) FindEmployeeCondition cond) {
        Map<String, Object> emp = employeeServiceImpl.findEmpByPageCondition(page, cond);
        return Result.ok().data(emp);
    }

    @ApiOperation("添加、修改员工信息")
    @PostMapping
    public Result saveEmployee(@RequestBody @Validated Employee employee){
        System.out.println(employee.getBirthday());
        String workId = employee.getWorkId();
        if (workId==null) {
            log.info("工号为空，添加员工");
            employeeServiceImpl.saveEmployee(employee);
        } else {
            log.info("工号为{}",workId);
            employeeServiceImpl.updateEmployee(employee);
        }
        return Result.ok();
    }
}
