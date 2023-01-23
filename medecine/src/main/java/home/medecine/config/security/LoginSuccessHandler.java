package home.medecine.config.security;


import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    /*-
   <성공적인 사용자 인증을 처리하는 데 사용되는 전략입니다.>
    - 구현은 원하는 대로 할 수 있지만 일반적인 동작은 제어하는 것입니다.
    - 후속 목적지로의 탐색 (리디렉션 또는 포워드 사용)
    - 예를 들어, 사용자가 로그인 양식을 제출하여 로그인한 후 애플리케이션에서 결정해야 합니다.*/

     /*
        HttpServletRequest : 요청 객체
        HttpServletResponse : 응답 객체
        FilterChain : 다음 필터를 호출하기 위한 객체
        Authentication : 인증된 객체
        */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        FilterChain chain,
                                        Authentication authentication) throws IOException, ServletException {
        AuthenticationSuccessHandler.super.onAuthenticationSuccess(request, response, chain, authentication);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication
    ) throws IOException, ServletException {
        /* 요구기능
        1.정상 로그인 시 실패 지정회수를 초기화하는 기능 구현
        */
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        log.info("로그인 계정 : "+ username);


        response.sendRedirect(request.getContextPath());
    }
}
