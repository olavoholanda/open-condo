package com.opencondo.forumservice.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * SpringFox Swagger 2 configuration class. In this class is every configuration for api documentation
 * using swagger annotations.
 *
 * @author Olavo Holanda
 * @version 0.1
 * @since 0.1
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

  /**
   * Configures the SpringFox Swagger 2 documentation.
   *
   * @return a new SpringFox documentation instance for Swagger 2.
   */
  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.opencondo.forumservice.controller"))
        .paths(PathSelectors.ant("/api/**"))
        .build();
  }
}
