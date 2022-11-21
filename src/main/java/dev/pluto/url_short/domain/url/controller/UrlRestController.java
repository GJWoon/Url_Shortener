package dev.pluto.url_short.domain.url.controller;


import dev.pluto.url_short.domain.url.dto.UrlCommandDto;
import dev.pluto.url_short.domain.url.dto.UrlDetailDto;
import dev.pluto.url_short.domain.url.service.UrlFacadeService;
import dev.pluto.url_short.domain.url.service.UrlShortCommandService;
import dev.pluto.url_short.global.dto.ApiResponse;
import dev.pluto.url_short.global.model.HttpStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

@RestController
@RequiredArgsConstructor
public class UrlRestController {

    private final UrlShortCommandService urlShortCommandService;
    private final UrlFacadeService urlFacadeService;

    @GetMapping()
    public Long encoding(@RequestParam String url, @RequestParam String password) {
        return urlShortCommandService.shortingUrl(new UrlCommandDto(url, password
        ));
    }

    @GetMapping("/{url}/detail")
    public ApiResponse<UrlDetailDto> getUrlDetail(@PathVariable String url) {
        return new ApiResponse<>(HttpStatus.SUCCESS, urlFacadeService.findUrlWithAccessLogById(url));
    }

}
