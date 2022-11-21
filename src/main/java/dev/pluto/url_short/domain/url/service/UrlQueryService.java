package dev.pluto.url_short.domain.url.service;


import dev.pluto.url_short.domain.url.dto.UrlDetailDto;
import dev.pluto.url_short.domain.url.entity.Url;
import dev.pluto.url_short.domain.url.repository.UrlRepository;
import dev.pluto.url_short.domain.url.repository.UrlRepositorySupport;
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
    protected Url findById(Long urlId){
        return urlRepository.findById(urlId)
                .orElseThrow(NoSuchElementException::new);
    }
    protected UrlDetailDto findUrlWithAccessLogByEncodedUrl(String encodedUrl){
        return urlRepositorySupport.findDetailUrlWithAccessLogByEncodedUrl(
                encodedUrl);
    }

}
