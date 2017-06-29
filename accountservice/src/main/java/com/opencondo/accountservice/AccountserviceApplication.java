package com.opencondo.accountservice;

import com.opencondo.accountservice.domain.storage.UserRepository;
import com.opencondo.accountservice.security.ImmutableUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

/**
 * Spring Boot Application class, with a main method to start the embedded server.
 *
 * @author Olavo Holanda
 * @version 0.1
 * @since 0.1
 */
@SpringBootApplication
public class AccountserviceApplication {

  /**
   * Starts this project with an embedded server.
   */
  public static void main(String[] args) {
    SpringApplication.run(AccountserviceApplication.class, args);
  }

  @Autowired
  public void authenticationManager(AuthenticationManagerBuilder builder, UserRepository repo)
      throws Exception {
    builder.userDetailsService(s -> (new ImmutableUserDetails(repo.findByUsername(s))));
  }
}
