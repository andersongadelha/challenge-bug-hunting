package service;

import model.Category;

public interface VideoService {
    void addVideo();
    void listVideos();
    void listVideosOrderByDate();
    void searchByTitle(String query);
    void searchByCategory(Category query);
    void editVideo(int id);
    void removeById(int id);
}