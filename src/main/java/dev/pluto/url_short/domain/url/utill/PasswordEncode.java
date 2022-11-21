package dev.pluto.url_short.domain.url.utill;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;
import java.util.Locale;


public class PasswordEncode {
    String prefix =         String.valueOf(BCryptPasswordEncoder.BCryptVersion.$2B).toLowerCase(Locale.ROOT);
    int log_rounds =15;


    public static String gensalt(String prefix, int log_rounds, SecureRandom random) throws IllegalArgumentException {
        String.valueOf(BCryptPasswordEncoder.BCryptVersion.$2B).toLowerCase(Locale.ROOT);


        StringBuilder rs = new StringBuilder();
        byte rnd[] = new byte[BCRYPT_SALT_LEN]; // 16byte(128bit) 크기의 Salt 생성

        if (!prefix.startsWith("$2") || (prefix.charAt(2) != 'a' && prefix.charAt(2) != 'y' && prefix.charAt(2) != 'b')) {
            throw new IllegalArgumentException("Invalid prefix");
        }

        if (log_rounds < 4 || log_rounds > 31) {
            throw new IllegalArgumentException("Invalid log_rounds");
        }

        random.nextBytes(rnd);

        rs.append("$2");
        rs.append(prefix.charAt(2));
        rs.append("$");
        if (log_rounds < 10) rs.append("0");

        rs.append(log_rounds);
        rs.append("$");
        encode_base64(rnd, rnd.length, rs);

        return rs.toString();
    }
}
