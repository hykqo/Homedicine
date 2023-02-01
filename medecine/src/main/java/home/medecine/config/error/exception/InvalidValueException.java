package home.medecine.config.error.exception;
/**
 * 유효하지 않은 값일 경우 예외를 던지는 Excetion
 * ex) 쿠폰 만료, 이미 사용한 쿠폰 등의 이유로 더이상 진행이 못할경우
 * */
public class InvalidValueException extends BusinessException{

    public InvalidValueException(String message) {
        super(message, ErrorCode.INVALID_INPUT_VALUE);
    }

    public InvalidValueException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
