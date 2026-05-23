package proyecto.bookadvisor.security;

import org.springframework.boot.security.autoconfigure.web.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
        @Bean
        public AuthenticationManager authenticationManager(
                        AuthenticationConfiguration authenticationConfiguration)
                        throws Exception {
                return authenticationConfiguration.getAuthenticationManager();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http.headers(headersConfigurer -> headersConfigurer
                                .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));

                http.authorizeHttpRequests(auth -> auth
                                .requestMatchers("/user/auto/**", "/public/**", "/libros/","/libros/files/**", "/genero/", "/libros/buscar", "/libros/consulta/**","/imagenes/**").permitAll()
                                .requestMatchers("/valoracion/**").hasAnyRole("Administrador", "Usuario", "Manager")
                                .requestMatchers("/libros/**", "/genero/**").hasAnyRole("Administrador", "Manager")
                                .requestMatchers("/user/**").hasRole("Administrador")
                                .requestMatchers("/**").hasAnyRole("Administrador", "Usuario", "Manager")
                                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll() // Recursos estáticos
                                .anyRequest().authenticated());
             
/*
                http.authorizeHttpRequests(auth -> auth
                                .requestMatchers("/**").permitAll()
                                .requestMatchers("/**").hasAnyRole("Administrador", "Usuario", "Manager")
                                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll() // Recursos estáticos
                                .anyRequest().authenticated());
 */ 
                http.formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer
                                .loginPage("/signin") // Mostrar formulario de login
                                .loginProcessingUrl("/login") // Ruta POST para login
                                .failureUrl("/signin?error") // Redirección en caso de error
                                .defaultSuccessUrl("/", true).permitAll());

                http.logout(logout -> logout
                                .logoutSuccessUrl("/").permitAll());

                http.exceptionHandling(exceptions -> exceptions.accessDeniedPage("/errorAccess"));

                return http.build();
        }
}
