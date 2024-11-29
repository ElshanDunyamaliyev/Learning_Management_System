package dev.elshan.lms.service;

import dev.elshan.lms.dto.request.QuestionCreateRequestDTO;
import dev.elshan.lms.model.Question;

import java.util.List;

public interface QuestionService {

    Question getQuestion(Long id);
    List<Question> getQuestions();
    void saveQuestion(QuestionCreateRequestDTO request);
    void updateQuestion(Long questionId, Question question);
    void deleteQuestion(Long id);
}
