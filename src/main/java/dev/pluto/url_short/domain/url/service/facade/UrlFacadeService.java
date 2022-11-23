package dev.pluto.url_short.domain.url.service.facade;


import dev.pluto.url_short.domain.url.dto.UrlAndTokenDto;
import dev.pluto.url_short.domain.url.dto.UrlCommandDto;
import dev.pluto.url_short.domain.url.dto.UrlDetailDto;
import dev.pluto.url_short.domain.url.entity.Url;
import dev.pluto.url_short.domain.url.service.AccessLogCommandService;
import dev.pluto.url_short.domain.url.service.UrlQueryService;
import dev.pluto.url_short.domain.url.service.UrlShortCommandService;
import dev.pluto.url_short.domain.url.utill.PasswordEncoding;
import dev.pluto.url_short.global.provider.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class UrlFacadeService {

    private final UrlQueryService urlQueryService;
    private final AccessLogCommandService accessLogService;
    private final PasswordEncoding passwordEncoding;
    private final UrlShortCommandService urlShortCommandService;

    private final TokenProvider tokenProvider;

    // URL 생성
    @Transactional
    public UrlAndTokenDto shortingUrl(UrlCommandDto dto) {

        Url url = urlShortCommandService.shortingUrl(dto);

        return tokenProvider.createJwtDto(url.getShortUrl());

    }

    //인코딩된 URL을 디코딩후 반환
    @Transactional
    public String getDestination_url(String encodedUrl, HttpServletRequest servletRequest) {

        Url url = urlQueryService.findByEncodedUrl(encodedUrl);

        url.clickUrl();

        accessLogService.commandAccessLog(servletRequest, url);

        return url.getDestinationUrl();

    }

    //URL의 detail 정보와 access_log 조회
    @Transactional(readOnly = true)
    public UrlDetailDto findUrlWithAccessLogById(String encodedUrl) {
        return urlQueryService.findUrlWithAccessLogByEncodedUrl(encodedUrl);
    }

    //URL의 비밀번호 확인
    @Transactional(readOnly = true)
    public UrlAndTokenDto checkPw(UrlCommandDto dto) {

        System.out.println(dto.getUrl());
        Url url = urlQueryService.findByEncodedUrl(dto.getUrl());

        passwordEncoding.matchPw(dto.getPassword(), url.getPassword());

        return tokenProvider.createJwtDto(url.getShortUrl());

    }

    @Transactional(readOnly = true)
    public boolean checkToken(String url){
        return url.equals(tokenProvider.getUrl());
    }

}
