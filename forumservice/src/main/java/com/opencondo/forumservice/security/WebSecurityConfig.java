package com.opencondo.forumservice.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Spring security configuration class. In this class is every configuration for credentials and
 * authorization for http requests. For now, is authorizing all requests.
 * TODO: JWT authentication
 *
 * @author Olavo Holanda
 * @version 0.1
 * @since 0.1
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable().authorizeRequests()
        .anyRequest().permitAll()
        .antMatchers("/api/**").authenticated()
        .and()
        // filter all requests to check the presence of JWT in header
        .addFilterBefore(new JWTAuthenticationFilter(),
            UsernamePasswordAuthenticationFilter.class);
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    // Create a default account
    auth.inMemoryAuthentication()
        .withUser("admin")
        .password("admin")
        .roles("ADMIN");
  }
}
