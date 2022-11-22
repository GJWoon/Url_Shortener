package dev.pluto.url_short.domain.url.controller;


import dev.pluto.url_short.domain.url.dto.UrlCommandDto;
import dev.pluto.url_short.domain.url.dto.UrlDetailDto;
import dev.pluto.url_short.domain.url.dto.UrlReturnDto;
import dev.pluto.url_short.domain.url.service.facade.UrlFacadeService;
import dev.pluto.url_short.global.dto.ApiResponse;
import dev.pluto.url_short.global.model.HttpStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UrlRestController {

    private final UrlFacadeService urlFacadeService;

    @PostMapping()
    public ApiResponse<UrlReturnDto> encoding(@RequestBody UrlCommandDto dto) {
        return new ApiResponse<>(HttpStatus.SUCCESS,
                new UrlReturnDto(urlFacadeService.shortingUrl(dto)));
    }

    @GetMapping("/{url}/detail")
    public ApiResponse<UrlDetailDto> getUrlDetail(@PathVariable String url, HttpServletRequest request) {


        //TODO : 인터셉터로 옮기기
        String sessionUrl = request.getAttribute("sUrl").toString();

        if (sessionUrl == null || !sessionUrl.equals(url)) {
            throw new RuntimeException();
        }

        return new ApiResponse<>(HttpStatus.SUCCESS,
                urlFacadeService.findUrlWithAccessLogById(url));
    }

    @GetMapping("/auth/pw")
    public ApiResponse<Boolean> checkPassword(@RequestBody UrlCommandDto dto, HttpServletRequest request) {

        request.setAttribute("sUrl", urlFacadeService.checkPw(dto));

        return new ApiResponse<>(HttpStatus.SUCCESS, true);

    }
}
