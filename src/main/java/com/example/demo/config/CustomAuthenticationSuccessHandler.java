package com.example.demo.config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.common.util.DateTimeUtil;
import com.example.demo.dto.CustomeOidcUser;
import com.example.demo.dto.entity.Account;
import com.example.demo.repository.AccountMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final AccountMapper mapper;

    @Override
    @Transactional
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        CustomeOidcUser userDetails = (CustomeOidcUser) authentication.getPrincipal();
        String userId = userDetails.getLocalUserId();

        Account account = mapper.selectByPrimaryKey(userId);
        account.setLastLogin(DateTimeUtil.getCurentTimestamp());
        mapper.updateByPrimaryKey(account);

        System.out.println(userId + " has logged in!");
        response.sendRedirect("/");
    }


}
