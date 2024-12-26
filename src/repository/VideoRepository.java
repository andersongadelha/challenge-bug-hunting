package repository;

import model.Category;
import model.Video;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface VideoRepository {
    void save(Video video);
    List<Video> findAll();
    int getLastId();
    Optional<Video> findById(int id);
    void update(int id, String title, String description, int duration, Category category, LocalDate publishDate);
}