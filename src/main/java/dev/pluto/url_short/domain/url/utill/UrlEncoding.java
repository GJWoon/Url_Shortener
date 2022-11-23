package dev.pluto.url_short.domain.url.utill;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UrlEncoding {

    private final static int BASE = 62;
    private final static String BASE_CHAR = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static StringBuilder encoding(long param) {
        StringBuilder sb = new StringBuilder();
        while(param > 0) {
            sb.append(BASE_CHAR.charAt((int) (param % BASE)));
            param /= BASE;
        }
        return sb;
    }

    private static long decoding(String param) {
        long sum = 0;
        long power = 1;
        for (int i = 0; i < param.length(); i++) {
            sum += BASE_CHAR.indexOf(param.charAt(i)) * power;
            power *= BASE;
        }
        return sum;
    }

    //PK를 인코딩
    public static String urlEncoder(Long urlId){
        return String.valueOf(encoding(urlId));
    }
    //디코딩
    public static long urlDecoder(String encodeStr){
        return decoding(encodeStr);
    }

}
