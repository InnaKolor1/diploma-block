package ru.skypro.homework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
/**
 * Конфигурация безопасности приложения.
 * Настраивает Spring Security для обработки аутентификации и авторизации.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {

    private static final String[] AUTH_WHITELIST = {
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/webjars/**",
            "/login",
            "/register",
            "/ads",
            "/ads/*",
            "/ads/*/comments",
            "/images/**"
    };


    /**
     * Настраивает цепочку фильтров безопасности.
     * Конфигурирует правила доступа, CORS, аутентификацию и управление сессиями.
     * @param http объект HttpSecurity для настройки
     * @return настроенная цепочка фильтров безопасности
     * @throws Exception при ошибках конфигурации
     */

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auths -> auths
                        .requestMatchers(AUTH_WHITELIST).permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(httpBasic -> {});

        return http.build();
    }
    /**
     * Создает кодировщик паролей.
     * Использует алгоритм bcrypt для безопасного хранения паролей.
     *
     * @return экземпляр PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
