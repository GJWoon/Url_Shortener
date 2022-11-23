package dev.pluto.url_short.domain.url.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UrlAndTokenDto {
    private final String url;
    private final String token;
}
