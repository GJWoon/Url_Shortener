package dev.pluto.url_short.global.interceptor;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log4j2
@Component
public class HttpLoggerInterceptor extends HandlerInterceptorAdapter {


        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

            log.info("[REQ] ---> [METHOD] {} | [URL] {} | [qs] {} | [TOKEN] {} | reto {}",request.getMethod(), request.getRequestURI(), request.getQueryString(), request.getHeader(HttpHeaders.AUTHORIZATION),request.getHeader("Authorization: Bearer"));
            return super.preHandle(request, response, handler);
        }


        @Override
        public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
            super.afterCompletion(request, response, handler, ex);
        }

}
