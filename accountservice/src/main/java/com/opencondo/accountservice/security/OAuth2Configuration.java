package com.opencondo.accountservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Spring authorization server configuration class based on OAuth 2.
 *
 * To get a token, first you need to register a user, then you create a POST request
 * with POSTMAN Basic auth with client id as username and client secret as password. In the
 * body you use x-www-form-urlencoded grant_type:password username:admin password:md5(password)
 *
 * @author Olavo Holanda
 * @version 0.1
 * @since 0.1
 */
@Configuration
@EnableAuthorizationServer
public class OAuth2Configuration extends AuthorizationServerConfigurerAdapter {

  private static final String JWT_KEY = "opcjwtkey";
  private static final String CLIENT_ID = "opcid";
  private static final String CLIENT_SECRET = "opcsecret";

  @Autowired
  private AuthenticationManager authenticationManager;

  // TODO externalize token related data to configuration, store clients in DB
  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients.inMemory().withClient(CLIENT_ID).secret(CLIENT_SECRET).authorizedGrantTypes("implicit", "refresh_token", "password")
            .authorities("ROLE_ADMIN, ROLE_MANAGER, ROLE_RESIDENT, ROLE_DOORMAN").scopes("trust").autoApprove(true)
            .accessTokenValiditySeconds(60000).refreshTokenValiditySeconds(60000);
  }

  /*
   * The endpoints can only be accessed by a not logged in user or a user with
   * the specified role
   */
  @Override
  public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
    oauthServer.tokenKeyAccess("isAnonymous() || hasAuthority('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_RESIDENT', 'ROLE_DOORMAN')")
            .checkTokenAccess("hasAuthority('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_RESIDENT', 'ROLE_DOORMAN')");
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    endpoints.tokenStore(tokenStore()).tokenEnhancer(jwtAccessTokenConverter())
            .authenticationManager(authenticationManager);
  }

  @Bean
  public TokenStore tokenStore() {
    return new JwtTokenStore(jwtAccessTokenConverter());
  }

  @Bean
  protected JwtAccessTokenConverter jwtAccessTokenConverter() {
    JwtAccessTokenConverter converter = new CustomTokenEnhancer();
    converter.setSigningKey(JWT_KEY);
    return converter;
  }

  /*
   * Add custom user principal information to the JWT token
   */
  protected static class CustomTokenEnhancer extends JwtAccessTokenConverter {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
      ImmutableUserDetails user = (ImmutableUserDetails) authentication.getPrincipal();

      Map<String, Object> info = new LinkedHashMap<>(accessToken.getAdditionalInformation());

      info.put("username", user.getUsername());

      DefaultOAuth2AccessToken customAccessToken = new DefaultOAuth2AccessToken(accessToken);

      // Get the authorities from the user
      Set<GrantedAuthority> authoritiesSet = new HashSet<>(authentication.getAuthorities());

      // Generate String array
      String[] authorities = new String[authoritiesSet.size()];

      int i = 0;
      for (GrantedAuthority authority : authoritiesSet)
        authorities[i++] = authority.getAuthority();

      info.put("authorities", authorities);
      customAccessToken.setAdditionalInformation(info);

      return super.enhance(customAccessToken, authentication);
    }
  }

  /*
   * Setup the refresh_token functionality to work with the custom
   * UserDetailsService
   */
  @Configuration
  protected static class GlobalAuthenticationManagerConfiguration extends GlobalAuthenticationConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
      auth.userDetailsService(userDetailsService);
    }
  }
}