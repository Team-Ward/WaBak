package com.woowacourse.woowaquiz.room.service.dto;

import com.woowacourse.woowaquiz.quiz.domain.model.Quiz;
import com.woowacourse.woowaquiz.room.domain.model.Room;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoomSaveDto {
    private String name;
    private String author;
    private List<Long> quizIds;

    @Builder
    public RoomSaveDto(String name, String author, List<Long> quizIds) {
        this.name = name;
        this.author = author;
        this.quizIds = quizIds;
    }

    public Room toEntity(List<Quiz> quizzes) {
        return Room.builder()
                .author(this.author)
                .name(this.name)
                .quizzes(quizzes)
                .build();
    }
}
