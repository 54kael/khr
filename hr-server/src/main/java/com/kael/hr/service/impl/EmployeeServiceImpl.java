package com.kael.hr.service.impl;

import com.kael.hr.entity.Employee;
import com.kael.hr.entity.vo.FindEmployeeCondition;
import com.kael.hr.entity.vo.PutEmpParam;
import com.kael.hr.mapper.EmployeeMapper;
import com.kael.hr.service.EmployeeService;
import com.kael.hr.util.WorkIdPrefix;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
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

    SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
    SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
    DecimalFormat decimalFormat = new DecimalFormat("##.00");

    @Override
    public Map<String,Object> findEmpByPageCondition(int currentPage, FindEmployeeCondition cond) {
        int offset = (currentPage-1)*LIMIT;
        List<Employee> employeeList = employeeMapper.findEmpByPageCondition(LIMIT, offset, cond);
        long countEmployeeByCondition = employeeMapper.getCountEmployeeByCondition(cond);
        Map<String,Object> map = new HashMap<>();
        map.put("employees",employeeList);
        map.put("total",countEmployeeByCondition);
        return map;
    }

    @Transactional
    @Override
    public void saveEmployee(PutEmpParam employee) {
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
        Date beginContract = employee.getBeginContract();
        Date endContract = employee.getEndContract();
        double month = (Double.parseDouble(yearFormat.format(endContract)) - Double.parseDouble(yearFormat.format(beginContract))) * 12 + (Double.parseDouble(monthFormat.format(endContract)) - Double.parseDouble(monthFormat.format(beginContract)));
        employee.setContractTerm(Double.parseDouble(decimalFormat.format(month / 12)));
        employee.setWorkId(workId);
        System.out.println(employee);
        employeeMapper.saveEmployee(employee);
    }

    @Override
    public void updateEmployee(PutEmpParam employee) {
        Date beginContract = employee.getBeginContract();
        Date endContract = employee.getEndContract();
        double month = (Double.parseDouble(yearFormat.format(endContract)) - Double.parseDouble(yearFormat.format(beginContract))) * 12 + (Double.parseDouble(monthFormat.format(endContract)) - Double.parseDouble(monthFormat.format(beginContract)));
        employee.setContractTerm(Double.parseDouble(decimalFormat.format(month / 12)));
        employeeMapper.updateEmployee(employee);
    }
}
