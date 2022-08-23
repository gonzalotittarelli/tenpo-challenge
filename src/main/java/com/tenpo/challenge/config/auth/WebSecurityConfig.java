package com.tenpo.challenge.config.auth;

import com.tenpo.challenge.entity.jpa.UserRole;
import com.tenpo.challenge.filter.AuthTokenFilter;
import com.tenpo.challenge.service.auth.JWTTokenServiceImpl;
import com.tenpo.challenge.service.auth.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired private final UserDetailsServiceImpl userDetailsServiceImpl;
  @Autowired private final BCryptPasswordEncoder bCryptPasswordEncoder;
  @Autowired private final AuthEntryPointJWT authEntryPointJWT;
  @Autowired private final JWTTokenServiceImpl jwtTokenService;
  private static final String[] ENDPOINTS_WITHOUT_AUTH = {
    "/v3/api-docs/**",
    "/swagger-ui/**",
    "/v2/api-docs",
    "/swagger-resources",
    "/swagger-resources/**",
    "/configuration/ui",
    "/configuration/security",
    "/swagger-ui.html",
    "/webjars/**",
    "/user/**"
  };
  private static final String[] ENDPOINTS_AUTH = {
    "/calculator/add/**", "/history/**", "/user/logout/**"
  };

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers(ENDPOINTS_WITHOUT_AUTH);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
    http.csrf()
        .disable()
        .exceptionHandling()
        .authenticationEntryPoint(authEntryPointJWT)
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests()
        .antMatchers(ENDPOINTS_AUTH)
        .hasAnyAuthority(UserRole.USER.name())
        .and()
        .addFilterBefore(
            new AuthTokenFilter(this.jwtTokenService, this.userDetailsServiceImpl),
            BasicAuthenticationFilter.class)
        .authorizeRequests()
        .antMatchers(ENDPOINTS_WITHOUT_AUTH)
        .permitAll();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(this.userDetailsServiceImpl)
        .passwordEncoder(this.bCryptPasswordEncoder);
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }
}
