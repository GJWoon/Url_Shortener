package dev.pluto.url_short.domain.url.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class UrlDetailDto {

    private final Long id;

    private final String destinationUrl;

    private final String shortUrl;

    private final LocalDateTime createdAt;

    private final LocalDateTime lastClickedAt;

    private final  long totalClickCount;

    private final List<AccessLogDetailDto> accessLogDetailDtoList;
}
