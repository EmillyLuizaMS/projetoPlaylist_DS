package com.cefet.playlist.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cefet.playlist.security.JwtAuthenticationFilter;
import com.cefet.playlist.services.UsuarioDetailsService;

import java.util.Arrays;

@Configuration
public class SecurityConfig {

    @Autowired
    private UsuarioDetailsService usuarioDetailsService;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // --- INÍCIO DA CORREÇÃO ---
                        // 1. Permite todas as requisições do tipo OPTIONS (para o CORS preflight)
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        // 2. Coloca as rotas públicas primeiro para garantir que são processadas antes das regras restritivas.
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth").permitAll()      // Permitir login
                        .requestMatchers(HttpMethod.POST, "/usuario").permitAll()   // Permitir registo de usuário
                        // --- FIM DA CORREÇÃO ---

                        // Regras de Autorização para ARTISTA
                        .requestMatchers(HttpMethod.GET, "/artista/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/artista").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/artista/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/artista/**").hasRole("ADMIN")

                        // Regras de Autorização para COMENTÁRIOS
                        .requestMatchers(HttpMethod.GET, "/comentario/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/comentario").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.PUT, "/comentario/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.DELETE, "/comentario/**").hasAnyRole("ADMIN", "USER")

                        // Regras de Autorização para FAVORITOS
                        .requestMatchers(HttpMethod.GET, "/favorito/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/favorito").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.DELETE, "/favorito/**").hasAnyRole("ADMIN", "USER")

                        // Regras de Autorização para MÚSICAS
                        .requestMatchers(HttpMethod.GET, "/musica/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/musica").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/musica/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/musica/**").hasRole("ADMIN")

                        // Regras de Autorização para MÚSICAS NA PLAYLIST
                        .requestMatchers(HttpMethod.GET, "/musicas-playlist/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/musicas-playlist/add").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.DELETE, "/musicas-playlist/**").hasAnyRole("ADMIN", "USER")

                        // Regras de Autorização para PLAYLISTS
                        .requestMatchers(HttpMethod.GET, "/playlist/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/playlist").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.PUT, "/playlist/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.DELETE, "/playlist/**").hasAnyRole("ADMIN", "USER")

                        // Regras de Autorização para USUÁRIOS
                        .requestMatchers(HttpMethod.GET, "/usuario/**").authenticated()
                        // .requestMatchers(HttpMethod.POST, "/usuario").permitAll() // Movido para cima
                        .requestMatchers(HttpMethod.PUT, "/usuario/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.DELETE, "/usuario/**").hasRole("ADMIN")

                        // Todos os outros endpoints exigem autenticação
                        .anyRequest().authenticated()
                )
                .headers(headers -> headers.frameOptions().disable())
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(usuarioDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // Adicione a URL do seu frontend do Netlify aqui!
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200", "https://app-musica.netlify.app"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
