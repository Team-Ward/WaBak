package com.woowacourse.woowaquiz.room.domain.repository;

import com.woowacourse.woowaquiz.room.domain.model.Room;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.Optional;

import static com.woowacourse.woowaquiz.quiz.domain.QQuiz.quiz;
import static com.woowacourse.woowaquiz.room.domain.model.QRoom.room;

public class RoomRepositoryImpl extends QuerydslRepositorySupport implements RoomRepositoryCustom {
    public RoomRepositoryImpl() {
        super(Room.class);
    }

    @Override
    public Optional<Room> findByIdFetch(Long id) {
        return Optional.ofNullable(super.from(room)
                .innerJoin(room.quizzes, quiz).fetchJoin()
                .where(room.id.eq(id))
                .fetchOne());
    }

}
