package dev.pluto.url_short.domain.url.entity;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "t_access_log")
public class AccessLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ip;

    private String userAgent;

    private String referrer;

    private LocalDateTime clickedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "url_id")
    private Url url;


}
