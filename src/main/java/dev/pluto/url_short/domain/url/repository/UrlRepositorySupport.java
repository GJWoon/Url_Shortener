package dev.pluto.url_short.domain.url.repository;


import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import dev.pluto.url_short.domain.url.dto.AccessLogDetailDto;
import dev.pluto.url_short.domain.url.dto.UrlDetailDto;
import dev.pluto.url_short.global.exception.BusinessException;
import dev.pluto.url_short.global.model.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;

import static dev.pluto.url_short.domain.url.entity.QUrl.url;
import static dev.pluto.url_short.domain.url.entity.QAccessLog.accessLog;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class UrlRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public UrlDetailDto findDetailUrlWithAccessLogByEncodedUrl(String encodedUrl) {



        List<UrlDetailDto> resultDto = queryFactory
                .from(url)
                .leftJoin(accessLog)
                .on(accessLog.url.id.eq(url.id))
                .where(url.shortUrl.eq(encodedUrl))
                .orderBy(accessLog.clickedAt.desc())
                .transform(
                        groupBy(url)
                                .list(
                                        Projections.constructor(
                                                UrlDetailDto.class,
                                                url.id,
                                                url.destinationUrl,
                                                url.shortUrl,
                                                url.createdAt,
                                                url.lastClickedAt,
                                                url.totalClickCount,
                                                list(
                                                        Projections.constructor(
                                                                AccessLogDetailDto.class,
                                                                accessLog.id,
                                                                accessLog.clickedAt,
                                                                accessLog.ip,
                                                                accessLog.userAgent,
                                                                accessLog.referrer
                                                        ).skipNulls()
                                                )
                                        )
                                )
                );
        if (resultDto.isEmpty()) {
            throw new BusinessException(ErrorCode.NOT_FOUND_URL);
        }
        return resultDto.get(0);
    }
}
