package com.opencondo.accountservice.security;

import com.opencondo.accountservice.domain.model.User;
import com.opencondo.accountservice.domain.storage.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by olavo on 22/11/2017.
 */
@Service(value = "userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();

    @Override
    public UserDetails loadUserByUsername(String name) {
        User user;

        if (name.contains("@"))
            user = userRepository.findByEmail(name);
        else {
            System.out.println(name);
            user = userRepository.findByUsername(name);
            System.out.println("found user: " + user.getId());
        }


        if (user == null)
            throw new UsernameNotFoundException("Incorrect username, password or admin id.");

        ImmutableUserDetails details = new ImmutableUserDetails(user);

        detailsChecker.check(details);

        return details;
    }
}
