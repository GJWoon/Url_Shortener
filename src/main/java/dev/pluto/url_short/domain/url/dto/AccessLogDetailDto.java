package dev.pluto.url_short.domain.url.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class AccessLogDetailDto extends AccessLogDto{
    private final Long id;
    private final LocalDateTime createAt;

    public AccessLogDetailDto(Long id,LocalDateTime createAt,String userIp, String userAgent, String referer) {
        super(userIp, userAgent, referer);
        this.id =id;
        this.createAt=createAt;
    }
}
