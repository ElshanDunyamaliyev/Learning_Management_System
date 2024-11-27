package dev.elshan.lms.repository;

import dev.elshan.lms.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {
}
