package com.github.jetqin.config.security;


import com.github.jetqin.domain.User;
import com.github.jetqin.service.security.CustomUserDetailService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Configuration
public class OAuth2ServerConfig
{

    private static final String RESOURCE_ID = "restservice";

    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfiguration extends
            ResourceServerConfigurerAdapter {

        @Override
        public void configure(ResourceServerSecurityConfigurer config) {
            // @formatter:off
            config.tokenServices(tokenServices())
            ;
            // @formatter:on
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            // @formatter:off
            http
                    .authorizeRequests()
                    .antMatchers("/oauth/*").permitAll()
                    .antMatchers("/hello").hasRole("ADMIN")
                    .antMatchers("/api/*").authenticated()
                    ;
            // @formatter:on
        }

        @Bean
        public TokenStore tokenStore() {
            return new JwtTokenStore(accessTokenConverter());
        }

//        @Bean
//        public JwtAccessTokenConverter accessTokenConverter() {
//            JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//            converter.setSigningKey("123");
//            return converter;
//        }

        @Bean
        public JwtAccessTokenConverter accessTokenConverter() {
            JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
            Resource resource = new ClassPathResource("pubkey.txt");
            String publicKey = null;
            try {
                publicKey = IOUtils.toString(resource.getInputStream());
            } catch (final IOException e) {
                throw new RuntimeException(e);
            }
            converter.setVerifierKey(publicKey);
            return converter;
        }

        @Bean
        @Primary
        public DefaultTokenServices tokenServices() {
            DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
            defaultTokenServices.setTokenStore(tokenStore());
            return defaultTokenServices;
        }

    }

    @Configuration
    @EnableAuthorizationServer
    protected static class AuthorizationServerConfiguration extends
            AuthorizationServerConfigurerAdapter {

        @Autowired
        @Qualifier("authenticationManagerBean")
        private AuthenticationManager authenticationManager;

        @Autowired
        private PasswordEncoder passwordEncoder;

        @Override
        public void configure(AuthorizationServerSecurityConfigurer security) throws Exception
        {
           security.tokenKeyAccess("permitAll()")
                   .checkTokenAccess("isAuthenticated()");
        }

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints)
                throws Exception {
            // @formatter:off
            endpoints
                    .tokenStore(tokenStore())
                    .accessTokenConverter(accessTokenConverter())
                    .authenticationManager(authenticationManager)
            ;
            // @formatter:on
        }

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            // @formatter:off
            clients
                    .inMemory()
                    .withClient("clientapp")
                    .secret("123456")
                    .authorizedGrantTypes("password", "refresh_token")
                    .authorities("USER","ADMIN")
                    .scopes("read", "write")
                    .accessTokenValiditySeconds(20000)
                    .refreshTokenValiditySeconds(20000)
                    ;
            // @formatter:on
        }

        @Bean
        public JwtTokenStore tokenStore() {
            return new JwtTokenStore(accessTokenConverter());
        }

        /**
         * Generate the jks file

            keytool -genkeypair -alias mytest
                    -keyalg RSA
                    -keypass mypass
                    -keystore mytest.jks
                    -storepass mypass

         * Export the jks file

            keytool -list -rfc --keystore mytest.jks | openssl x509 -inform pem -pubkey

         * @return
         */

        @Bean
        public JwtAccessTokenConverter accessTokenConverter() {
            JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
            converter.setKeyPair(
                    new KeyStoreKeyFactory(new ClassPathResource("bootsecurity.jks"), "bootsecurity".toCharArray())
                            .getKeyPair("bootsecurity"));
            return converter;
        }

        @Bean
        @Primary
        public DefaultTokenServices tokenServices() {
            DefaultTokenServices tokenServices = new DefaultTokenServices();
            tokenServices.setSupportRefreshToken(true);
            tokenServices.setTokenStore(tokenStore());
            return tokenServices;
        }

    }

    /*
     * Setup the refresh_token functionality to work with the custom
     * UserDetailsService
     */


    @Configuration
    protected class WebSecurityConfig extends WebSecurityConfigurerAdapter {

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
            http.authorizeRequests()
                    .antMatchers("/oauth/**").permitAll()
                    .antMatchers("/tokens/**").permitAll()
                    .antMatchers("/api/**").hasRole("ADMIN")
                    .anyRequest().authenticated()
                    .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.NEVER);
            // @formatter:on
        }

    }

}