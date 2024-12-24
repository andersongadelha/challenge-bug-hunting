package service;

import model.Video;

import java.util.List;

public interface VideoService {
    void addVideo();
    List<Video> listVideos();
    List<Video> searchByTitle(String query);
}