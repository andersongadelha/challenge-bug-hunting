package strategy;

import model.Category;
import model.Video;

import java.util.List;

public interface SearchStrategy {
    List<Video> searchByTitle(List<Video> videos, String query);
    List<Video> searchByCategory(List<Video> videos, Category query);
    List<Video> orderByDate(List<Video> videos);
}