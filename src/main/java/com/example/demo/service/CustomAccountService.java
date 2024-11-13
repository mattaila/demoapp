package com.example.demo.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CustomeOidcUser;
import com.example.demo.dto.entity.Account;
import com.example.demo.repository.AccountMapper;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@Service
public class CustomAccountService extends OidcUserService {

    private final AccountMapper mapper;

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        var user = super.loadUser(userRequest);
        String userName = user.getPreferredUsername();
        Optional<Account> account = Optional.of(mapper.selectByPrimaryKey(userName));

        List<SimpleGrantedAuthority> authList = Arrays.asList(
            new SimpleGrantedAuthority(
                account.orElseThrow(() -> new UsernameNotFoundException(userName)).getUserRole()));

        return new CustomeOidcUser(authList, userRequest.getIdToken(), user.getUserInfo(), account.get());
    }
    
}
