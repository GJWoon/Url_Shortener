package dev.pluto.url_short.domain.url.service;


import dev.pluto.url_short.domain.url.dto.UrlCommandDto;
import dev.pluto.url_short.domain.url.entity.Url;
import dev.pluto.url_short.domain.url.repository.UrlRepository;
import dev.pluto.url_short.domain.url.utill.PasswordEncoding;
import dev.pluto.url_short.domain.url.utill.UrlEncoding;
import dev.pluto.url_short.global.exception.BusinessException;
import dev.pluto.url_short.global.model.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
@RequiredArgsConstructor
@Slf4j
public class UrlShortCommandService {
    private final UrlRepository urlRepository;

    private final PasswordEncoding passwordEncoding;

    public Url shortingUrl(UrlCommandDto dto) {

        checkAvailUrl(dto.getUrl());

        Url url = Url.create(dto.getUrl(), passwordEncoding.pwEncode(dto.getPassword()));

        urlRepository.save(url).setShortUrl(UrlEncoding.urlEncoder(url.getId()));

        return url;
    }

    // 사용자가 입력한 URL로 GET 요청 후 정상적인 URL인지 검증
    public void checkAvailUrl(String url) {
        try {
            final URL tempUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) tempUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
        } catch (IOException e) {
            throw new BusinessException(ErrorCode.NOT_EFFECTIVE_URL);
        }
    }
}
