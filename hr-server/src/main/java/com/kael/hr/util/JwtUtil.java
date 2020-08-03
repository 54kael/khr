package com.kael.hr.util;
import com.kael.hr.entity.Menu;
import com.kael.hr.exception.HrException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
public class JwtUtil {
    private JwtUtil(){}

    /**
     * 生成token
     * @param username 用户名，在数据库里面必须唯一
     * @param menus 用户菜单列表
     * @return 根据用户名和权限生成的唯一token
     */
    public static String createJwt(String username, List<Menu> menus) {
        List<String> urls = new ArrayList<>();
        for (Menu menu : menus) {
            urls.add(menu.getUrl());
        }

        /**
         * ==可选属性==
         * .setSubject(username)  主题
         * .setId(id)   JWT_ID
         * .setAudience("") 接受者
         * .setIssuer("")   签发者
         * .setIssuedAt(new Date()) 签发时间
         * .setNotBefore(new Date())    失效时间
         * .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*12))   过期时间
         * .signWith(SignatureAlgorithm.HS512,"kael")   签名和算法
         *  ==自定义属性==
         * .claim("urls",urls)  自定义属性 Map<String,Object></>
         */
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*12))
                .signWith(SignatureAlgorithm.HS512,"kael")
                .claim("urls",urls)
                .compact();
    }



    /**
     * 解析token
     * @param jwt token
     * @return 载有用户名和菜单列表的claims
     */
    public static Claims parseJwt(String jwt) {
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey("kael").parseClaimsJws(jwt).getBody();
        } catch (Exception e) {
            log.warn("**token解析失败**");
        }
        return claims;
    }
}
