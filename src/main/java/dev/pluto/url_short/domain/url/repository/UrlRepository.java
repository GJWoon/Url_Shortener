package dev.pluto.url_short.domain.url.repository;

import dev.pluto.url_short.domain.url.dto.UrlDetailDto;
import dev.pluto.url_short.domain.url.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UrlRepository extends JpaRepository<Url, Long> {
//
//    @Query(
//           value = "select " +
//                    "url.id,\n" +
//                    "url.destinationUrl,\n" +
//                    "url.shortUrl,\n" +
//                    "url.createdAt,\n" +
//                    "url.lastClickedAt,\n" +
//                    "url.totalClickCount,"+
//                   "accessLog.id,\n" +
//                   "accessLog.clickedAt,\n" +
//                   "accessLog.ip,\n" +
//                   "accessLog.userAgent,\n" +
//                   "accessLog.referrer\n" +
//                   "from url \n" +
//                   "left join accesslog \n" +
//                   "on accesslog.urlid = url.id \n" +
//                   "where url.shortUrl = :url"
//    )
//     UrlDetailDto findByIdw(@Param("url") String url);
}
