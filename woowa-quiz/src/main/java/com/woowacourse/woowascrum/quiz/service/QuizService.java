package com.woowacourse.woowascrum.quiz.service;

import com.woowacourse.woowascrum.quiz.domain.model.Quiz;
import com.woowacourse.woowascrum.quiz.domain.repository.QuizRepository;
import com.woowacourse.woowascrum.quiz.service.dto.QuizSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final QuizRepository quizRepository;


    @Transactional
    public Long saveQuiz(QuizSaveRequestDto quizSaveRequestDto) {
        Quiz quiz = quizSaveRequestDto.toEntity();
        quiz = quizRepository.save(quiz);
        return quiz.getId();
    }
}
