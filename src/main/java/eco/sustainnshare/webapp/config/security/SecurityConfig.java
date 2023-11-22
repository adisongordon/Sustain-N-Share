package eco.sustainnshare.webapp.config.security;

import eco.sustainnshare.webapp.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserDetailsService usersService;
    @Bean
    BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(authConfig -> {
            authConfig.requestMatchers(new MvcRequestMatcher(new HandlerMappingIntrospector(), "/"))
                    .permitAll();
            authConfig.requestMatchers(new MvcRequestMatcher(new HandlerMappingIntrospector(), "/about-us"))
                    .permitAll();
            authConfig.requestMatchers(new MvcRequestMatcher(new HandlerMappingIntrospector(), "/how-it-works"))
                    .permitAll();
            authConfig.requestMatchers(new MvcRequestMatcher(new HandlerMappingIntrospector(), "/impact"))
                    .permitAll();
            authConfig.requestMatchers(new MvcRequestMatcher(new HandlerMappingIntrospector(), "/blog"))
                    .permitAll();
            authConfig.requestMatchers(new MvcRequestMatcher(new HandlerMappingIntrospector(), "/contact-us"))
                    .permitAll();
            authConfig.requestMatchers(new MvcRequestMatcher(new HandlerMappingIntrospector(), "/community-stories"))
                    .permitAll();
            authConfig.requestMatchers(new MvcRequestMatcher(new HandlerMappingIntrospector(), "/faqs"))
                    .permitAll();
            authConfig.requestMatchers(new MvcRequestMatcher(new HandlerMappingIntrospector(), "/sign-up"))
                    .permitAll();

            authConfig.anyRequest().authenticated();
        })
                .formLogin(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    ApplicationListener<AuthenticationSuccessEvent> successEvent(){
        return event -> {
            System.out.println("Success Login " + event.getAuthentication().getClass().getSimpleName() + " - " + event.getAuthentication().getName());
        };
    }

    @Bean
    ApplicationListener<AuthenticationFailureBadCredentialsEvent> failureEvent(){
        return event -> {
            System.err.println("Bad Credentials Login " + event.getAuthentication().getClass().getSimpleName() + " - " + event.getAuthentication().getName());
        };
    }
}
