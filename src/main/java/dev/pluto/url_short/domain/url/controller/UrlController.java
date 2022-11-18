package dev.pluto.url_short.domain.url.controller;


import dev.pluto.url_short.domain.url.dto.UrlCommandDto;
import dev.pluto.url_short.domain.url.service.UrlShortCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UrlController {

    private final UrlShortCommandService urlShortCommandService;
    @GetMapping("/")
    public Long encoding(@RequestParam String url,@RequestParam String password){
        return urlShortCommandService.shortingUrl(new UrlCommandDto(url,password
        ));
    }
}
