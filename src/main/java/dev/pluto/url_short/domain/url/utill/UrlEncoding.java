package dev.pluto.url_short.domain.url.utill;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;

@Component
@Slf4j
public class UrlEncoding {

    private final static String URL_PREFIX = "http://test.com/";
    private final static int BASE = 62;
    private final static String BASE_CHAR = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";


    private static String encoding(long param) {
        StringBuilder sb = new StringBuilder();
        while(param > 0) {
            sb.append(BASE_CHAR.charAt((int) (param % BASE)));
            param /= BASE;
        }
        return URL_PREFIX + sb;
    }

    private long decoding(String param) {
        long sum = 0;
        long power = 1;
        for (int i = 0; i < param.length(); i++) {
            sum += BASE_CHAR.indexOf(param.charAt(i)) * power;
            power *= BASE;
        }
        return sum;
    }

    //신퀀스를 인코딩
    public static String urlEncoder(Long urlId){
        String encodeStr = encoding(urlId);
        log.info("base62 encode result:" + encodeStr);
        return encodeStr;
    }

    //디코딩
    public long urlDecoder(String encodeStr) throws NoSuchAlgorithmException {
        if(encodeStr.trim().startsWith(URL_PREFIX)){
            encodeStr = encodeStr.replace(URL_PREFIX, "");
        }
        long decodeVal = decoding(encodeStr);
        log.info("base62 decode result:" + decodeVal);
        return decodeVal;
    }

}
