//package com.xtysoft.smarttask.filter;
//
//
//import org.springframework.stereotype.Component;
//import com.xtysoft.common.config.FuckMessageSource;
//
//import javax.servlet.*;
//import javax.servlet.FilterConfig;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//@Component
//public class LanguageFilter implements Filter {
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        // 可选：初始化配置
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//
//        // 获取请求头中的 Accept-Language
//        String languageHeader = ((HttpServletRequest) request).getHeader("Accept-Language");
//
//        // 设置语言（按规则优先级：header -> locale -> Locale.US）
//        if (languageHeader != null) {
//            FuckMessageSource.setLocaleFromHeader(languageHeader);
//        } else {
//            // 没有语言头，默认使用Locale.US
//            FuckMessageSource.setLocaleFromHeader("en-US");
//        }
//
//        // 继续请求的处理
//        chain.doFilter(request, response);
//    }
//
//    @Override
//    public void destroy() {
//        // 可选：清理资源
//    }
//}
