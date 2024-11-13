package com.example.demo.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    /**
     * 初期表示
     * @param mav
     * @return
     */
    @GetMapping("/init")
    public ModelAndView itemListView(ModelAndView mav, Pageable pageable) {

        mav.setViewName("account/list");

        return mav;
    }
}
