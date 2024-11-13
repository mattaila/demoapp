package com.example.demo.common.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 削除フラグEnumクラス
 */
@Getter
@AllArgsConstructor
public enum DeleteFlg {

    NOT_DELETED((short)0),
    DELETED((short)1);

    private final short value;
}
