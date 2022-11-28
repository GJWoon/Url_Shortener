# Url_Shortener

## https://june.pe.kr

<p align="center">
  <br>
<img width="1790" alt="image" src="https://user-images.githubusercontent.com/72774518/204198566-dda5e501-1114-4670-ad89-ece3b0774f76.png">

  <br>
</p>

목차

## 프로젝트 소개

<p align="justify">
https://bitly.com/ <br>
https://url.kr/ <br>

위 사이트와 같이 길고 못생긴 Url들을 짧고 이쁘게 만드는 Url_Shortener 사이트 입니다! <br>

ex) <br><br>
before - https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query=url+%EC%A4%84%EC%9D%B4%EA%B8%B0 <br>

after - https://june.pe.kr/n
</p>


<br>

## 기술 스택

| JavaScript | TypeScript |  React   |  Node   |
| :--------: | :--------: | :------: | :-----: |
|   ![js]    |   ![ts]    | ![react] | ![node] |

<br>

## 구현 기능
* ### URL Shorting

<img width="1701" alt="image" src="https://user-images.githubusercontent.com/72774518/204214503-02519dd4-1eb5-4861-892f-b385871b3a6a.png">

> base62를 사용하여 PK를 인코딩해서 Short Url로 사용
```java

    public static String encode(Long id) {
        StringBuilder shortURL = new StringBuilder("");
        while (id > 0) {
            shortURL.append(BASE62[(int) (id % 62)]);
            id /= 62;
        }
        return shortURL.reverse().toString();
    }


    urlRepository.save(url).setShortUrl(UrlEncoding.encode(url.getId()));

```
> 유효한 URL 검증

```java

    private void checkAvailUrl(String url) {
        try {
            final URL tempUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) tempUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
        } catch (IOException e) {
            throw new BusinessException(ErrorCode.NOT_EFFECTIVE_URL);
        }
    }
```

> Short Url 생성 성공시 JWT 생성 후 반환

```java

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
```


원본 URL - https://www.google.com/search?q=%EA%B9%83%ED%97%88%EB%B8%8C&oq=%EA%B9%83%ED%97%88%EB%B8%8C&aqs=chrome..69i57j46i131i199i433i465i512j0i131i433i512l3j0i512l2j69i60.2235j0j4&sourceid=chrome&ie=UTF-8

Short URL - https://june.pe.kr/u


<br>

* ### 비밀번호 검증 
<img width="454" alt="image" src="https://user-images.githubusercontent.com/72774518/204213267-3d980ceb-248e-48ea-abd0-2cbea4511862.png">

- 비밀번호는 생성한 URL의 Detail 페이지를 접근하는 용도

> Spring security의 PasswordEncoder 사용
```java
  public static void matchPw(String password, String hashPassword) {
        if (!getPasswordEncoder().matches(password, hashPassword)) {
            throw new BusinessException(ErrorCode.NOT_MATCH_PW);
        }
    }
```
- 비밀번호 일치시 JWT 생성 후 반환


* ### URL Detail Page

<img width="1674" alt="image" src="https://user-images.githubusercontent.com/72774518/204216240-82c1c0db-c37a-44fa-83b4-9c50bec65d3f.png">

> URL Detail Page Get API 요청시 Interceptor에서 요청한 Url의 권한이 있는지 권한 체크

```java

@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Map pathVariables = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

        String requestUrl = (String)pathVariables.get("url");

        String url  = tokenProvider.getUrl();

        if(!requestUrl.equals(url)){
            throw new BusinessException(ErrorCode.FORBIDDEN);
        }
       
       return HandlerInterceptor.super.preHandle(request, response, handler);
    }
```

> Url의 Detail info와 Accee_log를 조회
>> URL의 정보와 Acceelog를 Dto로 변환하여 한번에 가져오기 위한 Querydsl transform(),groupBy() 메서드 사용 
```java

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
```


<br>

## 추가 개발 사항

<p align="justify">
비밀번호 encoding solt 적용
</p>
<p align="justify">
회원 도메인 개발 <br>
  - 로그인 <br>
  - 회원가입 <br>
  - 내가 생성한 URL 조회 <br>
</p>

<br>
