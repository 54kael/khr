package com.kael.hr.service;

import com.kael.hr.entity.Hr;
import com.kael.hr.entity.vo.HrLoginParameter;


public interface HrService {
    /**
     * hr登录
     */
    String login(HrLoginParameter hrLoginParameter);

    /**
     * 根据 hr 用户名获取用户信息
     * @param username 用户名
     * @return hr详情
     */
    Hr findHrInfoByUsername(String username);
}
