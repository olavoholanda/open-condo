package com.opencondo.accountservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

/**
 * Spring authorization server configuration class based on OAuth 2.
 *
 * @author Olavo Holanda
 * @version 0.1
 * @since 0.1
 */
@Configuration
@EnableAuthorizationServer
public class OAuth2Configuration extends AuthorizationServerConfigurerAdapter {

  private final AuthenticationManager authenticationManager;

  /**
   * Class constructor with AutoWired dependency injection.
   */
  @Autowired
  public OAuth2Configuration(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }

  /**
   * Configure the security of the Authorization Server, which means in practical terms the
   * /oauth/token endpoint. The /oauth/authorize endpoint also needs to be secure, but that is a
   * normal user-facing endpoint and should be secured the same way as the rest of your UI, so is
   * not covered here. The default settings cover the most common requirements, following
   * recommendations from the OAuth2 spec, so you don't need to do anything here to get a basic
   * server up and running.
   *
   * @param security - a fluent configurer for security features.
   * @throws Exception exception
   */
  @Override
  public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
    security.checkTokenAccess("isAuthenticated()");
  }

  /**
   * Configure the {@link org.springframework.security.oauth2.provider.ClientDetailsService}, e.g.
   * declaring individual clients and their properties. Note that password grant is not enabled
   * (even if some clients are allowed it) unless an {@link AuthenticationManager} is supplied to
   * the AuthorizationServerConfigurer.configure(AuthorizationServerEndpointsConfigurer). At least
   * one client, or a fully formed custom {@link org.springframework.security.oauth2.provider.ClientDetailsService}
   * must be declared or the server will not start.
   *
   * @param clients - the client details configurer.
   * @throws Exception exception
   */
  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients.inMemory()
        .withClient("clientId") // clientId
        .authorizedGrantTypes("client_credentials", "password")
        .authorities("ROLE_RESIDENT", "ROLE_MANAGER", "ROLE_ADMIN", "ROLE_DOORMAN")
        .scopes("read", "write", "trust")
        .resourceIds("oauth2-resource")
        .accessTokenValiditySeconds(86400)
        .secret("secret");
  }

  /**
   * Configure the non-security features of the Authorization Server endpoints, like token store,
   * token customizations, user approvals and grant types. You shouldn't need to do anything by
   * default, unless you need password grants, in which case you need to provide an
   * {@link AuthenticationManager}.
   *
   * @param endpoints - the endpoints configurer.
   * @throws Exception exception
   */
  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    endpoints.authenticationManager(authenticationManager);
  }
}