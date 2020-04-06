package com.woowacourse.woowascrum.room.service.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoomBundleResponseDto {
    private List<RoomResponseDto> responseDtos;

    public RoomBundleResponseDto(List<RoomResponseDto> responseDtos) {
        this.responseDtos = responseDtos;
    }

    public int size() {
        return this.responseDtos.size();
    }

    public List<RoomResponseDto> getResponseDtos() {
        return new ArrayList<>(responseDtos);
    }
}
