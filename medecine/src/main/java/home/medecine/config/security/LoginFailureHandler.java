package home.medecine.config.security;

import home.medecine.config.error.ErrorResponse;
import home.medecine.config.error.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class LoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {
        String username = request.getParameter("username");
        //실패 로직 분류
        ResponseEntity<ErrorResponse> errorResp = HandleLoginException(exception);
    }

    public ResponseEntity<ErrorResponse> HandleLoginException(AuthenticationException e){
        /**
         * Authentication 비밀번호 불일치
         */
        if(e instanceof BadCredentialsException) {
            log.error("handleBadCredentialsException", e);
            final ErrorResponse response = ErrorResponse.of(ErrorCode.BAD_CREDENTIALS);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        /**
         * Authentication 계정 없음.
         */
        else if(e instanceof UsernameNotFoundException){
            log.error("handleUsernameNotFoundException", e);
            final ErrorResponse response = ErrorResponse.of(ErrorCode.MEMBER_NOT_FOUNT);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        /**
         * Authentication 계정 만료.
         */
        else if(e instanceof AccountExpiredException){
            log.error("handleAccountExpiredException", e);
            final ErrorResponse response = ErrorResponse.of(ErrorCode.ACCOUNT_EXPIRED);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        /**
         * Authentication 비밀번호 만료.
         */
        else if(e instanceof CredentialsExpiredException){
            log.error("handleCredentialsExpiredException", e);
            final ErrorResponse response = ErrorResponse.of(ErrorCode.CREDENTIAL_EXPIRED);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        /**
         * Authentication 계정 비활성화.
         */
        else if(e instanceof DisabledException){
            log.error("handleDisabledException", e);
            final ErrorResponse response = ErrorResponse.of(ErrorCode.ACCOUNT_DISABLED);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        /**
         * Authentication 계정 잠김.
         */
        else if(e instanceof LockedException){
            log.error("handleLockedException", e);
            final ErrorResponse response = ErrorResponse.of(ErrorCode.ACCOUNT_LOCKED);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        else {
            log.error("handleAccessDeniedException", e);
            final ErrorResponse response = ErrorResponse.of(ErrorCode.HANDLE_ACCESS_DENIED);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
    }
}