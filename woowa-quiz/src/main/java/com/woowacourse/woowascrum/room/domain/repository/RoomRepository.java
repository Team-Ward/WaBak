package com.woowacourse.woowascrum.room.domain.repository;

import com.woowacourse.woowascrum.room.domain.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long>, RoomRepositoryCustom {
}
