package dev.pluto.url_short.domain.url.entity;


import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "t_url")
@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 2083,nullable = false)
    private String destinationUrl;
    private String shortUrl;

    private LocalDateTime createdAt;
    private LocalDateTime lastClickedAt;

    @Column(nullable = false)
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
