package dev.pluto.url_short.domain.url.controller;

import dev.pluto.url_short.domain.url.service.UrlFacadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequiredArgsConstructor
public class UrlViewController {

    private final UrlFacadeService urlFacadeService;

    @GetMapping("/{url}")
    public String redirectUrl(@PathVariable String url, HttpServletRequest httpServletRequest) {
        return "redirect:"+urlFacadeService.getDestination_url(url,httpServletRequest);
        // return new RedirectView(urlFacadeService.getDestination_url(url,httpServletRequest));
    }

}
