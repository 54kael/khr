package com.kael.hr;

import com.kael.hr.entity.Menu;
import com.kael.hr.entity.Role;
import com.kael.hr.service.EmployeeService;
import com.kael.hr.util.IgnoreUrlsConfig;
import com.kael.hr.util.JwtUtil;
import com.kael.hr.util.PasswordEncrypted;
import com.kael.hr.util.WorkIdPrefix;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HrApplication.class)
public class HrApplicationTest {
    @Autowired
    IgnoreUrlsConfig ignoreUrlsConfig;

    @Autowired
    EmployeeService employeeService;
    @Test
    public void passwordTest() {
        String encrypted = PasswordEncrypted.encrypted("123");
        System.out.println(encrypted);
    }

    @Test
    public void testConfig(){
        System.out.println(ignoreUrlsConfig.getUrls());
    }

    @Test
    public void createJwt() {
        Menu menu = new Menu();
        menu.setUrl("/employee/basic/**");
        Menu menu1 = new Menu();
        menu1.setUrl("/employee/advanced/**");
        Menu menu2 = new Menu();
        menu2.setUrl("personnel/emp");
        Menu menu3 = new Menu();
        menu3.setUrl("/salary/sob/**");
        Menu menu4 = new Menu();
        menu4.setUrl("/salary/table/**");
        List<Menu> menus = new ArrayList<>();
        menus.add(menu);
        menus.add(menu1);
        menus.add(menu2);
        menus.add(menu3);
        menus.add(menu4);
        String username = "admin";
        String jwt = JwtUtil.createJwt(username, menus);
        System.out.println(jwt);
    }


    @Test
    public void testWorkIdPrefix(){
        employeeService.saveEmployee(null);
    }
}
