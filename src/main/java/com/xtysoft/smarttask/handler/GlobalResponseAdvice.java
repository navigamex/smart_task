package com.xtysoft.smarttask.handler;//package top.itoken.wallet3.handler;
//
//import com.xtysoft.common.common.Response;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.core.MethodParameter;
//import org.springframework.http.MediaType;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
//
//@ControllerAdvice
//@Slf4j
//public class GlobalResponseAdvice implements ResponseBodyAdvice<Object> {
//
//    /**
//     * 判断是否需要处理返回值
//     */
//    @Override
//    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
//        // 针对所有返回值进行处理
//        return true;
//    }
//
//    /**
//     * 对返回值进行封装
//     */
//    @Override
//    @ResponseBody
//    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
//                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
//                                  org.springframework.http.server.ServerHttpRequest request,
//                                  org.springframework.http.server.ServerHttpResponse response) {
//        // 如果返回值已经是封装后的 Response 类型，则直接返回
//        if (body instanceof Response) {
//            log.info("返回值已是封装类型，无需处理：{}", body);
//            return body;
//        }
//
//        // 否则封装为自定义的 Response 类型
//        if (body != null) {
//            Response<Object> wrappedResponse = Response.success(body);
//            log.info("封装后的返回值：{}", wrappedResponse);
//            return wrappedResponse;
//        }
//
//        // 返回空时统一返回错误信息
//        log.info("返回值为空，封装为错误消息");
//        return Response.error(404, "Not Found");
//    }
//}
