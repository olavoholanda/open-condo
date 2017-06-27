package com.opencondo.forumservice.security;

import io.jsonwebtoken.Jwts;
import java.util.Collections;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

/**
 * Created by Olavo on 27/06/2017.
 */
class TokenAuthenticationService {

  private static final String SECRET = "ThisIsASecret";
  private static final String TOKEN_PREFIX = "Bearer";
  private static final String HEADER_STRING = "Authorization";

  static Authentication getAuthentication(HttpServletRequest request) {
    String token = request.getHeader(HEADER_STRING);
    if (token != null) {
      // parses the token
      String user = Jwts.parser()
          .setSigningKey(SECRET)
          .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
          .getBody()
          .getSubject();

      return user != null ?
          new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList()) :
          null;
    }
    return null;
  }
}
