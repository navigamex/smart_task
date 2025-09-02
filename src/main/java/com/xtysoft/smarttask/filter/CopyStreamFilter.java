package com.xtysoft.smarttask.filter;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class CopyStreamFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 过滤器初始化操作，可留空
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {


        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        if (!httpRequest.getRequestURI().startsWith("/api/v1/api/")) {
//            log.info("不是/api/v1/api/开头,跳过不执行");
            chain.doFilter(request, response); // 跳过过滤器
            return;
        }
//        log.info("是/api开头，执行过滤器 {}", httpRequest.getRequestURI());

        // 获取原始输入流
        InputStream originalInputStream = httpRequest.getInputStream();

        // 创建一个字节数组输出流
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        // 复制输入流到输出流
        copyStream(originalInputStream, byteArrayOutputStream);

        // 获取请求体内容并转为字符串
        byte[] copiedBytes = byteArrayOutputStream.toByteArray();
        String requestBody = new String(copiedBytes, StandardCharsets.UTF_8).trim();  // 去掉前后的空格


        // 获取请求中的 DTO 或者请求参数
        Map<String, String> apiGetNewAddressDTO = getBaseDtoFromRequest((HttpServletRequest) request, requestBody); // 假设从请求中提取 DTO
        // 校验用户权限
//        try {
//            // 校验用户权限
//            UserAccessTokenEntity appTokenEntity = apiUserAccessTokenService.checkAndGetUserAccessTokenEntity(apiGetNewAddressDTO);
//            log.info("用户token获取成功：{}", appTokenEntity);
//        } catch (BusinessException e) {
//            // 捕获异常并记录日志，或做其他处理
//            log.error("用户 token 校验失败，错误码: {}", e.getErrorCode(), e);
//
//            // 返回友好的错误 JSON
//            httpResponse.setContentType("application/json;charset=UTF-8");
//            httpResponse.getWriter().write(JSONUtil.toJsonStr(Response.error(e.getErrorCode(), e.getMessage())));
//
//            log.error("用户 token 校验失败，错误码: {}", e.getErrorCode(), e);
//            // 根据需求可以将异常重新抛出，或者返回特定的错误响应
////            throw e; // 如果需要继续抛出异常
//            return; // 如果不需要继续抛出异常，可以直接返回
//        }
        // 仅在调试模式下打印请求信息
        log.info("请求地址：{}", httpRequest.getRequestURI());
        log.info("请求参数：{}", requestBody);

        // 使用自定义的 HttpServletRequestWrapper 包装请求体
        HttpServletRequestWrapper wrappedRequest = new HttpServletRequestWrapper(httpRequest) {
            @Override
            public ServletInputStream getInputStream() throws IOException {
                // 返回通过 ByteArrayInputStream 包装的字节数组
                return new ServletInputStream() {
                    private final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(copiedBytes);

                    @Override
                    public int read() throws IOException {
                        return byteArrayInputStream.read();
                    }

                    @Override
                    public boolean isFinished() {
                        return byteArrayInputStream.available() == 0;
                    }

                    @Override
                    public boolean isReady() {
                        return true;
                    }

                    @Override
                    public void setReadListener(ReadListener readListener) {
                        // 不需要异步读取
                    }
                };
            }

            @Override
            public BufferedReader getReader() throws IOException {
                // 通过字节流生成 BufferedReader 供其他逻辑读取
                return new BufferedReader(new InputStreamReader(getInputStream()));
            }
        };

        // 继续处理请求链
        chain.doFilter(wrappedRequest, response);
    }

    @Override
    public void destroy() {
        // 过滤器销毁操作，可留空
    }

    private void copyStream(InputStream input, OutputStream output) throws IOException {
        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = input.read(buffer)) != -1) {
            output.write(buffer, 0, bytesRead);
        }
    }

    private Map<String, String> getBaseDtoFromRequest(HttpServletRequest request, String jsonBody) {
        Map<String, String> params = new HashMap<>();

        // 获取所有请求参数
        if ("POST".equalsIgnoreCase(request.getMethod()) && isJsonRequest(request)) {
            //如果是post
            JSONObject json = JSONUtil.parseObj(jsonBody);
//            然后全部写入
            json.forEach((key, value) -> {
                params.put(key, value.toString());
            }) ;
            return params;
        }

        // 获取所有的请求参数
        for (Enumeration<String> paramNames = request.getParameterNames(); paramNames.hasMoreElements();) {
            String paramName = paramNames.nextElement();
            String paramValue = request.getParameter(paramName);
            params.put(paramName, paramValue);
        }

        return params;
    }

    private boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    private boolean isJsonRequest(HttpServletRequest request) {
        return request.getContentType() != null && request.getContentType().contains("application/json");
    }

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
}
