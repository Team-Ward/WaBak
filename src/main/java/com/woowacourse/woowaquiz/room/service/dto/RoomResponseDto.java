package com.woowacourse.woowaquiz.room.service.dto;

import com.woowacourse.woowaquiz.room.domain.model.Room;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoomResponseDto {
    private Long id;
    private String title;
    private String author;

    @Builder
    public RoomResponseDto(Long id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public static RoomResponseDto of(Room room) {
        return RoomResponseDto.builder()
                .id(room.getId())
                .title(room.getName())
                .author(room.getAuthor())
                .build();
    }
}
