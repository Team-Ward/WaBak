package com.woowacourse.woowaquiz.answer.web;

import com.woowacourse.woowaquiz.answer.service.AnswerService;
import com.woowacourse.woowaquiz.answer.service.dto.AnswerSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/answer")
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerService answerService;

    @PostMapping
    public ResponseEntity<Long> saveAnswer(@RequestBody AnswerSaveRequestDto answerSaveRequestDto) {
        return new ResponseEntity<>(answerService.saveAnswer(answerSaveRequestDto), HttpStatus.CREATED);
    }
}
