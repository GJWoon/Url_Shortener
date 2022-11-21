package dev.pluto.url_short.global.dto;

import dev.pluto.url_short.global.model.HttpStatus;
import lombok.Getter;

@Getter
public class ApiResponse<T>{

    private int code;
    private String message;
    private T data;

    public ApiResponse(HttpStatus status,T data){
        this.code = status.getCode();
        this.message = status.getMessage();
        this.data = data;
    }
}
