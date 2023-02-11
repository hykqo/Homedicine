package home.medecine.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import home.medecine.config.error.ErrorResponse;
import home.medecine.config.error.exception.ErrorCode;
import home.medecine.config.security.service.FailureService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Primary
@Slf4j
@RequiredArgsConstructor
public class LoginFailureHandler implements AuthenticationFailureHandler {

    private ObjectMapper objectMapper = new ObjectMapper();
    private final FailureService failureService;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {
        String username = request.getParameter("username");
        ErrorResponse errorResponse = HandleLoginException(exception, username);
        formatServletResponse(response, errorResponse).flushBuffer();
    }

    //실패 로직 분류
    public ErrorResponse HandleLoginException(AuthenticationException e,String username){
        /**
         * Authentication 비밀번호 불일치
         */
        if(e instanceof BadCredentialsException) {
            log.error("handleBadCredentialsException", e);
            return failureService.handleBadCredentials(username);
        }
        /**
         * Authentication 계정 없음.
         */
        else if(e instanceof UsernameNotFoundException){
            log.error("handleUsernameNotFoundExceptieon", e);
            return ErrorResponse.of(ErrorCode.MEMBER_NOT_FOUNT);
        }
        /**
         * Authentication 계정 만료.
         */
        else if(e instanceof AccountExpiredException){
            log.error("handleAccountExpiredException", e);
            return ErrorResponse.of(ErrorCode.ACCOUNT_EXPIRED);
        }
        /**
         * Authentication 비밀번호 만료.
         */
        else if(e instanceof CredentialsExpiredException){
            log.error("handleCredentialsExpiredException", e);
            return ErrorResponse.of(ErrorCode.CREDENTIAL_EXPIRED);
        }
        /**
         * Authentication 계정 비활성화.
         */
        else if(e instanceof DisabledException){
            log.error("handleDisabledException", e);

            return ErrorResponse.of(ErrorCode.ACCOUNT_DISABLED);
        }
        /**
         * Authentication 계정 잠김.
         */
        else if(e instanceof LockedException){
            log.error("handleLockedException", e);
            return ErrorResponse.of(ErrorCode.ACCOUNT_LOCKED);
        }
        else {
            log.error("handleAccessDeniedException", e);
            return ErrorResponse.of(ErrorCode.HANDLE_ACCESS_DENIED);
            }
    }

    public HttpServletResponse formatServletResponse( HttpServletResponse response, ErrorResponse errorResponse) throws IOException {
        response.setStatus(errorResponse.getStatus());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String result = objectMapper.writeValueAsString(errorResponse);
        response.getWriter().write(result);
        return response;
    }
}