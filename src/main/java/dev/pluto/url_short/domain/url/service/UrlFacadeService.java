package dev.pluto.url_short.domain.url.service;


import dev.pluto.url_short.domain.url.dto.UrlDetailDto;
import dev.pluto.url_short.domain.url.entity.Url;
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
    private final UrlEncoding urlEncoding;
    private final AccessLogCommandService accessLogService;
    @Transactional
    public String getDestination_url(String encodedUrl, HttpServletRequest servletRequest) {
        Url url = urlQueryService.findById(
                urlEncoding.urlDecoder(encodedUrl)
        );

        url.clickUrl();

        accessLogService.commandAccessLog(servletRequest, url);

        return url.getDestinationUrl();
    }
    @Transactional(readOnly = true)
    public UrlDetailDto findUrlWithAccessLogById(String encodedUrl){
        return urlQueryService.findUrlWithAccessLogByEncodedUrl(encodedUrl);
    }

}
