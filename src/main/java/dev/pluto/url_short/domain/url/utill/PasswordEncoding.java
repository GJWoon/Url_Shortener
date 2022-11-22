package dev.pluto.url_short.domain.url.utill;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PasswordEncoding {

    private final PasswordEncoder passwordEncoder;

    public String pwEncode(String password) {

        return passwordEncoder.encode(password);

    }

    public void matchPw(String password, String hashPassword) {

        if (!passwordEncoder.matches(password, hashPassword)) {

            throw new RuntimeException();
        }

    }
}
