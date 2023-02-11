package home.medecine.config.security.exception;

import home.medecine.config.error.exception.BusinessException;
import home.medecine.config.error.exception.ErrorCode;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;

public class SecurityCustomException {

    public static class BadCredintail extends BusinessException {
        public BadCredintail(String message) { super(message, ErrorCode.BAD_CREDENTIALS);}
    }
    public static class UsernameNotFound extends  BusinessException{
        public UsernameNotFound(String message) { super(message, ErrorCode.MEMBER_NOT_FOUNT);}
    }
}
