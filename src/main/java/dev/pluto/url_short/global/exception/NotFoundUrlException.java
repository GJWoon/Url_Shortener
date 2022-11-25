package dev.pluto.url_short.global.exception;

import dev.pluto.url_short.global.model.ErrorCode;

public class NotFoundUrlException extends BusinessException{

    public NotFoundUrlException(ErrorCode errorCode) {
        super(errorCode);
    }
}
