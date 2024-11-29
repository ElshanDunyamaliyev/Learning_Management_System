package dev.elshan.lms.service.impl;

import dev.elshan.lms.dto.request.QuestionCreateRequestDTO;
import dev.elshan.lms.model.Question;
import dev.elshan.lms.repository.QuestionRepository;
import dev.elshan.lms.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository repository;

    @Override
    public Question getQuestion(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND,"No question found with given id " + id));
    }

    @Override
    public List<Question> getQuestions() {
        return repository.findAll();
    }

    @Override
    public void saveQuestion(QuestionCreateRequestDTO request) {
        var newQuestion = Question.builder()
                .name(request.getQuestionName())
                .build();
        repository.save(newQuestion);
    }

    @Override
    public void updateQuestion(Long questionId, Question question) {
        var oldQuestion = getQuestion(questionId);
        oldQuestion.setName(question.getName());
        repository.save(oldQuestion);
    }

    @Override
    public void deleteQuestion(Long id) {
        repository.deleteById(id);
    }
}
