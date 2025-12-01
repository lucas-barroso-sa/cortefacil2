package cortefacil.unifor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. Desabilita CSRF: Necessário para APIs REST (POST/PUT) funcionarem livremente
                .csrf(csrf -> csrf.disable())

                // 2. Define que a API é Stateless (não guarda sessão de usuário na memória)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // 3. Configura as permissões de acesso
                .authorizeHttpRequests(authorize -> authorize
                        // LIBERA TUDO: Permite acesso a qualquer URL sem login
                        .anyRequest().permitAll()
                )

                // 4. Libera o acesso ao H2-Console (se estiver usando)
                // O H2 usa frames HTML que o Spring Security bloqueia por padrão
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()));

        return http.build();
    }

    // Bean responsável por Criptografar e Verificar senhas
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}