package service;

import model.Video;

import java.util.List;

public interface VideoService {
    void addVideo();
    void listVideos();
    List<Video> searchByTitle(String query);
}