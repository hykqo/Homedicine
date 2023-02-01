package home.medecine.config.error.exception;

public enum ErrorCode {

    //Common
    INVALID_INPUT_VALUE(400, "C001", " Invalid Input Value"),
    METHOD_NOT_ALLOWED(405, "C002", "Invalid Input Value"),
    ENTITY_NOT_FOUND(400, "C003", " Entity Not Found"),
    INTERNAL_SERVER_ERROR(500, "C004", "Server Error"),
    INVALID_TYPE_VALUE(400, "C005", " Invalid Type Value"),
    HANDLE_ACCESS_DENIED(403, "C006", "Access is Denied"),

    //Member
    EMAIL_DUPLICATION(400, "M001", "이미 사용중인 이메일입니다."),
    ID_DUPLICATION(400, "M002", "이미 사용중인 아이디입니다."),
    PHONE_DUPLICATION(400, "M003", "이미 사용중인 번호입니다."),
    LOGIN_INPUT_INVALID(400, "M004", "Login input is invalid"),
    PHONE_INPUT_INVALID(400, "M005", "Phone input is invalid")


    ;
    private final String code;
    private final String message;
    private int status;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }
}
