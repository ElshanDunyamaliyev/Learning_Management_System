package dev.elshan.lms.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionChoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "is_correct_choice")
    private Boolean isCorrectChoice = false;

    @ManyToOne
    @JoinColumn(name = "question_id",nullable = false)
    private Question question;
}
