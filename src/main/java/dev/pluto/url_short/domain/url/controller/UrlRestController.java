package dev.pluto.url_short.domain.url.controller;


import dev.pluto.url_short.domain.url.dto.UrlCommandDto;
import dev.pluto.url_short.domain.url.dto.UrlDetailDto;
import dev.pluto.url_short.domain.url.dto.UrlAndTokenDto;
import dev.pluto.url_short.domain.url.service.facade.UrlFacadeService;
import dev.pluto.url_short.global.dto.ApiResponse;
import dev.pluto.url_short.global.model.HttpStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UrlRestController {
    private final UrlFacadeService urlFacadeService;
    @PostMapping()
    public ApiResponse<UrlAndTokenDto> encoding(@RequestBody @Valid UrlCommandDto dto) {
        return new ApiResponse<>(HttpStatus.SUCCESS, urlFacadeService.shortingUrl(dto));
    }
    @GetMapping("/{url}/detail")
    public ApiResponse<UrlDetailDto> getUrlDetail(@PathVariable String url) {
        return new ApiResponse<>(HttpStatus.SUCCESS, urlFacadeService.findUrlWithAccessLogById(url));
    }

}
