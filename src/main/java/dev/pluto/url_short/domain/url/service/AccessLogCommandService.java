package dev.pluto.url_short.domain.url.service;


import dev.pluto.url_short.domain.url.dto.AccessLogDto;
import dev.pluto.url_short.domain.url.entity.AccessLog;
import dev.pluto.url_short.domain.url.entity.Url;
import dev.pluto.url_short.domain.url.repository.AccessLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class AccessLogCommandService {

    private final AccessLogRepository accessLogRepository;

    @Transactional
    protected void commandAccessLog(HttpServletRequest httpServletRequest, Url url) {

        AccessLog accessLog = new AccessLog().create(
                getUserAccessInfo(httpServletRequest),
                url
        );
        accessLogRepository.save(accessLog);
    }

    protected AccessLogDto getUserAccessInfo(HttpServletRequest httpServletRequest) {

        return new AccessLogDto(
                getClientIP(httpServletRequest),
                httpServletRequest.getHeader("USER-AGENT"),
                httpServletRequest.getHeader("Referer")
        );
    }


    private String getClientIP(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");

        if (ip == null) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }


}
