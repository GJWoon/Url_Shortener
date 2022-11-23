package dev.pluto.url_short.domain.url.service;


import dev.pluto.url_short.domain.url.dto.UrlDetailDto;
import dev.pluto.url_short.domain.url.entity.Url;
import dev.pluto.url_short.domain.url.repository.UrlRepository;
import dev.pluto.url_short.domain.url.repository.UrlRepositorySupport;
import dev.pluto.url_short.domain.url.utill.UrlEncoding;
import dev.pluto.url_short.global.exception.BusinessException;
import dev.pluto.url_short.global.model.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UrlQueryService {
    private final UrlRepository urlRepository;
    private final UrlRepositorySupport urlRepositorySupport;
    public Url findByEncodedUrl(String url){
        return urlRepository.findById(UrlEncoding.urlDecoder(url))
                .orElseThrow(()-> new BusinessException(ErrorCode.NOT_FOUND_URL));
    }
    public UrlDetailDto findUrlWithAccessLogByEncodedUrl(String encodedUrl){
        return urlRepositorySupport.findDetailUrlWithAccessLogByEncodedUrl(encodedUrl);
    }

}
