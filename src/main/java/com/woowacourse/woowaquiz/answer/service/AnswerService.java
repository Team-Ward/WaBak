package com.woowacourse.woowaquiz.answer.service;

import com.woowacourse.woowaquiz.answer.domain.Answer;
import com.woowacourse.woowaquiz.answer.domain.AnswerRepository;
import com.woowacourse.woowaquiz.answer.service.dto.AnswerSaveRequestDto;
import com.woowacourse.woowaquiz.quiz.domain.Quiz;
import com.woowacourse.woowaquiz.quiz.domain.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final QuizRepository quizRepository;
    private final AnswerRepository answerRepository;

    public Long saveAnswer(AnswerSaveRequestDto answerSaveRequestDto) {
        Quiz quiz = quizRepository.findById(answerSaveRequestDto.getQuizId())
                .orElseThrow(() -> new IllegalArgumentException(String.format("%d :존재하지 않는 퀴즈번호 입니다.", answerSaveRequestDto.getQuizId())));

        Answer answer = answerSaveRequestDto.toEntity(quiz);
        answer = answerRepository.save(answer);

        return answer.getId();
    }
}
