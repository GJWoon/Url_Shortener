package dev.pluto.url_short.domain.url.service;


import dev.pluto.url_short.domain.url.dto.UrlCommandDto;
import dev.pluto.url_short.domain.url.entity.Url;
import dev.pluto.url_short.domain.url.repository.UrlRepository;
import dev.pluto.url_short.domain.url.utill.UrlEncoding;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

@Service
@RequiredArgsConstructor
@Slf4j
public class UrlShortCommandService {
    private final UrlRepository urlRepository;
    @Transactional
    public Long shortingUrl(UrlCommandDto dto) {

        String destinationUrl = dto.getDestinationUrl();
        // URL Get 요청 후 정상적으로 요청이 되는지 확인
        checkAvailUrl(destinationUrl);
        // 비밀번호 인코딩 로직 추가
        String encodedPassword = dto.getPassword();

        Url url = Url.create(destinationUrl,encodedPassword);

        urlRepository.save(url);

        url.setShortUrl(UrlEncoding.urlEncoder(url.getId()));

        return url.getId();
    }

    public void checkAvailUrl(String url){
        try{
            URL tempUrl  = new URL(url);
            HttpURLConnection connection = (HttpURLConnection)tempUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
        }catch(IOException e){
            throw new IllegalArgumentException();
        }
    }
}
