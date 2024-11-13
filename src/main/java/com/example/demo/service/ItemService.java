package com.example.demo.service;

import java.util.Collections;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dto.entity.Item;
import com.example.demo.dto.entity.ItemExample;
import com.example.demo.repository.ItemMapper;

import lombok.RequiredArgsConstructor;

/**
 * Itemサービス
 */
@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemMapper mapper;

    public Page<Item> findPageByExample(Pageable pageable, ItemExample example) {
        long count = mapper.countByExample(example);
        List<Item> recordList;

        if(count > 0) {
            recordList = mapper.selectByExample(example, pageable);
        } else {
            recordList = Collections.emptyList();
        }
        return new PageImpl<>(recordList, pageable, count);
    }

}
