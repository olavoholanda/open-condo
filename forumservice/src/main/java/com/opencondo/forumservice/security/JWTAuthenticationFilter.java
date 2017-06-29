package com.opencondo.forumservice.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

/**
 * Class responsible for filtering every request that must be authenticated.
 *
 * @author Olavo Holanda
 * @version 0.1
 * @since 0.1
 */
public class JWTAuthenticationFilter extends GenericFilterBean {

  /**
   * Filters every request that must be authenticated, using the <code>TokenAuthenticationService</code>.
   *
   * @param request a <code>ServletRequest</code> instance.
   * @param response a <code>ServletResponse</code> instance.
   * @param filterChain a <code>FilterChain</code> instance.
   */
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
      throws IOException, ServletException {
    Authentication authentication = TokenAuthenticationService
        .getAuthentication((HttpServletRequest) request);

    SecurityContextHolder.getContext().setAuthentication(authentication);
    filterChain.doFilter(request, response);
  }
}
