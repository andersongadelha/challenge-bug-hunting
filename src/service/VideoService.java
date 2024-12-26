package service;

public interface VideoService {
    void addVideo();
    void listVideos();
    void searchByTitle(String query);
    void editVideo(int id);
    void removeById(int id);
}