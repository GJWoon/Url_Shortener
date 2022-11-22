package dev.pluto.url_short.domain.url.service;


import dev.pluto.url_short.domain.url.dto.UrlDetailDto;
import dev.pluto.url_short.domain.url.entity.Url;
import dev.pluto.url_short.domain.url.repository.UrlRepository;
import dev.pluto.url_short.domain.url.repository.UrlRepositorySupport;
import dev.pluto.url_short.domain.url.utill.UrlEncoding;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
public class UrlQueryService {
    private final UrlRepository urlRepository;
    private final UrlRepositorySupport urlRepositorySupport;
    public Url findByEncodedUrl(String url){
        return urlRepository.findById(UrlEncoding.urlDecoder(url))
                .orElseThrow(NoSuchElementException::new);
    }
    public UrlDetailDto findUrlWithAccessLogByEncodedUrl(String encodedUrl){
        return urlRepositorySupport.findDetailUrlWithAccessLogByEncodedUrl(
                encodedUrl);
    }

}
