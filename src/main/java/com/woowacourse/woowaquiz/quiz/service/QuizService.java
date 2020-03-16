package com.woowacourse.woowaquiz.quiz.service;

import com.woowacourse.woowaquiz.quiz.domain.Quiz;
import com.woowacourse.woowaquiz.quiz.domain.QuizRepository;
import com.woowacourse.woowaquiz.quiz.service.dto.QuizSaveRequestDto;
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
