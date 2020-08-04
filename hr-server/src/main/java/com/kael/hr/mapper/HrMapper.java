package com.kael.hr.mapper;

import com.kael.hr.entity.Hr;
import org.apache.ibatis.annotations.Param;

public interface HrMapper {
    /**
     * 根据 username 查找密码
     * @param username 用户名
     * @return 用户密码
     */
    String findPasswordByUsername(@Param("username")String username);

    /**
     * 根据 username查找hr信息
     * @param username 用户名
     * @return hr全部信息
     */
    Hr findHrByUsername(@Param("username") String username);

}
