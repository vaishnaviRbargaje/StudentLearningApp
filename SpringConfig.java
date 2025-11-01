package com.tka.StudentLearningApp.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SpringConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        try {
            return http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                    // Admin routes
                    .requestMatchers("/admin/**").hasAuthority("ADMIN")
                    // User routes
                    .requestMatchers("/user/**").hasAuthority("USER")
                    // Public routes
                    .requestMatchers(
                        "/api/courses/viewAll",
                        "/api/contact/user/post",
                        "/api/enrollments/enroll",
                        "/api/courses/image/{courseId}",
                        "/api/enrollments/user/{userid}",
                        "/api/courses/admin/update/{courseId}",
                        "/api/courses/admin/add",
                        "/api/enrollments/admin/viewAllEnroll",
                        "/api/enrollments/admin/cancel/{id}",
                        "/api/contact/admin/get",
                        "/api/enrollments/admin/enrollment/stats",
                        "/api/enrollments/user/{userId}",
                        "/api/courses/search",
                        "/api/admin/test/add",
                        "/api/admin/test/delete/{id}",
                        "/api/admin/test/update/{id}",
                        "/api/admin/test/getResult",
                        "/api/users/test/getTest",           // Fixed missing slash
                        "/api/users/test/submit",
                        "/api/users/results/{username}"      // Fixed missing slash
                    ).permitAll()
                    // Authentication routes
                    .requestMatchers(
                        "/api/users/register",
                        "/api/users/login",
                        "/api/users/update-password"
                    ).permitAll()
                    // Any other request requires authentication
                    .anyRequest().authenticated()
                )
                .exceptionHandling(ex -> ex
                    .authenticationEntryPoint((request, response, authException) -> {
                        response.sendError(401, "Unauthorized - Please log in");
                    })
                )
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .build();
        } catch (Exception e) {
            System.err.println("Error configuring SecurityFilterChain: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Security configuration failed", e);
        }
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of(
        		"https://*.vercel.app",
            "https://edu-nexus-front-end-v2.vercel.app",
            "http://localhost:3000"
           
        ));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of(
            "Authorization", "Cache-Control", "Content-Type", "Accept", "Origin",
            "X-Requested-With", "multipart/form-data"
        ));
        configuration.setExposedHeaders(List.of("Authorization"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12); // Using strength 12 for better security
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
