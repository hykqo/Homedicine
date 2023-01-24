package home.medecine.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final LoginSuccessHandler loginSuccessHandler;
    private final LoginFailerHandler loginFailerHandler;

    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
            .authorizeRequests()
                .antMatchers("/admin/**")
                .hasAnyAuthority("ROLE_ADMIN")

                .antMatchers("/medicine/**")
                .permitAll()
                .anyRequest().authenticated()

                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")

                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login-proc")
                .defaultSuccessUrl("/")
                .successHandler(loginSuccessHandler)
                .failureHandler(loginFailerHandler);
        return http.build();
    }
}
