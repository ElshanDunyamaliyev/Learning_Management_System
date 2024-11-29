package dev.elshan.lms.repository;

import dev.elshan.lms.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
