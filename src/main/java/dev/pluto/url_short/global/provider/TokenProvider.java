package dev.pluto.url_short.global.provider;


import dev.pluto.url_short.domain.url.dto.UrlAndTokenDto;
import dev.pluto.url_short.global.exception.BusinessException;
import dev.pluto.url_short.global.model.ErrorCode;
import dev.pluto.url_short.global.model.HttpStatus;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class TokenProvider {

    @Value("spring.jwt.secret")
    private String secretKey;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createJwt(String url) {
        Date now = new Date();
        return Jwts.builder()
                .setHeaderParam("type", "jwt")
                .claim("url", url)
                .setIssuedAt(now)
                .setExpiration(new Date(System.currentTimeMillis() + (1000L * 60 * 60 * 24 * 365)))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }


    public String getJwt(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        return request.getHeader(HttpHeaders.AUTHORIZATION);
    }

    public String getUrl(){
        // 헤더에서 JWT 추출
        String accessToken = getJwt();

        if(accessToken == null || accessToken.length() == 0){
            throw new BusinessException(ErrorCode.FORBIDDEN);
        }
        // JWT 파싱
        Jws<Claims> claims;

        try{
            claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(accessToken);
        } catch (Exception ignored) {
            throw new BusinessException(ErrorCode.FORBIDDEN);
        }
        // id 추출
        return claims.getBody().get("url",String.class);
    }

    public UrlAndTokenDto createJwtDto(String url){
        return new UrlAndTokenDto(url,createJwt(url));
    }

}
