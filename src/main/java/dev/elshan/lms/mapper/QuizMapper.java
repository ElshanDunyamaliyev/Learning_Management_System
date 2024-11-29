package dev.elshan.lms.mapper;


import dev.elshan.lms.model.Quiz;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuizMapper {

    Quiz mapToEntity(Quiz quiz);
}
