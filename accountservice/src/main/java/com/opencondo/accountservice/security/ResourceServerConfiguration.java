package com.opencondo.accountservice.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * Spring resource server security configuration class. In this class is every configuration for
 * credentials and authorization for http requests.
 *
 * @author Olavo Holanda
 * @version 0.1
 * @since 0.1
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

  /**
   * Configures <code>HttpSecurity</code> to permit any request for anonymous users except
   * the ones starting with '/api', these requests must be authenticated.
   *
   * @param http a <code>HttpSecurity</code> object.
   */
  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/api/**").authenticated()
        .anyRequest().permitAll();
  }
}
