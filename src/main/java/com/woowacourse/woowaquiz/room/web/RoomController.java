package com.woowacourse.woowaquiz.room.web;

import com.woowacourse.woowaquiz.room.service.RoomService;
import com.woowacourse.woowaquiz.room.service.dto.RoomSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/room")
public class RoomController {

    private final RoomService roomService;

    @PostMapping
    public ResponseEntity<Long> saveRoom(@RequestBody RoomSaveDto roomSaveDto) {
        Long saveId = roomService.save(roomSaveDto);
        return new ResponseEntity<>(saveId, HttpStatus.CREATED);
    }
}
