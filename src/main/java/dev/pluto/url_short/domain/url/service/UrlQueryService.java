package dev.pluto.url_short.domain.url.service;


import dev.pluto.url_short.domain.url.dto.AccessLogDto;
import dev.pluto.url_short.domain.url.dto.UrlAndTokenDto;
import dev.pluto.url_short.domain.url.dto.UrlCommandDto;
import dev.pluto.url_short.domain.url.dto.UrlDetailDto;
import dev.pluto.url_short.domain.url.entity.AccessLog;
import dev.pluto.url_short.domain.url.entity.Url;
import dev.pluto.url_short.domain.url.repository.AccessLogRepository;
import dev.pluto.url_short.domain.url.repository.UrlRepository;
import dev.pluto.url_short.domain.url.repository.UrlRepositorySupport;
import dev.pluto.url_short.domain.url.utill.PasswordEncoding;
import dev.pluto.url_short.domain.url.utill.UrlEncoding;
import dev.pluto.url_short.global.exception.BusinessException;
import dev.pluto.url_short.global.exception.NotFoundUrlException;
import dev.pluto.url_short.global.model.ErrorCode;
import dev.pluto.url_short.global.provider.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class UrlQueryService {
    private final UrlRepository urlRepository;
    private final UrlRepositorySupport urlRepositorySupport;

    private final TokenProvider tokenProvider;
    private final AccessLogRepository accessLogRepository;

    @Transactional
    public String getDestination_url(String encodedUrl, HttpServletRequest servletRequest) {

        Url url = findByEncodedUrl(encodedUrl);

        url.clickUrl();

        commandAccessLog(servletRequest, url);

        return url.getDestinationUrl();

    }

    @Transactional(readOnly = true)
    public UrlDetailDto findUrlWithAccessLogById(String encodedUrl) {
        return urlRepositorySupport.findDetailUrlWithAccessLogByEncodedUrl(encodedUrl);
    }

    @Transactional(readOnly = true)
    public UrlAndTokenDto checkPw(UrlCommandDto dto) {

        Url url = findByEncodedUrl(dto.getUrl());

        PasswordEncoding.matchPw(dto.getPassword(), url.getPassword());

        return tokenProvider.createJwtDto(url.getShortUrl());

    }

    @Transactional(readOnly = true)
    public boolean checkToken(String url) {
        return url.equals(tokenProvider.getUrl());
    }

    private void commandAccessLog(HttpServletRequest httpServletRequest, Url url) {

        final AccessLog accessLog = AccessLog.create(
                getUserAccessInfo(httpServletRequest),
                url
        );
        accessLogRepository.save(accessLog);
    }

    private AccessLogDto getUserAccessInfo(HttpServletRequest httpServletRequest) {

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

    private Url findByEncodedUrl(String url) {
        return urlRepository.findById(UrlEncoding.urlDecoder(url))
                .orElseThrow(() -> new NotFoundUrlException(ErrorCode.NOT_FOUND_URL));
    }
}
