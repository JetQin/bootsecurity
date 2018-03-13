package com.github.jetqin.controller.api;

import org.apache.catalina.LifecycleState;
import org.hibernate.type.ListType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/token")
public class TokenController
{
    @Autowired
    @Qualifier("tokenService")
    DefaultTokenServices tokenServices;

    @Autowired
    TokenStore tokenStore;


    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public List<String> getTokens(){
        List<String> tokenValues = new ArrayList<>();
        Collection<OAuth2AccessToken> tokens = tokenStore.findTokensByClientId("sampleClientId");
        if(tokens != null){
            for (OAuth2AccessToken token : tokens) {
                tokenValues.add(token.getValue());
            }
        }
        return tokenValues;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/revoke/{tokenId:.*}")
    public String revokeToken(@PathVariable String tokenId){
        tokenServices.revokeToken(tokenId);
        return tokenId;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/revokeRefreshToken/{tokenId:.*}")
    public String revokeRefreshToken(@PathVariable String tokenId){
        if (tokenStore instanceof JdbcTokenStore){
            ((JdbcTokenStore) tokenStore).removeRefreshToken(tokenId);
        }
        return tokenId;
    }

}
