package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dto.entity.Item;
import com.example.demo.dto.entity.ItemExample;
import com.example.demo.dto.form.item.ItemSearchForm;
import com.example.demo.exceptions.OffsetLimitOverException;
import com.example.demo.service.ItemService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

/**
 * 商品関連Controllerクラス
 */
@Controller
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService service;

    private final HttpSession session;

    private final MessageSource messageSource;

    /**
     * Offset値の規定上限
     */
    @Value("${demo.offset-limit}")
    private long offsetLimit;

    /**
     * 初期表示
     * @param mav
     * @return
     */
    @GetMapping("/init")
    public ModelAndView itemListView(ModelAndView mav, Pageable pageable) {

        session.setAttribute("previous_page", pageable);

        Page<Item> itemPage = service.findPageByExample(pageable, new ItemExample());
        mav.setViewName("item/list");
        mav.addObject("itemSearchForm", new ItemSearchForm());
        mav.addObject("page", itemPage);
        mav.addObject("itemList", itemPage.getContent());
        return mav;
    }

    @PostMapping("/search")
    public ModelAndView search(ModelAndView mav, Pageable pageable,
        @ModelAttribute("itemSearchForm") @Validated ItemSearchForm itemSearchForm, BindingResult result) {

        if(result.hasErrors()) {
            List<ObjectError> error = result.getGlobalErrors();
            error.forEach(e -> System.out.println(e.getCode()));
            //error.forEach(e -> System.out.println(e.getArguments()[1]));
            //error.forEach(e -> System.out.println(e.getArguments()[2]));
            //String message = messageSource.getMessage("null", null, Locale.JAPAN);
            mav.addObject("message","error");
            mav.setViewName("/item/list");
            mav.addObject("itemSearchForm", itemSearchForm);
            mav.addObject("page", null);
            mav.addObject("itemList", null);
            return mav;
        }

        Page<Item> itemPage = service.findPageByExample(pageable, new ItemExample());
        mav.setViewName("item/list");
        mav.addObject("itemSearchForm", itemSearchForm);
        mav.addObject("page", itemPage);
        mav.addObject("itemList", itemPage.getContent());

        return mav;
    }

    /**
     * ページネーション
     * @param mav
     * @param pageable
     * @return
     * @throws OffsetLimitOverException 
     */
    @GetMapping("/pagination")
    public ModelAndView pagination(ModelAndView mav, Pageable pageable) {
        
        if(pageable.getOffset() > offsetLimit) {
            Pageable previousPageable = (Pageable)session.getAttribute("previous_page");
            if(previousPageable != null) {
                Page<Item> itemPage = service.findPageByExample(previousPageable, new ItemExample());
                mav.setViewName("item/list");

                mav.addObject("message", "keikoku");
                mav.addObject("page", itemPage);
                mav.addObject("itemList", itemPage.getContent());
                return mav;
            }
            //throw new OffsetLimitOverException("");
        }

        session.setAttribute("previous_page", pageable);

        Page<Item> itemPage = service.findPageByExample(pageable, new ItemExample());
        mav.setViewName("item/list");
        mav.addObject("page", itemPage);
        mav.addObject("itemList", itemPage.getContent());
        return mav;
    }
}
