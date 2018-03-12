package com.github.jetqin.config.security;


import com.github.jetqin.service.security.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true)
public class OAuth2ServerConfig extends WebSecurityConfigurerAdapter {

        @Bean(name = "passwordEncoder")
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Autowired
        @Qualifier("customUserDetailService")
        private CustomUserDetailService userDetailsService;


        @Override
        @Autowired
        protected void configure(AuthenticationManagerBuilder auth) throws Exception
        {
            // @formatter:off
           auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        }   // @formatter:on


        @Override
        @Bean
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }

        @Override
        protected void configure(final HttpSecurity http) throws Exception {
            // @formatter:off
            http.csrf().disable().authorizeRequests()
                    .antMatchers("/oauth/**").permitAll()
                    .anyRequest().authenticated()
                    ;
            // @formatter:on
        }

}
