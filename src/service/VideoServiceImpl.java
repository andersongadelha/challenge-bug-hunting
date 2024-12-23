package service;

import model.Video;
import repository.VideoRepository;
import strategy.SearchStrategy;
import strategy.SearchStrategyImpl;

import java.util.List;

public class VideoServiceImpl implements VideoService {
    private final VideoRepository repository;
    SearchStrategy searchStrategy = new SearchStrategyImpl();

    public VideoServiceImpl(VideoRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addVideo(Video video) {
        repository.save(video);
    }

    @Override
    public List<Video> listVideos() {
        return repository.findAll();
    }

    @Override
    public List<Video> searchByTitle(String query) {
        var videos = listVideos();

        return searchStrategy.searchByTitle(videos, query);
    }
}