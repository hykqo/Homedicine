package home.medecine.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
public class LoginFailerHandler implements AuthenticationFailureHandler {

    private final HandlerService handlerService;

    /*
    - 실패한 인증 시도를 처리하는 데 사용되는 전략입니다.
    - 일반적인 동작은 사용자를 인증 페이지로 리디렉션하는 것일 수 있습니다(이 경우 양식 로그인)을 사용하여 다시 시도할 수 있습니다. 더 정교한 논리는
    - 예외 유형에 따라 구현됩니다.
    */

    /*
    BadCredentialsException : 비밀번호불일치
    UsernameNotFoundException : 계정없음
    AccountExpiredException : 계정만료
    CredentialsExpiredException : 비밀번호만료
    DisabledException : 계정비활성화
    LockedException : 계정잠김
    */


    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException exception
    ) throws IOException, ServletException {
        /*
         * 요구기능
         * 1. 한 아이디에 대해 너무 잦은 요청이 올 경우 ID 잠금
         * 2. 단순 로그인 요청이 너무 잦을 경우 일치하는 숫자를 입력하도록 구현.
         * 3. 아이디가 잠긴경우 이메일을 통해 풀리도록 구현.
         * 4. 비밀번호 찾기 기능 구현*/

        String id = request.getParameter("id");
        String pw = request.getParameter("pw");
        String error = exception.getMessage();


        try {
            ObjectMapper objectMapper = new ObjectMapper();

            Map<String, Object> responseMap = new HashMap<>();
            String message = getExceptionMessage(exception, id);
            responseMap.put("status", 401);
            responseMap.put("message", message);
            response.getOutputStream().println(objectMapper.writeValueAsString(responseMap));
            if(message.equals("비밀번호불일치")) {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getExceptionMessage(AuthenticationException exception, String id) {
        if (exception instanceof BadCredentialsException) {
            handlerService.BadCredendtial(id);
            return "비밀번호불일치";
        } else if (exception instanceof UsernameNotFoundException) {
            return "계정없음";
        } else if (exception instanceof AccountExpiredException) {
            return "계정만료";
        } else if (exception instanceof CredentialsExpiredException) {
            return "비밀번호만료";
        } else if (exception instanceof DisabledException) {
            return "계정비활성화";
        } else if (exception instanceof LockedException) {
            return "계정잠김";
        } else {
            return "확인된 에러가 없습니다.";
        }
    }

}
