package vml.travelagency.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.FilterChain;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Component
//@RequiredArgsConstructor
//@Slf4j
//public class WebAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//
//    @Autowired
//    public WebAuthenticationFilter(AuthenticationManager webAuthenticationManager) {
//        setAuthenticationManager(webAuthenticationManager);
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request,
//                                                HttpServletResponse response) throws AuthenticationException {
//        log.info("WebAuthenticationFilter is running");
//
//        String username = request.getParameter("email");
//        username = username != null ? username : "";
//        username = username.trim();
//        String password = request.getParameter("password");
//        password = password != null ? password : "";
//
//        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
//        return getAuthenticationManager().authenticate(authentication);
//    }
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request,
//                                            HttpServletResponse response,
//                                            FilterChain chain,
//                                            Authentication authResult) throws IOException {
//        SecurityContextHolder.getContext().setAuthentication(authResult);
//        response.sendRedirect("/home");
//    }
//
//    @Override
//    protected void unsuccessfulAuthentication(HttpServletRequest request,
//                                              HttpServletResponse response,
//                                              AuthenticationException failed) throws IOException {
//        SecurityContextHolder.clearContext();
//        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        response.sendRedirect("/login-form?error");
//    }
//}