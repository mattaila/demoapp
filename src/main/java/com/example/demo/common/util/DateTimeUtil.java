package com.example.demo.common.util;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * 日時ユーティリティクラス
 */
public class DateTimeUtil {

    /**
     * 日本標準ZoneID
     */
    private static final ZoneId ZONE_ID = ZoneId.of("JST", ZoneId.SHORT_IDS);

    /**
     * 現在日時をTimestamp型で取得する
     * @return
     */
    public static Timestamp getCurentTimestamp() {
        var now = Instant.now();
        LocalDateTime dateTime = LocalDateTime.ofInstant(now, ZONE_ID);

        return Timestamp.valueOf(dateTime);
    }
}
