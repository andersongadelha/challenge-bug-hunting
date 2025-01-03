package repository;

import model.Category;
import model.Video;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VideoRepositoryImpl implements VideoRepository {
    private final File file;

    public VideoRepositoryImpl(String filePath) {
        this.file = new File(filePath);
    }

    @Override
    public void save(Video video) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.write(video.toString());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Erro ao salvar vídeo.");
        }
    }

    @Override
    public List<Video> findAll() {
        List<Video> videos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                Video video = Video.fromString(line);
                if (video != null) {
                    videos.add(video);
                }
            }
        } catch (IOException e) {
            System.out.println("Não existem vídeos cadastrados.");
        }
        return videos;
    }

    @Override
    public int getLastId() {
        List<Video> videos = findAll();
        return videos.stream().mapToInt(Video::getId).max().orElse(0);
    }

    @Override
    public Optional<Video> findById(int id) {
        List<Video> videos = findAll();

        return videos.stream().filter(video -> video.getId() == id).findFirst();
    }

    @Override
    public void update(int id, String title, String description, int duration, Category category, LocalDate publishDate) {
        List<Video> videos = findAll();
        for (Video video : videos) {
            if (video.getId() == id) {
                video.setTitle(title);
                video.setDescription(description);
                video.setDuration(duration);
                video.setCategory(category);
                video.setPublishDate(publishDate);
            }
        }
        saveVideos(videos);
    }

    @Override
    public void remove(Video videoForRemove) {
        List<Video> videos = findAll();
        videos.removeIf(video -> video.getId() == videoForRemove.getId());
        saveVideos(videos);
    }

    private void saveVideos(List<Video> videos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (Video video : videos) {
                bw.write(video.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar vídeos");
        }
    }
}