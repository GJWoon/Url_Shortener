package dev.pluto.url_short.domain.url.repository;

import dev.pluto.url_short.domain.url.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<Url,Long> {
}
