package com.kael.hr.service;

import com.kael.hr.entity.User;


public interface UserService {
    /**
     * 用户登录
     */
    String login(User user);

    /**
     * 根据 hr 用户名获取用户信息
     * @param username 用户名
     * @return hr详情
     */
    User findUserInfoByUsername(String username);
}
