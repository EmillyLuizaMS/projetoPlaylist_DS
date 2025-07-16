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
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cefet.playlist.security.JwtAuthenticationFilter;
import com.cefet.playlist.services.UsuarioDetailsService;

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
                .requestMatchers("/h2-console/**").permitAll() // Acesso ao H2 Console
                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll() // Acesso ao Swagger UI
                .requestMatchers(HttpMethod.POST, "/auth").permitAll() // Permitir login de usuário

                
            // Regras de Autorização para ARTISTA
            .requestMatchers(HttpMethod.GET, "/artista/**").hasAnyRole("ADMIN", "USER") // Acesso para ADMIN e USER
            .requestMatchers(HttpMethod.POST, "/artista").hasRole("ADMIN") // Apenas ADMIN pode criar artistas
            .requestMatchers(HttpMethod.PUT, "/artista/**").hasRole("ADMIN") // Apenas ADMIN pode atualizar artistas
            .requestMatchers(HttpMethod.DELETE, "/artista/**").hasRole("ADMIN") // Apenas ADMIN pode deletar artistas

            // Regras de Autorização para COMENTÁRIOS
            .requestMatchers(HttpMethod.GET, "/comentario/**").hasAnyRole("ADMIN", "USER") // ADMIN e USER podem visualizar comentários
            .requestMatchers(HttpMethod.POST, "/comentario").hasAnyRole("ADMIN", "USER") // ADMIN e USER podem criar comentários
            .requestMatchers(HttpMethod.PUT, "/comentario/**").hasAnyRole("ADMIN", "USER") // ADMIN e USER podem editar seus comentários
            .requestMatchers(HttpMethod.DELETE, "/comentario/**").hasAnyRole("ADMIN", "USER") // ADMIN e USER podem excluir seus comentários

            // Regras de Autorização para FAVORITOS
            .requestMatchers(HttpMethod.GET, "/favorito/**").hasAnyRole("ADMIN", "USER") // ADMIN e USER podem visualizar favoritos
            .requestMatchers(HttpMethod.POST, "/favorito").hasAnyRole("ADMIN", "USER") // ADMIN e USER podem criar favoritos
            .requestMatchers(HttpMethod.DELETE, "/favorito/**").hasAnyRole("ADMIN", "USER") // ADMIN e USER podem excluir favoritos

            // Regras de Autorização para MÚSICAS
            .requestMatchers(HttpMethod.GET, "/musica/**").hasAnyRole("ADMIN", "USER") // ADMIN e USER podem visualizar músicas
            .requestMatchers(HttpMethod.POST, "/musica").hasRole("ADMIN") // Apenas ADMIN pode criar músicas
            .requestMatchers(HttpMethod.PUT, "/musica/**").hasRole("ADMIN") // Apenas ADMIN pode atualizar músicas
            .requestMatchers(HttpMethod.DELETE, "/musica/**").hasRole("ADMIN") // Apenas ADMIN pode deletar músicas

            // Regras de Autorização para MÚSICAS NA PLAYLIST
            .requestMatchers(HttpMethod.GET, "/musicas-playlist/**").hasAnyRole("ADMIN", "USER") // ADMIN e USER podem visualizar músicas na playlist
            .requestMatchers(HttpMethod.POST, "/musicas-playlist/add").hasAnyRole("ADMIN", "USER") //  ADMIN e USER podem adicionar músicas na playlist
            .requestMatchers(HttpMethod.DELETE, "/musicas-playlist/**").hasAnyRole("ADMIN", "USER") //  ADMIN e USER podem remover músicas da playlist

            // Regras de Autorização para PLAYLISTS
            .requestMatchers(HttpMethod.GET, "/playlist/**").hasAnyRole("ADMIN", "USER") // ADMIN e USER podem visualizar playlists
            .requestMatchers(HttpMethod.POST, "/playlist").hasAnyRole("ADMIN", "USER") // ADMIN e USER podem criar playlists
            .requestMatchers(HttpMethod.PUT, "/playlist/**").hasAnyRole("ADMIN", "USER") // ADMIN e USER podem editar suas playlists
            .requestMatchers(HttpMethod.DELETE, "/playlist/**").hasAnyRole("ADMIN", "USER") // ADMIN e USER podem excluir suas playlists

            // Regras de Autorização para USUÁRIOS
            .requestMatchers(HttpMethod.GET, "/usuario/**").hasRole("ADMIN") // Apenas ADMIN pode ver detalhes de outros usuários
            .requestMatchers(HttpMethod.POST, "/usuario").permitAll() // ADMIN e USER podem criar usuários
            .requestMatchers(HttpMethod.PUT, "/usuario/**").hasAnyRole("ADMIN", "USER") // Apenas ADMIN pode atualizar usuários
            .requestMatchers(HttpMethod.DELETE, "/usuario/**").hasRole("ADMIN") // Apenas ADMIN pode deletar usuários

            //Todos os outros endpoints exigem autenticação 
                .anyRequest().authenticated() 
            )
            .headers(headers -> headers.frameOptions().disable()) // Para H2 Console
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
	  public WebMvcConfigurer corsConfigurer() {
	    return new WebMvcConfigurer() {
	      @Override
	      public void addCorsMappings(CorsRegistry registry) {
	        registry.addMapping("/**")
	          .allowedOrigins("http://localhost:4200")
	          .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
	          .allowedHeaders("*")
	          .allowCredentials(true);
	      }
	    };
	  }   

}
