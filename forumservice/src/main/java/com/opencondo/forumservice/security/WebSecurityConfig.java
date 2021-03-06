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
 *
 * @author Olavo Holanda
 * @version 0.1
 * @since 0.1
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  /**
   * Configures <code>HttpSecurity</code> to permit any request for anonymous users except
   * the ones starting with '/api', these requests must be authenticated.
   *
   * @param http a <code>HttpSecurity</code> object.
   */
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable().authorizeRequests()
        .antMatchers("/api/**").authenticated()
        .anyRequest().permitAll()
        .and()
        // filter all requests to check the presence of JWT in header
        .addFilterBefore(new JWTAuthenticationFilter(),
            UsernamePasswordAuthenticationFilter.class);
  }

  /**
   * Configures <code>AuthenticationManagerBuilder</code> to be in memory authentication
   * with a simple user 'admin'.
   *
   * @param auth an <code>AuthenticationManagerBuilder</code>.
   */
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    // Create a default account
    auth.inMemoryAuthentication()
        .withUser("admin")
        .password("admin")
        .roles("ADMIN");
  }
}
