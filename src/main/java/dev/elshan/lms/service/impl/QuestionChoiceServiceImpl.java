package dev.elshan.lms.service.impl;

import dev.elshan.lms.model.Question;
import dev.elshan.lms.model.QuestionChoice;
import dev.elshan.lms.repository.QuestionChoiceRepository;
import dev.elshan.lms.service.QuestionChoiceService;
import dev.elshan.lms.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class QuestionChoiceServiceImpl implements QuestionChoiceService {
    private final QuestionChoiceRepository repository;
    private final QuestionService questionService;

    @Override
    public QuestionChoice getQuestionChoiceById(Long questionChoiceId) {
        return repository.findById(questionChoiceId).orElseThrow(() -> new ResponseStatusException(NOT_FOUND,"No question choice with id " + questionChoiceId));
    }

    @Override
    public QuestionChoice getQuestionChoiceByName(String quizName) {
        // Todo
        return null;
    }

    @Override
    public List<QuestionChoice> getAllQuestionChoices() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public void saveQuestionChoice(Long questionId, List<QuestionChoice> questionChoices) {
        var question = questionService.getQuestion(questionId);
        questionChoices.forEach(questionChoice -> {
            repository.save(questionChoice);
            question.getQuestionChoices().add(questionChoice);
        });
        questionService.updateQuestion(questionId,question);
    }

    @Override
    public void updateQuestionChoice(long questionChoiceId, QuestionChoice questionChoice) {
        var oldQuestionChoice = getQuestionChoiceById(questionChoiceId);
        oldQuestionChoice.setName(questionChoice.getName());
        repository.save(oldQuestionChoice);
    }

    @Override
    public void deleteQuestionChoice(Long questionChoiceId) {
        repository.deleteById(questionChoiceId);
    }
}
