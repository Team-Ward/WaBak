package com.woowacourse.woowaquiz.room.domain.repository;

import com.woowacourse.woowaquiz.room.domain.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long>, RoomRepositoryCustom {
}
