package dev.elshan.lms.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuizCreateRequestDto {

    String quizName;
    Set<Question> questions;
}

class Question{
    String name;
    Set<QuestionChoice> questionChoices;
}

class QuestionChoice{
    String name;
    boolean isCorrectChoice = false;
}
