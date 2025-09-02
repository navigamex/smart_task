//package com.xtysoft.smarttask.handler;
//
//import cn.dev33.satoken.stp.StpUtil;
//import com.xtysoft.common.constance.BaseErrorCodeEnum;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//public class SaTokenInterceptor implements HandlerInterceptor {
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        try {
//            StpUtil.checkLogin();
//        } catch (Exception e) {
//            // 设置 HTTP 状态码
//            response.setStatus(HttpServletResponse.SC_OK);
//            // 返回友好的 JSON 提示
//            response.setContentType("application/json;charset=UTF-8");
//            response.getWriter().write("{\"code\":"+ BaseErrorCodeEnum.USER_NOT_LOGIN.getCode()+", \"message\":\""+BaseErrorCodeEnum.USER_NOT_LOGIN.getMessage()+"\"}");
//            return true;
//        }
//
//        return true;
//
//
//    }
//}
