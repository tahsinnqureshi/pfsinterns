package com.cms.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	public SecurityFilterChain securityFilter(HttpSecurity http) throws Exception {
	    http
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers("/newContent").permitAll()
	            .requestMatchers("/view-post").permitAll()
	            .requestMatchers("/welcomePage").permitAll()
	            .requestMatchers("/").permitAll()
	            .requestMatchers("/delete").permitAll()
	            .requestMatchers("/edit").permitAll()  // This allows access to /edit without authentication
	            .requestMatchers("/search").permitAll()
	            .requestMatchers("/admin-user").permitAll()
	            .requestMatchers("/index").permitAll()
	            .requestMatchers("/register").permitAll()
	            .requestMatchers("/save-user").permitAll()
	            .requestMatchers("/upload-Content").permitAll()
	            .requestMatchers("/login").permitAll()
	            .requestMatchers("/loginPage").permitAll()
	            
	            .anyRequest().authenticated()
	        )
	        .formLogin(form -> form
	            .loginPage("/login")
	            .defaultSuccessUrl("/admin-user", true)
	            .permitAll()
	        )
	        .logout(logout -> logout
	            .logoutUrl("/logout")
	            .logoutSuccessUrl("/")
	            .invalidateHttpSession(true)
	            .deleteCookies("JSESSIONID")
	            .permitAll()
	        );
	    return http.build();
	}

	


	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	
}
