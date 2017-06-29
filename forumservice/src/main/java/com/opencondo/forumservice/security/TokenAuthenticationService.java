package com.opencondo.forumservice.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.util.Arrays;
import java.util.Collections;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 * Service for authenticate a JWT token. This forum micro service only validates tokens,
 * it does not create then. TODO: Enable role authentication and check if user is registered.
 *
 * @author Olavo Holanda
 * @version 0.1
 * @since 0.1
 */
class TokenAuthenticationService {

  private static final String SECRET = "YourSecretHere";
  private static final String TOKEN_PREFIX = "Bearer";
  private static final String HEADER_STRING = "Authorization";

  /**
   * Creates a new <code>UsernamePasswordAuthenticationToken</code> with a user id parsed
   * from the Json Web Token in the Authorization header.
   *
   * @param request a <code>HttpServletRequest</code> object.
   * @return an authentication of the type <code>UsernamePasswordAuthenticationToken</code>.
   */
  static Authentication getAuthentication(HttpServletRequest request) {
    String token = request.getHeader(HEADER_STRING);
    if (token != null) {
      Claims claims = Jwts.parser()
          .setSigningKey(SECRET)
          .parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody();

      String userId = (String) claims.get("userId");
      String role = (String) claims.get("role");

      if (userId != null && role != null) {
        return new UsernamePasswordAuthenticationToken(userId, null,
            Collections.singletonList((GrantedAuthority) () -> role));
      }
    }
    return null;
  }
}
