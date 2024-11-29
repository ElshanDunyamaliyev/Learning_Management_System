package dev.elshan.lms.service;

import dev.elshan.lms.model.QuestionChoice;

import java.util.List;

public interface QuestionChoiceService {

    QuestionChoice getQuestionChoiceById(Long questionChoiceId);
    QuestionChoice getQuestionChoiceByName(String quizName);
    List<QuestionChoice> getAllQuestionChoices();
    void saveQuestionChoice(Long questionId, List<QuestionChoice> questionChoices);
    void updateQuestionChoice(long questionChoiceId, QuestionChoice questionChoice);
    void deleteQuestionChoice(Long questionChoiceId);
}
