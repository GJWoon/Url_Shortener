package dev.pluto.url_short.domain.url.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class UrlDetailDto {

    private Long id;

    private String destinationUrl;

    private String shortUrl;

    private LocalDateTime createdAt;

    private LocalDateTime lastClickedAt;

    private long totalClickCount;

    private List<AccessLogDetailDto> accessLogDetailDtoList;
}
