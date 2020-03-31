package com.woowacourse.woowaquiz.room.service;

import com.woowacourse.woowaquiz.quiz.domain.Quiz;
import com.woowacourse.woowaquiz.quiz.domain.QuizRepository;
import com.woowacourse.woowaquiz.room.domain.model.Room;
import com.woowacourse.woowaquiz.room.domain.repository.RoomRepository;
import com.woowacourse.woowaquiz.room.service.dto.RoomSaveDto;
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
}
