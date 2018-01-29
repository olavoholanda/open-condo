package com.opencondo.forumservice.utils;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Cross Origin Resource Sharing filter, enables all domains for every method.
 *
 * @author Olavo Holanda
 * @version 0.1
 * @since 0.1
 */
@Configuration
public class CORSConfig {

  /**
   * Enables CORS requests from any origin, for any method with any header.
   *
   * @return a new first priority filter with CORS enabled.
   */
  @Bean
  public CorsFilter corsFilter() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(Boolean.TRUE);
    config.addAllowedOrigin("*");
    config.addAllowedHeader("*");
    config.addAllowedMethod("*");
    source.registerCorsConfiguration("/**", config);
    return new CorsFilter(source);
  }
}
