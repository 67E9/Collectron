package de.yougrowgroup.CollectronBackend.config;

import de.yougrowgroup.CollectronBackend.Constants.SecurityConstants;
import de.yougrowgroup.CollectronBackend.Filter.JwtGeneratorFilter;
import de.yougrowgroup.CollectronBackend.Filter.JwtValidatorFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain defaultSecurityFilterChain (HttpSecurity http) throws Exception{

        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().cors().configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration config = new CorsConfiguration();
                        config.setAllowedOrigins(Collections.singletonList(SecurityConstants.getFRONTEND_ADDRESS()));
                        config.setAllowedMethods(Collections.singletonList("*"));
                        config.setAllowCredentials(true);
                        config.setAllowedHeaders(Collections.singletonList("*"));
                        config.setExposedHeaders(Arrays.asList("Authorization"));
                        config.setMaxAge(3600L);
                        return config;
                    }
                })
                .and().csrf().disable() //enable unless you implement jwt
                .authorizeHttpRequests(auth -> auth
                        .antMatchers(HttpMethod.POST).hasRole("ADMIN")
                        .antMatchers(HttpMethod.PUT).hasRole("ADMIN")
                        .antMatchers(HttpMethod.DELETE).hasRole("ADMIN")
                        .antMatchers("/api/user/**").hasRole("ADMIN")
                        .antMatchers(HttpMethod.GET, "/api/blogPost/**").permitAll()
                        .antMatchers(HttpMethod.GET, "/api/collectible/**").permitAll()
                        .antMatchers(HttpMethod.GET, "/api/type/**").permitAll()
                .and().addFilterAfter(new JwtGeneratorFilter(), BasicAuthenticationFilter.class)
                        .addFilterBefore(new JwtValidatorFilter(), BasicAuthenticationFilter.class)
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }


}
