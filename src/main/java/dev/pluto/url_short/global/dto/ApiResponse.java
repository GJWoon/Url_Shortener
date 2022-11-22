package dev.pluto.url_short.global.dto;

import dev.pluto.url_short.global.model.HttpStatus;
import lombok.Getter;

import java.util.Map;

@Getter
public class ApiResponse<T>{

    private final int code;
    private final String message;
    private final T data;

    public ApiResponse(HttpStatus status,T data){
        this.code = status.getCode();
        this.message = status.getMessage();
        this.data = data;
    }
}
