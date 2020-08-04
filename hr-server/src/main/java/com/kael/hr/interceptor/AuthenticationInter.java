package com.kael.hr.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kael.hr.responst.Result;
import com.kael.hr.responst.Statue;
import com.kael.hr.util.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

@Slf4j
public class AuthenticationInter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        // options请求不会携带请求头‘Authorization’,直接放行
        if (request.getMethod().equals("OPTIONS")) {
            return true;
        }

        String requestUrl = request.getRequestURI();
        log.info("访问路径:{}",requestUrl);

        // token解析出的载体，里面存储用户相关信息
        Claims claims;

        String authHeader = request.getHeader("Authorization");

        if (authHeader==null) {
            log.info("请求头为空");
            return result(response,Statue.UNAUTHORIZED);
        } else {
            claims = JwtUtil.parseJwt(authHeader);
            if (claims==null) {
                log.warn("**token解析失败**");
                return result(response,Statue.UNAUTHORIZED);
            }

            String username = claims.getSubject();
            // 从token中得到用户的访问权限列表
            List<String> hrUrls =(List<String>) claims.get("urls");
            // 添加两个只需要登录就可访问的路径
            hrUrls.add("/menu/**");
            hrUrls.add("/hr/info/**");
            // 匹配用户的访问权限
            AntPathMatcher antPathMatcher = new AntPathMatcher();
            for (String hrUrl : hrUrls) {
                // 权限匹配成功，放行
                if (antPathMatcher.match(hrUrl,requestUrl)) {
                    request.setAttribute("username",username);
                    return true;
                }
            }
            // 权限匹配失败
            log.warn("'{}'访问'{}'权限不足",username,requestUrl);
            return result(response,Statue.FORBIDDEN);
        }
    }

    /**
     * token验证失败
     * @param response 返回json数据
     * @param statue 状态
     */
    private boolean result(HttpServletResponse response,Statue statue) {
        // 未登录
        if (statue.getCode().equals(Statue.UNAUTHORIZED.getCode())) {
            statue=Statue.UNAUTHORIZED;
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

        // 未授权
        if (statue.getCode().equals(Statue.FORBIDDEN.getCode())) {
            statue=Statue.FORBIDDEN;
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try {
            ObjectMapper om = new ObjectMapper();
            String s = om.writeValueAsString(Result.failure(statue));
            PrintWriter out = response.getWriter();
            out.println(s);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
