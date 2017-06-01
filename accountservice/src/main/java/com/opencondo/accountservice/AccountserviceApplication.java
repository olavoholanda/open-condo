package com.opencondo.accountservice;

import com.opencondo.accountservice.model.storage.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Put a description of the class here.
 *
 * @author: Olavo Holanda
 */
@SpringBootApplication
public class AccountserviceApplication {

	@Autowired
	private UserRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(AccountserviceApplication.class, args);
	}
}
