package com.github.jetqin.config.security.oauth;

import com.github.jetqin.domain.jpa.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class CustomTokenEnhancer extends JwtAccessTokenConverter
{
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        Map<String, Object> info = new LinkedHashMap<String, Object>(accessToken.getAdditionalInformation());

        info.put("email", user.getEmail());

        DefaultOAuth2AccessToken customAccessToken = new DefaultOAuth2AccessToken(accessToken);

        // Get the authorities from the user
        Set<GrantedAuthority> authoritiesSet = new HashSet<>(authentication.getAuthorities());

        // Generate String array
        String[] authorities = new String[authoritiesSet.size()];

        int i = 0;
        for (GrantedAuthority authority : authoritiesSet)
            authorities[i++] = authority.getAuthority();

        info.put("authorities", authorities);
        customAccessToken.setAdditionalInformation(info);

        return super.enhance(customAccessToken, authentication);
    }
}
