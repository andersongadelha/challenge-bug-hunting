package strategy;

import model.Video;

import java.util.List;

public interface SearchStrategy {
    List<Video> searchByTitle(List<Video> videos, String query);
}