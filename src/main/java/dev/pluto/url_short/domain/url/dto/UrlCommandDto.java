package dev.pluto.url_short.domain.url.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UrlCommandDto {

    private String destinationUrl;

    private String password;

}
