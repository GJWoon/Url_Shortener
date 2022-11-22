package dev.pluto.url_short.domain.url.controller;

import dev.pluto.url_short.domain.url.service.facade.UrlFacadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequiredArgsConstructor
public class UrlViewController {

    private final UrlFacadeService urlFacadeService;
    @GetMapping(value = {"/{url}"})
    public String redirectUrl(@PathVariable() String url, HttpServletRequest httpServletRequest) {
        return "redirect:"+urlFacadeService.getDestination_url(url,httpServletRequest);
     }
}
