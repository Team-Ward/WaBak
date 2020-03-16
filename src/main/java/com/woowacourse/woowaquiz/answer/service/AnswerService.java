package com.woowacourse.woowaquiz.answer.service;

import com.woowacourse.woowaquiz.answer.domain.Answer;
import com.woowacourse.woowaquiz.answer.repository.AnswerRepository;
import com.woowacourse.woowaquiz.answer.service.dto.AnswerSaveRequestDto;
import com.woowacourse.woowaquiz.quiz.domain.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final QuizRepository quizRepository;
    private final AnswerRepository answerRepository;

    public Long saveAnswer(AnswerSaveRequestDto answerSaveRequestDto) {
        Answer answer = answerSaveRequestDto.toEntity(quizRepository);
        answer = answerRepository.save(answer);
        return answer.getId();
    }
}
