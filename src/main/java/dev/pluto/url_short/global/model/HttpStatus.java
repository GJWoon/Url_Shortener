package dev.pluto.url_short.global.model;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum HttpStatus {
    SUCCESS(200, "Success"),
    CREATE(201, "Create Success");
    private int code;
    private String message;

    HttpStatus(int code, String message) {
        this.code =code;
        this.message = message;
    }
}
