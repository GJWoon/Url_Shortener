package dev.pluto.url_short.domain.url.controller;

import dev.pluto.url_short.domain.url.service.UrlQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequiredArgsConstructor
public class UrlViewController {

    private final UrlQueryService urlQueryService;
    @GetMapping(value = {"/{url}"})
    public String redirectUrl(@PathVariable() String url, HttpServletRequest httpServletRequest) {
        return "redirect:"+urlQueryService.getDestination_url(url,httpServletRequest);
     }
}
