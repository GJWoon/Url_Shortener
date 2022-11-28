# Url_Shortener

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
>URL Shorting

<img width="1725" alt="image" src="https://user-images.githubusercontent.com/72774518/204212090-739a7bbc-f8f6-41c5-88bb-033b7f14cfab.png">

* base62를 사용하여 PK를 인코딩해서 Short Url로 사용
```groovy

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
원본 URL - https://www.google.com/search?q=%EA%B9%83%ED%97%88%EB%B8%8C&oq=%EA%B9%83%ED%97%88%EB%B8%8C&aqs=chrome..69i57j46i131i199i433i465i512j0i131i433i512l3j0i512l2j69i60.2235j0j4&sourceid=chrome&ie=UTF-8

Short URL - https://june.pe.kr/u

### 기능 2

### 기능 3

### 기능 4

<br>

## 배운 점 & 아쉬운 점

<p align="justify">

</p>

<br>

## 라이센스

MIT &copy; [NoHack](mailto:lbjp114@gmail.com)

<!-- Stack Icon Refernces -->

[js]: /images/stack/javascript.svg
[ts]: /images/stack/typescript.svg
[react]: /images/stack/react.svg
[node]: /images/stack/node.sv
