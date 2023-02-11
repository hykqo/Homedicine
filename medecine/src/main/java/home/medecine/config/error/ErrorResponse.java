package home.medecine.config.error;

import home.medecine.config.error.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
//ErrorResponse 객체 입니다.
// POJO 객체로 관리하면 errorResponse.getXXX(); 이렇게 명확하게 객체에 있는 값을 가져올 수 있습니다.
// 그 밖에 특정 Exception에 대해서 ErrorResponse 객체를 어떻게 만들 것인가에 대한 책임을 명확하게 갖는 구조로 설계할 수 있습니다.

@Slf4j
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponse {

    private String message;
    private int status;
    private List<FieldError> errors;
    private String code;

    private ErrorResponse(final ErrorCode code, final List<FieldError> errors){
        this.message = code.getMessage();
        this.status = code.getStatus();
        this.errors = errors;
        this.code = code.getCode();
    }

    private ErrorResponse(final ErrorCode code){
        this.message = code.getMessage();
        this.status = code.getStatus();
        this.code = code.getCode();
        this.errors = new ArrayList<>();
    }

    //BindException을 상속받고 있는 exceptionClass 일 경우 사용.
    public static ErrorResponse of(final ErrorCode code, final BindingResult bindingResult){
        log.info(code.toString());
        return new ErrorResponse(code, FieldError.of(bindingResult));
    }

    //FieldError로 넣을 정보가 없는 exceptionClass 일 경우 사용.
    public static ErrorResponse of(final ErrorCode code){
        log.info(code.toString());
        return new ErrorResponse(code);
    }

    public static ErrorResponse of(final ErrorCode code, final List<FieldError> fieldErrors){
        log.info(code.toString());
        return new ErrorResponse(code, fieldErrors);
    }

    //MethodArgumentTypeMismatchException 전용.
    public static ErrorResponse of(MethodArgumentTypeMismatchException e){
        final String value = e.getValue() == null ? "" : e.getValue().toString();
        final List<ErrorResponse.FieldError> errors = ErrorResponse.FieldError.of(e.getName(), value, e.getErrorCode());
        ErrorCode code = ErrorCode.INVALID_TYPE_VALUE;
        log.info(code.toString());
        return new ErrorResponse(code, errors);
    }


    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class FieldError{
        private String field;
        private String value;
        private String reason;

        private FieldError(String field, String value, String reason) {
            this.field = field;
            this.value = value;
            this.reason = reason;
        }

        public static List<FieldError> of(final String field, final String value, final String reason){
            List<FieldError> fieldErrors = new ArrayList<>();
            fieldErrors.add(new FieldError(field, value, reason));
            return fieldErrors;
        }

        private static List<FieldError> of(final BindingResult bindingResult){
            final List<org.springframework.validation.FieldError> fieldErrors = bindingResult.getFieldErrors();
            return fieldErrors.stream().map(
                    error -> new FieldError(
                            error.getField(),
                            error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
                            error.getDefaultMessage()
                    )
            ).collect(Collectors.toList());
        }
    }
}
