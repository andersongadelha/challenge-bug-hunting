package strategy;

import model.Video;

import java.util.List;
import java.util.stream.Collectors;

public class SearchStrategyImpl implements SearchStrategy {
    @Override
    public List<Video> searchByTitle(List<Video> videos, String query) {
        return videos.stream()
                .filter(video -> video.getTitle().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }
}