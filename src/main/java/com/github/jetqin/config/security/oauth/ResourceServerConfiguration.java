package com.github.jetqin.config.security.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends
        ResourceServerConfigurerAdapter
{

    @Autowired
    @Qualifier("tokenService")
    private DefaultTokenServices tokenServices;

    @Autowired
    private TokenStore tokenStore;

    @Override
    public void configure(ResourceServerSecurityConfigurer config) {
        // @formatter:off
//                config.tokenServices(tokenServices())
        config.resourceId(Constants.RESOURCE_ID)
                .tokenServices(tokenServices)
                .tokenStore(tokenStore);;
        ;
        // @formatter:on
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/oauth/**").permitAll()
                .antMatchers("/hello").access("#oauth2.hasScope('read')")
                .antMatchers("/greeting").hasRole("ADMIN")
                .antMatchers("/api/**").authenticated()
        ;

//        http.requestMatcher(new OAuthRequestedMatcher())
//                .anonymous().disable()
//                .authorizeRequests()
//                .antMatchers(HttpMethod.OPTIONS).permitAll()
//                .antMatchers("/api/hello").access("hasAnyRole('USER')")
//                .antMatchers("/api/me").hasAnyRole("USER", "ADMIN")
//                .antMatchers("/api/register").hasAuthority("ROLE_REGISTER");
        // @formatter:on
    }

    private static class OAuthRequestedMatcher implements RequestMatcher
    {
        public boolean matches(HttpServletRequest request) {
            String auth = request.getHeader("Authorization");
            // Determine if the client request contained an OAuth Authorization
            boolean haveOauth2Token = (auth != null) && auth.startsWith("Bearer");
            boolean haveAccessToken = request.getParameter("access_token")!=null;
            return haveOauth2Token || haveAccessToken;
        }
    }
//
//        public TokenStore tokenStore() {
//            return new JwtTokenStore(accessTokenConverter());
//        }
//
//        public JwtAccessTokenConverter accessTokenConverter() {
//            JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//            Resource resource = new ClassPathResource("pubkey.txt");
//            String publicKey = null;
//            try {
//                publicKey = IOUtils.toString(resource.getInputStream());
//            } catch (final IOException e) {
//                throw new RuntimeException(e);
//            }
//            converter.setVerifierKey(publicKey);
//            return converter;
//        }
//
//        public DefaultTokenServices tokenServices() {
//            DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
//            defaultTokenServices.setTokenStore(tokenStore());
//            return defaultTokenServices;
//        }

}