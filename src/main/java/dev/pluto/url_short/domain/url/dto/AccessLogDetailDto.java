package dev.pluto.url_short.domain.url.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AccessLogDetailDto extends AccessLogDto{

    private Long id;

    private LocalDateTime createAt;

    public AccessLogDetailDto(Long id,LocalDateTime createAt,String userIp, String userAgent, String referer) {
        super(userIp, userAgent, referer);
        this.id =id;
        this.createAt=createAt;
    }
}
