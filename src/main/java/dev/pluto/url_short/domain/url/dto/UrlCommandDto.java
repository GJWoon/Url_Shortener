package dev.pluto.url_short.domain.url.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UrlCommandDto {
    private String url;
    private String password;
}
