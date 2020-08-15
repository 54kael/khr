package com.kael.hr.mapper;

import com.kael.hr.entity.Account;
import org.apache.ibatis.annotations.Param;

public interface AccountMapper {
    /**
     * 根据 username 查找密码
     * @param username 用户名
     * @return 用户密码
     */
    String findPasswordByUsername(@Param("username")String username);

    /**
     * 根据 username查找user信息
     * @param username 用户名
     * @return user全部信息
     */
    Account findAccountByUsername(@Param("username") String username);

    /**
     * 添加账号
     * @param account 账号
     */
    void saveAccount(@Param("account") Account account);
}
