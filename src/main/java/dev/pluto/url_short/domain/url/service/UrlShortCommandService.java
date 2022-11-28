package dev.pluto.url_short.domain.url.service;


import dev.pluto.url_short.domain.url.dto.UrlAndTokenDto;
import dev.pluto.url_short.domain.url.dto.UrlCommandDto;
import dev.pluto.url_short.domain.url.entity.Url;
import dev.pluto.url_short.domain.url.repository.UrlRepository;
import dev.pluto.url_short.domain.url.utill.PasswordEncoding;
import dev.pluto.url_short.domain.url.utill.UrlEncoding;
import dev.pluto.url_short.global.exception.BusinessException;
import dev.pluto.url_short.global.model.ErrorCode;
import dev.pluto.url_short.global.provider.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
@RequiredArgsConstructor
public class UrlShortCommandService {
    private final UrlRepository urlRepository;

    private final TokenProvider tokenProvider;
    @Transactional
    public UrlAndTokenDto shortingUrl(UrlCommandDto dto) {

        checkAvailUrl(dto.getUrl());

        final Url url = Url.create(dto.getUrl(), PasswordEncoding.pwEncode(dto.getPassword()));



        urlRepository.save(url).setShortUrl(UrlEncoding.encode(url.getId()));


        System.out.println(url.getId());

        return tokenProvider.createJwtDto(url.getShortUrl());

    }

    // 사용자가 입력한 URL로 GET 요청 후 정상적인 URL인지 검증
    private void checkAvailUrl(String url) {
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
