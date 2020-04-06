package com.woowacourse.woowascrum.answer.service;

import com.woowacourse.woowascrum.answer.domain.model.Answer;
import com.woowacourse.woowascrum.answer.domain.repository.AnswerRepository;
import com.woowacourse.woowascrum.answer.service.dto.AnswerSaveRequestDto;
import com.woowacourse.woowascrum.quiz.domain.model.Quiz;
import com.woowacourse.woowascrum.quiz.domain.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final QuizRepository quizRepository;
    private final AnswerRepository answerRepository;

    @Transactional
    public Long saveAnswer(AnswerSaveRequestDto answerSaveRequestDto) {
        Quiz quiz = quizRepository.findById(answerSaveRequestDto.getQuizId())
                .orElseThrow(() -> new IllegalArgumentException(String.format("%d :존재하지 않는 퀴즈번호 입니다.", answerSaveRequestDto.getQuizId())));

        Answer answer = answerSaveRequestDto.toEntity(quiz);
        answer = answerRepository.save(answer);

        return answer.getId();
    }
}
