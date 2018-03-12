package com.github.jetqin.config.security.oauth;

import com.github.jetqin.config.security.OAuth2ServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends
        AuthorizationServerConfigurerAdapter
{

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
                .resourceIds(Constants.RESOURCE_ID)
                .scopes("read", "write")
                .accessTokenValiditySeconds(20000)
                .refreshTokenValiditySeconds(20000)
                .autoApprove(true)
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
        JwtAccessTokenConverter converter = new CustomTokenEnhancer();
        converter.setKeyPair(
                new KeyStoreKeyFactory(new ClassPathResource("bootsecurity.jks"), "bootsecurity".toCharArray())
                        .getKeyPair("bootsecurity"));
        return converter;
    }

    @Bean("tokenService")
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setTokenStore(tokenStore());
        return tokenServices;
    }

}