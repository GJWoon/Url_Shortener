package dev.pluto.url_short.domain.url.controller;


import dev.pluto.url_short.domain.url.dto.UrlCommandDto;
import dev.pluto.url_short.domain.url.dto.UrlDetailDto;
import dev.pluto.url_short.domain.url.dto.UrlAndTokenDto;
import dev.pluto.url_short.domain.url.service.UrlQueryService;
import dev.pluto.url_short.domain.url.service.UrlShortCommandService;
import dev.pluto.url_short.global.dto.ApiResponse;
import dev.pluto.url_short.global.model.HttpStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UrlRestController {

    private final UrlQueryService urlQueryService;
    private final UrlShortCommandService urlShortCommandService;
    @PostMapping()
    public ApiResponse<UrlAndTokenDto> encoding(@RequestBody @Valid UrlCommandDto dto) {
        return new ApiResponse<>(HttpStatus.CREATE, urlShortCommandService.shortingUrl(dto));
    }
    @GetMapping("/{url}/detail")
    public ApiResponse<UrlDetailDto> getUrlDetail(@PathVariable String url) {
        return new ApiResponse<>(HttpStatus.SUCCESS, urlQueryService.findUrlWithAccessLogById(url));
    }
}
