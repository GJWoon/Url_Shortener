package dev.pluto.url_short.domain.url.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AccessLogDto {
    private final String userIp;
    private final String userAgent;
    private final String referer;
}
