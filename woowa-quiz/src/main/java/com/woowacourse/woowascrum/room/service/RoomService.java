package com.woowacourse.woowascrum.room.service;

import com.woowacourse.woowascrum.quiz.domain.model.Quiz;
import com.woowacourse.woowascrum.quiz.domain.repository.QuizRepository;
import com.woowacourse.woowascrum.room.domain.model.Room;
import com.woowacourse.woowascrum.room.domain.repository.RoomRepository;
import com.woowacourse.woowascrum.room.service.dto.RoomBundleResponseDto;
import com.woowacourse.woowascrum.room.service.dto.RoomResponseDto;
import com.woowacourse.woowascrum.room.service.dto.RoomSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;
    private final QuizRepository quizRepository;

    @Transactional
    public Long save(RoomSaveDto roomSaveDto) {
        Room room = roomSaveDto.getQuizIds().stream()
                .map(this::findQuiz)
                .collect(collectingAndThen(toList(), roomSaveDto::toEntity));

        room = roomRepository.save(room);
        return room.getId();
    }

    private Quiz findQuiz(Long quizId) {
        return quizRepository.findById(quizId)
                .orElseThrow(IllegalArgumentException::new);
    }

    public RoomBundleResponseDto findAll() {
        return roomRepository.findAll().stream()
                .map(RoomResponseDto::of)
                .collect(collectingAndThen(toList(), RoomBundleResponseDto::new));
    }
}
