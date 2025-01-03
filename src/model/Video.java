package model;

import util.LocalDateUtil;
import static util.LocalDateUtil.serialize;

import java.time.LocalDate;

public class Video {
    private int id;
    private String title;
    private String description;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    private int duration;
    private Category category;
    private LocalDate publishDate;

    public Video(int id, String title, String description, int duration, Category category, LocalDate publishDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.category = category;
        this.publishDate = publishDate;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getDuration() {
        return duration;
    }

    public Category getCategory() {
        return category;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    @Override
    public String toString() {
        return id + ";" + title + ";" + description + ";" + duration + ";" + category + ";" + serialize(publishDate);
    }

    public static Video fromString(String linha) {
        try {
            String[] parts = linha.split(";");
            return new Video(Integer.parseInt(parts[0]), parts[1], parts[2], Integer.parseInt(parts[3]), Category.deserialize(parts[4]), LocalDateUtil.deserialize(parts[5]));
        } catch (Exception e) {
            System.out.println("Erro ao desserializar vídeo.");
            return null;
        }
    }
}