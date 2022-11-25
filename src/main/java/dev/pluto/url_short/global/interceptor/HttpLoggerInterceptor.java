package dev.pluto.url_short.global.interceptor;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log4j2
@Component
public class HttpLoggerInterceptor implements HandlerInterceptor {
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            log.info("[REQ] ---> [METHOD] {} | [URL] {} | [TOKEN] {}",request.getMethod(), request.getRequestURI(), request.getHeader(HttpHeaders.AUTHORIZATION));
            return HandlerInterceptor.super.preHandle(request, response, handler);
        }
}
