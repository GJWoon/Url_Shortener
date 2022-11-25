package dev.pluto.url_short.global.interceptor;

import dev.pluto.url_short.global.exception.BusinessException;
import dev.pluto.url_short.global.model.ErrorCode;
import dev.pluto.url_short.global.provider.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RequiredArgsConstructor
@Configuration
public class AccessLogInterceptor implements HandlerInterceptor {

    private final TokenProvider tokenProvider;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(request.getMethod().equals( "OPTIONS")){
            return HandlerInterceptor.super.preHandle(request, response, handler);
        }

        Map pathVariables = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

        String requestUrl = (String)pathVariables.get("url");

        String url  = tokenProvider.getUrl();

        if(!requestUrl.equals(url)){
            throw new BusinessException(ErrorCode.FORBIDDEN);
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
