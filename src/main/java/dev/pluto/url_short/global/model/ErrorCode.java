package dev.pluto.url_short.global.model;

public enum ErrorCode {

    NOT_FOUND_URL(404, "C004", "URL을 찾을 수 없습니다."),

    NOT_MATCH_PW(403, "C004", "비밀번호를 확인해 주세요."),


    NOT_EFFECTIVE_URL(409, "C004", "유효한 URL이 아닙니다."),
    FORBIDDEN(403, "C004", "권한이 없습니다."),

    INTERNAL_SERVER_ERROR(500, "C004", "알 수 없는 에러 (서버 에러)"),

    ;

    private final int status;
    private final String code;
    private final String message;

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }
    public String getMessage() {
        return this.message;
    }

    public String getCode() {
        return code;
    }

    public int getStatus() {
        return status;
    }

}
