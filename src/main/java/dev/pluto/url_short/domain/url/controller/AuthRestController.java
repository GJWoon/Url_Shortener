package dev.pluto.url_short.domain.url.controller;


import dev.pluto.url_short.domain.url.dto.UrlAndTokenDto;
import dev.pluto.url_short.domain.url.dto.UrlCommandDto;
import dev.pluto.url_short.domain.url.service.facade.UrlFacadeService;
import dev.pluto.url_short.global.dto.ApiResponse;
import dev.pluto.url_short.global.model.HttpStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthRestController {

    private final UrlFacadeService urlFacadeService;

    @PostMapping("/auth/pw")
    public ApiResponse<UrlAndTokenDto> checkPassword(@RequestBody @Valid UrlCommandDto dto) {
        return new ApiResponse<>(HttpStatus.SUCCESS, urlFacadeService.checkPw(dto) );
    }
    @GetMapping("/auth/token/{url}")
    public ApiResponse<Boolean> checkPassword(@PathVariable String url) {
        return new ApiResponse<>(HttpStatus.SUCCESS,urlFacadeService.checkToken(url));

    }
}
