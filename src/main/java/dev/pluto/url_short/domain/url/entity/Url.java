package dev.pluto.url_short.domain.url.entity;


import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_url")
@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Cacheable
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String destinationUrl;
    private String shortUrl;

    private LocalDateTime createdAt;
    private LocalDateTime lastClickedAt;

    private String password;
    private long totalClickCount;

//    @OneToMany(mappedBy = "urlId",fetch = FetchType.LAZY)
//    private final List<AccessLog> accessLogList = new ArrayList<>();


    // URL 생성
    public static Url create(String destinationUrl, String password) {

        return Url.builder()
                .destinationUrl(destinationUrl)
                .password(password)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public void clickUrl() {
        this.lastClickedAt = LocalDateTime.now();
        this.totalClickCount++;
    }

    public void setShortUrl(String encodingUrl) {
        this.shortUrl = encodingUrl;
    }
}
