package com.xtysoft.smarttask.handler;

import cn.dev33.satoken.stp.StpUtil;
import com.xtysoft.common.constance.BaseErrorCodeEnum;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class SaTokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            StpUtil.checkLogin();
        } catch (Exception e) {
            // 使用ContentCachingResponseWrapper避免getWriter()冲突
            ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
            
            // 设置 HTTP 状态码
            responseWrapper.setStatus(HttpServletResponse.SC_OK);
            // 返回友好的 JSON 提示
            responseWrapper.setContentType("application/json;charset=UTF-8");
            
            String jsonResponse = "{\"code\":"+ BaseErrorCodeEnum.USER_NOT_LOGIN.getCode()+", \"message\":\""+BaseErrorCodeEnum.USER_NOT_LOGIN.getMessage()+"\"}";
            responseWrapper.getWriter().write(jsonResponse);
            
            // 将响应内容写入原始response
            responseWrapper.copyBodyToResponse();
            
            return false;
        }

        return true;
    }
}