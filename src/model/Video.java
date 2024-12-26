package model;

import util.LocalDateUtil;

import java.time.LocalDate;

public class Video {
    private String title;
    private String description;
    private int duration;
    private Category category;
    private LocalDate publishDate;

    public Video(String title, String description, int duration, Category category, LocalDate publishDate) {
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.category = category;
        this.publishDate = publishDate;
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

    public static Video fromString(String linha) {
        try {
            String[] parts = linha.split(";");
            return new Video(parts[0], parts[1], Integer.parseInt(parts[2]), Category.deserialize(parts[3]), LocalDateUtil.deserialize(parts[4]));
        } catch (Exception e) {
            return null; // Ignora erros de parsing
        }
    }
}