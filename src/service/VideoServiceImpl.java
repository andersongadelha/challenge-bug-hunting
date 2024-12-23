package service;

import model.Video;
import repository.VideoRepository;
import repository.VideoRepositoryImpl;
import strategy.SearchStrategy;
import strategy.SearchStrategyImpl;

import java.util.List;

public class VideoServiceImpl implements VideoService {
    private static final String ARQUIVO_VIDEOS = "videos.txt";
    private final VideoRepository repository;
    SearchStrategy searchStrategy = new SearchStrategyImpl();

    public VideoServiceImpl() {
        this.repository = new VideoRepositoryImpl(ARQUIVO_VIDEOS);
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