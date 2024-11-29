package dev.elshan.lms.controller;

import dev.elshan.lms.dto.request.QuestionCreateRequestDTO;
import dev.elshan.lms.model.Question;
import dev.elshan.lms.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/question")
public class QuestionController {
    private final QuestionService service;

    @GetMapping("/{id}")
    public Question getQuestion(@PathVariable Long id) {
        return service.getQuestion(id);
    }

    @GetMapping
    public List<Question> getAllQuestions() {
        return service.getQuestions();
    }

    @PostMapping
    public void addQuestion(@RequestBody QuestionCreateRequestDTO request) {
        service.saveQuestion(request);
    }
}
