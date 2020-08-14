package com.kael.hr.mapper;

import com.kael.hr.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
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
    User findUserByUsername(@Param("username") String username);

}
