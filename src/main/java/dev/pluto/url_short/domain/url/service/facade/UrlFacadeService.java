package dev.pluto.url_short.domain.url.service.facade;


import dev.pluto.url_short.domain.url.dto.UrlCommandDto;
import dev.pluto.url_short.domain.url.dto.UrlDetailDto;
import dev.pluto.url_short.domain.url.entity.Url;
import dev.pluto.url_short.domain.url.service.AccessLogCommandService;
import dev.pluto.url_short.domain.url.service.UrlQueryService;
import dev.pluto.url_short.domain.url.service.UrlShortCommandService;
import dev.pluto.url_short.domain.url.utill.PasswordEncoding;
import dev.pluto.url_short.domain.url.utill.UrlEncoding;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;


@Service
@RequiredArgsConstructor
@Slf4j
public class UrlFacadeService {

    private final UrlQueryService urlQueryService;
    private final AccessLogCommandService accessLogService;
    private final PasswordEncoding passwordEncoding;
    private final UrlShortCommandService urlShortCommandService;

    // URL 생성
    @Transactional
    public String shortingUrl(UrlCommandDto dto) {
        return urlShortCommandService.shortingUrl(dto.getUrl(), dto.getPassword());
    }

    //인코딩된 URL을 디코딩후 반환
    @Transactional
    public String getDestination_url(String encodedUrl, HttpServletRequest servletRequest) {
        Url url = urlQueryService.findById(
                UrlEncoding.urlDecoder(encodedUrl)
        );
        url.clickUrl();
        accessLogService.commandAccessLog(servletRequest, url);
        return url.getDestinationUrl();
    }

    //URL의 detail 정보와 accesslog 조회
    @Transactional(readOnly = true)
    public UrlDetailDto findUrlWithAccessLogById(String encodedUrl) {
        return urlQueryService.findUrlWithAccessLogByEncodedUrl(encodedUrl);
    }

    //URL의 비밀번호 확인
    @Transactional(readOnly = true)
    public String checkPw(UrlCommandDto dto) {

        Url url = urlQueryService.findById(
                UrlEncoding.urlDecoder(dto.getUrl())
        );

        passwordEncoding.matchPw(dto.getPassword(), url.getPassword());

        return url.getShortUrl();
    }

}
