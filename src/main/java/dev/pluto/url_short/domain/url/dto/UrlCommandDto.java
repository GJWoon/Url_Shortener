package dev.pluto.url_short.domain.url.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UrlCommandDto {

    @NotBlank(message = "URL을 입력해 주세요")
    private String url;
    @NotBlank(message = "비밀번호를 입력해 주세요")
    private String password;
}
