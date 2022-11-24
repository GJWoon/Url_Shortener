package dev.pluto.url_short.domain.url.utill;


import dev.pluto.url_short.global.exception.BusinessException;
import dev.pluto.url_short.global.model.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PasswordEncoding {


    //TODO: 남으면 비밀번호 암호화 적용
    private final PasswordEncoder passwordEncoder;

    public String pwEncode(String password) {
        return passwordEncoder.encode(password);
    }

    public void matchPw(String password, String hashPassword) {
        if (!passwordEncoder.matches(password, hashPassword)) {
            throw new BusinessException(ErrorCode.NOT_MATCH_PW);
        }
    }
}
