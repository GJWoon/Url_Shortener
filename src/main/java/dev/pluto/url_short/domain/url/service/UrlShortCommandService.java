package dev.pluto.url_short.domain.url.service;


import dev.pluto.url_short.domain.url.dto.UrlCommandDto;
import dev.pluto.url_short.domain.url.entity.Url;
import dev.pluto.url_short.domain.url.repository.UrlRepository;
import dev.pluto.url_short.domain.url.utill.UrlEncoding;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
@Slf4j
public class UrlShortCommandService {


    private final UrlRepository urlRepository;


    @Transactional
    public Long shortingUrl(UrlCommandDto dto) {

        // 비밀번호 인코딩 로직 추가
        String encodedPassword = dto.getPassword();

        String destinationUrl = dto.getDestinationUrl();

        Url url = Url.create(dto.getDestinationUrl(),encodedPassword);

        url.setShortUrl(UrlEncoding.urlEncoder(url.getId()));

        urlRepository.save(url);


        return url.getId();
    }

}
