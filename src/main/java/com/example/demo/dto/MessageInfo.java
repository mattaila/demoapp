package com.example.demo.dto;

import com.example.demo.common.constants.MessageLevel;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * メッセージ情報DTOクラス
 */
@Data
@AllArgsConstructor
public class MessageInfo {

    private MessageLevel level;
    private String message;
}
