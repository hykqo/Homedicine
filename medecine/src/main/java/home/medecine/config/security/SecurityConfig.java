package home.medecine.config.security;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 *Thymeleaf, JSP, Freemaker, Mustache 등의 템플릿 엔진에서는 JWT를 구현하지 않는 것이 좋다.
 *위의 템플릿 엔진들은 전부 서버에서 페이지를 결정해주는 SSR(Server Side Rendering) 방식을 사용하는데,
 * 이 말은 스프링 Controller에서 View를 정하게 된다는 패러다임으로 클라이언트와 서버가 강결합 되어있는 개발 방식을 사용한다.
 *
 * JWT는 무상태성을 유지하는 인증 방식으로 클라이언트와 서버가 분리되어 있는 REST API에서 보통 사용기 때문에 이번에는 session을 이용해서 기능을 구현하자.
*/
@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
//                .antMatchers("/user/**", "/assets/**","/dist/**","/images/**","/assets/js/**","/assets/css/**").permitAll()
//                .anyRequest().authenticated()
                .anyRequest().permitAll()
                .and()
                    .formLogin()
                    .loginPage("/user/login")
                    .loginProcessingUrl("/login")
                    .defaultSuccessUrl("/")
                .and()
                    .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login")
                .and()
                    .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}

