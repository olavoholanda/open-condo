package com.opencondo.accountservice.security;

import com.opencondo.accountservice.domain.model.User;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Concrete immutable implementation of {@link UserDetails}. Encapsulates information for {@link
 * org.springframework.security.core.Authentication} objects.
 *
 * @author Olavo Holanda
 * @version 0.1
 * @since 0.1
 */
public class ImmutableUserDetails implements UserDetails {

  private String username;
  private String password;
  private boolean enable;
  private Collection<? extends GrantedAuthority> authorities;

  /**
   * Class constructor using an entity of {@link User}.
   */
  public ImmutableUserDetails(User user) {
    this.username = user.getUsername();
    this.password = user.getPassword();
    this.enable = user.isEnable();

    List<GrantedAuthority> auths = new ArrayList<>();
    auths.add(new SimpleGrantedAuthority(user.getRole().name()));

    this.authorities = auths;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return enable;
  }
}
