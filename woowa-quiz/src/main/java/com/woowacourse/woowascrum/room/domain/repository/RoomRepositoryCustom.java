package com.woowacourse.woowascrum.room.domain.repository;

import com.woowacourse.woowascrum.room.domain.model.Room;

import java.util.Optional;

public interface RoomRepositoryCustom {
    Optional<Room> findByIdFetch(Long id);
}
