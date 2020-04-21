package com.woowacourse.woowascrum.generic.common;

import java.time.LocalDateTime;
import java.time.ZoneId;

public final class LocalDateTimeUtils {
    private LocalDateTimeUtils() {
    }

    public static LocalDateTime now(LocalDateTime localDateTime) {
        return localDateTime == null ? LocalDateTime.now(ZoneId.of("GMT+9")) : localDateTime;
    }
}
