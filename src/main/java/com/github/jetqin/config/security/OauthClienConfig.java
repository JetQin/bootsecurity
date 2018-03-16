package com.github.jetqin.config.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@EnableOAuth2Client
@Configuration
@PropertySource("classpath:application.properties")
public class OauthClienConfig
{
    @Value("${accessTokenUri}")
    private String accessTokenUri;

    @Value("${clientID}")
    private String clientID;

    @Value("${clientSecret}")
    private String clientSecret;

    @Bean
    public ClientCredentialsResourceDetails credential(){
        ClientCredentialsResourceDetails resourceDetails = new ClientCredentialsResourceDetails();
        resourceDetails.setId("stock");
        resourceDetails.setClientId(clientID);
        resourceDetails.setClientSecret(clientSecret);
        resourceDetails.setAccessTokenUri(accessTokenUri);
        resourceDetails.setGrantType("client_credentials");
        return resourceDetails;
    }

    @Bean("restTemplate")
    public OAuth2RestTemplate stockRestTemplate() {
        OAuth2RestTemplate template = new OAuth2RestTemplate(credential(), new DefaultOAuth2ClientContext());
        return template;
    }
}