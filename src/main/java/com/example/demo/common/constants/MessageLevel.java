package com.example.demo.common.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * メッセージレベルEnum
 */
@Getter
@AllArgsConstructor
public enum MessageLevel {
    INFO("I"),
    WARNING("W"),
    ERROR("E");
    
    final String value;
}
