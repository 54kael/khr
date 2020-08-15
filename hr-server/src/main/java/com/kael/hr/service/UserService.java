package com.kael.hr.service;

import com.kael.hr.entity.Account;


public interface UserService {
    /**
     * 用户登录
     */
    String login(String username, String password);

    /**
     * 根据 用户名 用户名获取账号信息
     * @param username 用户名
     * @return 账号信息
     */
    Account findAccountByUsername(String username);
}
