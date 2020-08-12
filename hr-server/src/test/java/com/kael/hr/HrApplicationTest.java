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

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

    @Test
    public void testDate() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = dateFormat.parse("2018-01-11");
        Date endDate = dateFormat.parse("2020-10-11");
        LocalDate sd = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate ed = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Double a = ChronoUnit.DAYS.between(sd,ed)/365.0;
        DecimalFormat df2 = new DecimalFormat("###.00");
        System.out.println(df2.format(a));
    }
}
