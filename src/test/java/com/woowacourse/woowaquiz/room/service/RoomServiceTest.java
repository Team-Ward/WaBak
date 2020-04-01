package com.woowacourse.woowaquiz.room.service;

import com.woowacourse.woowaquiz.generic.BaseEntity;
import com.woowacourse.woowaquiz.quiz.domain.model.Quiz;
import com.woowacourse.woowaquiz.quiz.domain.repository.QuizRepository;
import com.woowacourse.woowaquiz.room.domain.model.Room;
import com.woowacourse.woowaquiz.room.domain.repository.RoomRepository;
import com.woowacourse.woowaquiz.room.service.dto.RoomSaveDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class RoomServiceTest {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private RoomService roomService;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
        roomRepository.deleteAll();
        quizRepository.deleteAll();
    }

    @DisplayName("퀴즈 id 리스트를 Room의 퀴즈로 등록한다.")
    @Test
    void save() {
        //given
        Quiz quiz1 = Quiz.builder()
                .quizType("OX")
                .question("ox quiz question1")
                .solution("O")
                .build();
        Quiz quiz2 = Quiz.builder()
                .quizType("OX")
                .question("ox quiz question2")
                .solution("O")
                .build();
        Quiz quiz3 = Quiz.builder()
                .quizType("OX")
                .question("ox quiz question3")
                .solution("O")
                .build();
        List<Quiz> quizzes = Arrays.asList(quiz1, quiz2, quiz3);
        List<Quiz> quizList = quizRepository.saveAll(quizzes);
        quizRepository.flush();

        List<Long> quizIds = quizList.stream().map(BaseEntity::getId).collect(Collectors.toList());

        RoomSaveDto roomSaveDto = RoomSaveDto.builder()
                .author("bebop")
                .name("오늘 스크럼은 퀴즈")
                .quizIds(quizIds)
                .build();

        //when
        Long save = roomService.save(roomSaveDto);
        Room room = roomRepository.findByIdFetch(save)
                .orElseThrow(IllegalArgumentException::new);

        //then
        assertThat(room.getQuizzes()).hasSize(3);
    }
}