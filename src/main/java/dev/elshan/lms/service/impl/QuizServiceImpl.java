package dev.elshan.lms.service.impl;

import dev.elshan.lms.model.Quiz;
import dev.elshan.lms.repository.QuizRepository;
import dev.elshan.lms.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {
    private final QuizRepository repository;

    @Override
    public List<Quiz> getAllQuizzes() {
        return repository.findAll();
    }

    @Override
    public Quiz getQuizById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND,"No quiz found with given id " + id));
    }

    @Override
    public Quiz createQuiz(Quiz quiz) {
        return repository.save(quiz);
    }

    @Override
    public Quiz updateQuiz(Long quizId, Quiz quiz) {
        return null;
    }

    @Override
    public void deleteQuiz(Long quizId) {

    }
}
