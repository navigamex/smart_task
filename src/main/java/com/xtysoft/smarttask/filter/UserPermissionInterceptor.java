package com.xtysoft.smarttask.filter;//package top.itoken.wallet3.config.filter;
//
//import cn.hutool.json.JSONUtil;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.jetbrains.annotations.NotNull;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StreamUtils;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.util.ContentCachingRequestWrapper;
//import com.xtysoft.common.api.dto.ApiBaseDTO;
//import com.xtysoft.common.api.service.ApiUserAccessTokenService;
//import com.xtysoft.common.common.exception.BusinessException;
//import com.xtysoft.common.constance.ErrorCodeEnum;
//import com.xtysoft.common.entity.UserAccessTokenEntity;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.nio.charset.StandardCharsets;
//import java.util.Optional;
//import java.util.function.Function;
//import org.springframework.web.util.ContentCachingRequestWrapper;
//
//import javax.servlet.http.HttpServletRequest;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.nio.charset.StandardCharsets;
//import org.springframework.web.util.ContentCachingRequestWrapper;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//
//
///**
// * 用于拦截API请求,计算合法性的过滤器
// */
//@Component
//public class UserPermissionInterceptor implements HandlerInterceptor {
//    @Autowired
//    private ApiUserAccessTokenService apiUserAccessTokenService;
//
//    @Override
//    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) throws Exception {
//        // 获取请求中的 DTO 或者请求参数
//        ApiBaseDTO apiGetNewAddressDTO = getBaseDtoFromRequest(request); // 假设从请求中提取 DTO
//        // 校验用户权限
//        UserAccessTokenEntity appTokenEntity = apiUserAccessTokenService.checkAndGetUserAccessTokenEntity(apiGetNewAddressDTO);
//
//
//        if (appTokenEntity == null) {
//            // 如果没有权限，可以直接返回错误信息或者抛出异常
//            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//            response.getWriter().write("Permission Denied");
//            return false; // 返回 false 表示请求不继续向下传递
//        }
//
//        // 权限校验通过，继续执行请求
//        return true;
//    }
//
//    private ApiBaseDTO getBaseDtoFromRequest(HttpServletRequest request) {
//        ApiBaseDTO apiBaseDTO = new ApiBaseDTO();
//
//        // 优先从 Header 中获取
//        String accessKey = request.getHeader("accessKey");
//        String nonceStr = request.getHeader("nonce");
//        String sign = request.getHeader("sign");
//
//        // 如果 Header 中没有，则从 GET 参数中获取
//        if (isEmpty(accessKey)) accessKey = request.getParameter("accessKey");
//        if (isEmpty(nonceStr)) nonceStr = request.getParameter("nonce");
//        if (isEmpty(sign)) sign = request.getParameter("sign");
//
//        // 如果 GET 参数中没有，则从 POST JSON 请求体中获取
//        if (isEmpty(accessKey) || isEmpty(nonceStr) || isEmpty(sign)) {
//            if ("POST".equalsIgnoreCase(request.getMethod()) && isJsonRequest(request)) {
//                try {
//                    String jsonBody = getRequestBody(request); // 读取并缓存请求体
//
//                    ApiBaseDTO bodyDto = JSONUtil.toBean(jsonBody, ApiBaseDTO.class);
//
//                    if (isEmpty(accessKey)) accessKey = bodyDto.getAccessKey();
//                    if (isEmpty(nonceStr)) nonceStr = bodyDto.getNonce() != null ? String.valueOf(bodyDto.getNonce()) : null;
//                    if (isEmpty(sign)) sign = bodyDto.getSign();
//                } catch (IOException e) {
//                    throw new BusinessException(ErrorCodeEnum.PARAM_ERROR);
//                }
//
//            }
//        }
//
//        // 检查最终值是否为空，并设置到 DTO 中
//        apiBaseDTO.setAccessKey(validateNotEmpty(accessKey, ErrorCodeEnum.API_ACCESS_EMPTY));
//        apiBaseDTO.setNonce(validateNotEmpty(nonceStr, ErrorCodeEnum.API_SIGN_NONCE_ERROR, Long::valueOf));
//        apiBaseDTO.setSign(validateNotEmpty(sign, ErrorCodeEnum.API_SIGN_SIGN_ERROR));
//
//        return apiBaseDTO;
//    }
//
//    private String getRequestBody(HttpServletRequest request) throws IOException {
//        // 如果请求没有被包装过，使用 ContentCachingRequestWrapper 包装
//        if (!(request instanceof ContentCachingRequestWrapper)) {
//            request = new ContentCachingRequestWrapper(request);
//        }
//
//        // 获取缓存的内容
//        ContentCachingRequestWrapper wrapper = (ContentCachingRequestWrapper) request;
//        byte[] cachedBody = wrapper.getContentAsByteArray();
//
//        if (cachedBody.length > 0) {
//            // 转换为字符串并返回
//            return new String(cachedBody, StandardCharsets.UTF_8);
//        }
//
//        // 如果缓存为空，从原始请求流读取
//        try (BufferedReader reader = wrapper.getReader()) {
//            StringBuilder body = new StringBuilder();
//            String line;
//            while ((line = reader.readLine()) != null) {
//                body.append(line);
//            }
//
//            return body.toString();
//        }
//    }
//
//
//
//    private boolean isEmpty(String value) {
//        return value == null || value.trim().isEmpty();
//    }
//
//    private boolean isJsonRequest(HttpServletRequest request) {
//        return request.getContentType() != null && request.getContentType().contains("application/json");
//    }
//
//    private <T> T validateNotEmpty(String value, ErrorCodeEnum errorCode, Function<String, T> mapper) {
//        try {
//            return mapper.apply(value);
//        } catch (Exception e) {
//            throw new BusinessException(errorCode);
//        }
//    }
//
//    private String validateNotEmpty(String value, ErrorCodeEnum errorCode) {
//        if (isEmpty(value)) {
//            throw new BusinessException(errorCode);
//        }
//        return value;
//    }
//
//
//
//
//
//
//}
