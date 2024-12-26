package repository;

import model.Video;
import util.LocalDateUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VideoRepositoryImpl implements VideoRepository {
    private final File file;

    public VideoRepositoryImpl(String filePath) {
        this.file = new File(filePath);
    }

    @Override
    public void save(Video video) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.write(video.getId() + ";" + video.getTitle() + ";" + video.getDescription() + ";" + video.getDuration() + ";" + video.getCategory() + ";" + LocalDateUtil.serialize(video.getPublishDate()));
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Erro ao salvar video.");
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
            System.out.println("Erro ao obter videos.");
        }
        return videos;
    }

    @Override
    public int getLastId() {
        List<Video> videos = findAll();

        return videos.size();
    }
}