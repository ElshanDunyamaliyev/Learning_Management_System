package dev.elshan.lms.service;

import dev.elshan.lms.model.Quiz;

import java.util.List;

public interface QuizService {

    List<Quiz> getAllQuizzes();
    Quiz getQuizById(Long id);
    Quiz createQuiz(Quiz quiz);
    Quiz updateQuiz(Long quizId, Quiz quiz);
    void deleteQuiz(Long quizId);
}
