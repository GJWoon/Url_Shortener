package dev.pluto.url_short.domain.url.service;


import dev.pluto.url_short.domain.url.dto.UrlCommandDto;
import dev.pluto.url_short.domain.url.entity.Url;
import dev.pluto.url_short.domain.url.repository.UrlRepository;
import dev.pluto.url_short.domain.url.utill.PasswordEncoding;
import dev.pluto.url_short.domain.url.utill.UrlEncoding;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.SecureRandom;

@Service
@RequiredArgsConstructor
@Slf4j
public class UrlShortCommandService {
    private final UrlRepository urlRepository;

    private final PasswordEncoding passwordEncoding;
    public String shortingUrl(String destinationUrl,String password) {

        checkAvailUrl(destinationUrl);
        // 비밀번호 인코딩 로직 추가
        Url url = Url.create(destinationUrl,passwordEncoding.pwEncode(password));
        //저장 후 인코딩 url update
        urlRepository.save(url).setShortUrl(UrlEncoding.urlEncoder(url.getId()));

        url.setShortUrl(UrlEncoding.urlEncoder(url.getId()));

        return url.getShortUrl();
    }

    // 사용자가 입력한 URL으로 GET요청 후 정상적인 URL인지 검증
    public void checkAvailUrl(String url) {
        try {
            URL tempUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) tempUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }
}
