package strategy;

import model.Category;
import model.Video;

import java.util.Comparator;
import java.util.List;

public class SearchStrategyImpl implements SearchStrategy {
    @Override
    public List<Video> searchByTitle(List<Video> videos, String query) {
        return videos.stream()
                .filter(video -> video.getTitle().toLowerCase().contains(query.toLowerCase()))
                .toList();
    }

    @Override
    public List<Video> searchByCategory(List<Video> videos, Category query) {
        return videos.stream()
                .filter(video -> video.getCategory().equals(query))
                .toList();
    }

    @Override
    public List<Video> orderByDate(List<Video> videos) {
        return videos.stream()
                .sorted(Comparator.comparing(Video::getPublishDate))
                .toList();
    }
}