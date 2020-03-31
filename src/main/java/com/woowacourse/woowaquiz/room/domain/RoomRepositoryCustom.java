package com.woowacourse.woowaquiz.room.domain;

import java.util.Optional;

public interface RoomRepositoryCustom {
    Optional<Room> findByIdFetch(Long id);
}
