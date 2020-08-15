package com.kael.hr.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kael.hr.responst.Result;
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
            return result(response,HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            claims = JwtUtil.parseJwt(authHeader);
            if (claims==null) {
                log.warn("**token解析失败**");
                return result(response, HttpServletResponse.SC_UNAUTHORIZED);
            }

            String username = claims.getSubject();
            // 从token中得到用户的访问权限列表
            List<String> hrUrls =(List<String>) claims.get("urls");
            // 添加两个只需要登录就可访问的路径
            hrUrls.add("/menu/**");
            hrUrls.add("/user/info/**");
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
            return result(response,HttpServletResponse.SC_FORBIDDEN);
        }
    }
    /**
     * token验证失败
     * @param response 返回json数据
     * @param statue 状态
     */
    private boolean result(HttpServletResponse response,int httpCode) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        ObjectMapper om = new ObjectMapper();
        PrintWriter out = null;
        String s = null;
        try {
            if (httpCode==401) {
                 response.setStatus(401);
                 s = om.writeValueAsString(Result.failure("尚未登录"));
                out = response.getWriter();
            } else if (httpCode==403) {
                 response.setStatus(403);
                 s = om.writeValueAsString(Result.failure("权限不足"));
                 out = response.getWriter();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.println(s);
            out.flush();
            out.close();
        }
        return false;
    }
}
