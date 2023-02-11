package home.medecine.config.error.exception;

public enum ErrorCode {

    /** Common */
    INVALID_INPUT_VALUE(400, "C01", " Invalid Input Value"),
    METHOD_NOT_ALLOWED(405, "C02", "Invalid Input Value"),
    ENTITY_NOT_FOUND(400, "C03", " Entity Not Found"),
    INTERNAL_SERVER_ERROR(500, "C04", "Server Error"),
    INVALID_TYPE_VALUE(400, "C05", " Invalid Type Value"),

    /** Auth */
    HANDLE_ACCESS_DENIED(401, "S06", "알수 없는 이유로 로그인에 실패하였습니다. 관리자에게 문의하세요."),
    BAD_CREDENTIALS(400, "S06-1", "비밀번호가 맞지 않습니다."),
    ACCOUNT_EXPIRED(400, "S06-2", "만료된 계정입니다."),
    CREDENTIAL_EXPIRED(400, "S06-3", "비밀번호가 만료되었습니다."),
    ACCOUNT_DISABLED(400, "S06-4", "비활성화된 계정입니다."),
    ACCOUNT_LOCKED(400, "S06-5", "잠긴 계정입니다."),

    /** Member */
    //join
    EMAIL_DUPLICATION(400, "M001", "이미 사용중인 이메일입니다."),
    ID_DUPLICATION(400, "M002", "이미 사용중인 아이디입니다."),
    PHONE_DUPLICATION(400, "M003", "이미 사용중인 번호입니다."),
    LOGIN_INPUT_INVALID(400, "M004", "Login input is invalid"),
    PHONE_INPUT_INVALID(400, "M005", "Phone input is invalid"),
    //find
    MEMBER_NOT_FOUNT(400, "M006", "일치하는 계정을 찾을 수 없습니다."),


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

    @Override
    public String toString() {
        return "ErrorCode{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", status=" + status +
                '}';
    }
}
