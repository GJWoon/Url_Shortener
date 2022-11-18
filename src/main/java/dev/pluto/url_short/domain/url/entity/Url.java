package dev.pluto.url_short.domain.url.entity;


import dev.pluto.url_short.domain.url.utill.UrlEncoding;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "t_url")
@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    private String destinationUrl;

    private String shortUrl;

    private LocalDateTime createdAt;

    private  LocalDateTime lastClickedAt;

    private String password;

    private long totalClickCount;






    // URL 생성
    public static Url create(String destinationUrl, String password){

        return Url.builder()
                .destinationUrl(destinationUrl)
                .password(password)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public void setShortUrl(String encodingUrl){
        this.shortUrl = encodingUrl;
    }
}
