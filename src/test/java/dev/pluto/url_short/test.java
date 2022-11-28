package dev.pluto.url_short;


import dev.pluto.url_short.domain.url.utill.UrlEncoding;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

public class test {

    @Autowired private UrlEncoding urlEncoding;

    @Test
    void test(){

        UrlEncoding urlEncoding1 = new UrlEncoding();

        for(int i =0; i< 100; i++){
            System.out.println(i + "  "+urlEncoding1.encode(Long.valueOf(i)));

        }

    }
}
