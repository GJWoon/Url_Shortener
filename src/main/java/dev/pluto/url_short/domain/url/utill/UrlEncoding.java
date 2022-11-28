package dev.pluto.url_short.domain.url.utill;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UrlEncoding {
    private static final char[] BASE62 = "abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    public static String encode(Long id) {
        StringBuilder shortURL = new StringBuilder("");
        while (id > 0) {
            shortURL.append(BASE62[(int) (id % 62)]);
            id /= 62;
        }
        return shortURL.reverse().toString();
    }

    public static Long decode(String str) {
        Long id = 0L;

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if ('a' <= ch && ch <= 'z') {
                id = id * 62 + ch - 'a';
            } else if ('A' <= ch && ch <= 'Z') {
                id = id * 62 + ch - 'A' + 36;
            } else if ('0' <= ch && ch <= '9') {
                id = id * 62 + ch - '0' + 26;
            }
        }
        return id;
    }

}


