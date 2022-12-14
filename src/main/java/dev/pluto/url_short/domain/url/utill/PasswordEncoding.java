package dev.pluto.url_short.domain.url.utill;


import dev.pluto.url_short.global.exception.BusinessException;
import dev.pluto.url_short.global.model.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PasswordEncoding {

    //TODO: 시간 남으면 비밀번호 암호화 적용
    @Bean
    public static PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    };
    public static String pwEncode(String password) {

        return getPasswordEncoder().encode(password);
    }
    public static void matchPw(String password, String hashPassword) {
        if (!getPasswordEncoder().matches(password, hashPassword)) {
            throw new BusinessException(ErrorCode.NOT_MATCH_PW);
        }
    }
}
