package com.woowacourse.woowaquiz.room.domain.repository;

import com.woowacourse.woowaquiz.room.domain.model.Room;

import java.util.Optional;

public interface RoomRepositoryCustom {
    Optional<Room> findByIdFetch(Long id);
}
