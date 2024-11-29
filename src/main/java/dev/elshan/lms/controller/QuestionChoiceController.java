package dev.elshan.lms.controller;

import dev.elshan.lms.model.Question;
import dev.elshan.lms.model.QuestionChoice;
import dev.elshan.lms.service.QuestionChoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/question-choice")
public class QuestionChoiceController {
    private final QuestionChoiceService service;

    @GetMapping("/{id}")
    public QuestionChoice getQuestionChoice(@PathVariable Long id) {
        return service.getQuestionChoiceById(id);
    }

    @GetMapping
    public List<QuestionChoice> getAllQuestionChoices() {
        return service.getAllQuestionChoices();
    }

    @PostMapping("/{questionId}")
    public void addQuestionChoice(@PathVariable Long questionId,@RequestBody List<QuestionChoice> questionChoices) {
        service.saveQuestionChoice(questionId,questionChoices);
    }
}
