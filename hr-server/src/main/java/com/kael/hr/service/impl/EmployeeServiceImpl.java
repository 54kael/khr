package com.kael.hr.service.impl;

import com.kael.hr.entity.Account;
import com.kael.hr.entity.Employee;
import com.kael.hr.entity.vo.FindEmployeeCondition;
import com.kael.hr.mapper.*;
import com.kael.hr.service.EmployeeService;
import com.kael.hr.util.PasswordEncrypted;
import com.kael.hr.util.WorkIdPrefix;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * @Author kael
 * @Description
 * @Date 2020/8/9 0009
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Resource
    EmployeeMapper employeeMapper;
    @Resource
    DepartmentMapper departmentMapper;
    @Resource
    JobLevelMapper jobLevelMapper;
    @Resource
    PositionMapper positionMapper;
    @Resource
    AccountMapper accountMapper;
    @Resource
    RoleMapper roleMapper;

    @Override
    public Map<String,Object> findEmpByPageCondition(int currentPage, FindEmployeeCondition cond) {
        int offset = (currentPage-1)*LIMIT;
        System.out.println(cond);
        List<Employee> employeeList = employeeMapper.findEmpByPageCondition(LIMIT, offset, cond);
        long countEmployeeByCondition = employeeMapper.getCountEmployeeByCondition(cond);
        Map<String,Object> map = new HashMap<>();
        map.put("employees",employeeList);
        map.put("total",countEmployeeByCondition);
        return map;
    }

    @Transactional
    @Override
    public void saveEmployee(Employee employee) {
        String workId;
        // 获取当天入职员工数，并加1，组成新员工id
        // 查询员工时，需要开启事务并且用<for update>锁表,防止出错
        String idPrefix = WorkIdPrefix.createWorkIdPrefix();
        int nu = employeeMapper.getCountEmpByCurrentDay(idPrefix)+1;
        if (nu<10) {
            // 员工数小于10，前面补“0”
            String a = "0"+nu+"";
            workId=idPrefix+a;
        } else {
            workId = idPrefix+nu;
        }
        employee.setWorkId(workId);

        Employee employee1 = redundantFieldAssignment(employee);
        employeeMapper.saveEmployee(employee1);

        // 添加员工账号
        Account account = new Account();
        account.setUsername(employee.getUsername());
        account.setPassword(PasswordEncrypted.encrypted("123456"));
        account.setName(employee.getName());
        account.setWorkId(workId);
        accountMapper.saveAccount(account);

        // 保存员工角色
        roleMapper.saveAccountRole(account.getId(),employee.getRoleIds());
    }

    @Transactional
    @Override
    public void updateEmployee(Employee employee) {
        System.out.println(employee.getBirthday());
        Employee employee1 = redundantFieldAssignment(employee);
        employeeMapper.updateEmployee(employee1);
    }

    @Override
    public List<Employee> findExportDate() {
        return employeeMapper.findExportDate();
    }

    /**
     * employee冗余字段赋值
     * @param employee 不完整字段
     * @return 完整employee
     */
    private Employee redundantFieldAssignment(Employee employee){
        // 计算合同期限
        LocalDate sd = employee.getBeginContract().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate ed = employee.getEndContract().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Double a = ChronoUnit.DAYS.between(sd,ed)/365.0;
        DecimalFormat df2 = new DecimalFormat("###.00");
        employee.setDepartment(departmentMapper.findDeptNameById(employee.getDepartmentId()));
        employee.setJobLevel(jobLevelMapper.findJobLevelNameById(employee.getJobLevelId()));
        employee.setPos(positionMapper.findPosNameById(employee.getPosId()));
        employee.setContractTerm(Double.valueOf(df2.format(a)));
        return employee;
    }
}
