package dev.pluto.url_short.domain.url.repository;

import dev.pluto.url_short.domain.url.entity.AccessLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessLogRepository extends JpaRepository<AccessLog,Long> {
}
