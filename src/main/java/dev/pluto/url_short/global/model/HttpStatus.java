package dev.pluto.url_short.global.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor
public enum HttpStatus {


    SUCCESS(200, "Success");

    private int code;
    private String message;

    HttpStatus(int code, String message) {
        this.code =code;
        this.message = message;
    }
}
