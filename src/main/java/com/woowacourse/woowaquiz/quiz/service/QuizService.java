package com.woowacourse.woowaquiz.quiz.service;

import com.woowacourse.woowaquiz.quiz.domain.Quiz;
import com.woowacourse.woowaquiz.quiz.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final QuizRepository quizRepository;


    public Long saveQuiz(Quiz quiz) {
        quiz = quizRepository.save(quiz);
        return quiz.getId();
    }
}
