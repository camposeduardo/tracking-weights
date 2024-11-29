package com.camposeduardo.trackingweights.config;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig implements WebMvcConfigurer {

    private final JwtAuthFilter jwtAuthenticationFilter;

    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .headers(HeadersConfigurer::disable)
                .authorizeHttpRequests(
                        (requests) -> requests
                                // https://stackoverflow.com/questions/65074794/cross-domain-cookies-in-preflight-requests
                                .requestMatchers(HttpMethod.OPTIONS, "/exercise/**").permitAll()
                                .requestMatchers("/login", "/register", "/auth/status")
                                .permitAll()
                                .anyRequest().authenticated()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        //.invalidateHttpSession(true) -> if the application is not stateless
                        .logoutSuccessHandler((request, response, authentication) -> {
                            /**
                             * the default Spring Security /logout endpoint, was displaying
                             * a CORS-related error. This is because the default
                             * Spring Security /logout endpoint was not sending CORS headers by default.
                             * Maybe ??? this is a limitation of how Spring Security's
                             * default endpoint handles requests
                             */
                            response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
                            response.setHeader("Access-Control-Allow-Credentials", "true");
                            response.setStatus(HttpServletResponse.SC_OK);
                        })
                        .deleteCookies("jwt")
                )
                .sessionManagement(customizer -> customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(authenticationProvider);
        return http.build();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowCredentials(true);
    }
}
