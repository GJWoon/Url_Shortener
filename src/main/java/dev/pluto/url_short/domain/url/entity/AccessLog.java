package dev.pluto.url_short.domain.url.entity;


import dev.pluto.url_short.domain.url.dto.AccessLogDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "t_access_log")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccessLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ip;

    private String userAgent;

    private String referrer;

    private LocalDateTime clickedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private Url url;
    public AccessLog create(AccessLogDto dto,Url url){
        return AccessLog.builder()
                .ip(dto.getUserIp())
                .clickedAt(LocalDateTime.now())
                .userAgent(dto.getUserAgent())
                .referrer(dto.getReferer())
                .url(url)
                .build();
    }

}
