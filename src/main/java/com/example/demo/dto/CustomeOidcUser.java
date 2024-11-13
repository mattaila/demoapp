package com.example.demo.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;

import com.example.demo.dto.entity.Account;

import lombok.Getter;

@Getter
public class CustomeOidcUser extends DefaultOidcUser {

    private final String localUserId;

    public CustomeOidcUser(
        Collection<? extends GrantedAuthority> authorities,
        OidcIdToken token,
        OidcUserInfo userInfo,
        Account account) {
        super(authorities, token, userInfo);
        this.localUserId = account.getUserId();
    }
}
