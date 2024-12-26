package service;

import model.Category;
import model.Video;
import repository.VideoRepository;
import repository.VideoRepositoryImpl;
import strategy.SearchStrategy;
import strategy.SearchStrategyImpl;
import util.InputUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static util.InputUtil.getCategory;
import static util.InputUtil.getNonEmptyInput;
import static util.InputUtil.getPositiveInteger;
import static util.LocalDateUtil.serialize;

public class VideoServiceImpl implements VideoService {
    private static final String VIDEOS_FILE = "videos.txt";
    private final VideoRepository repository;
    private final Scanner scanner;
    private final SearchStrategy searchStrategy;

    public VideoServiceImpl(Scanner scanner) {
        this.repository = new VideoRepositoryImpl(VIDEOS_FILE);
        this.scanner = scanner;
        this.searchStrategy = new SearchStrategyImpl();
    }

    @Override
    public void addVideo() {
        System.out.print("Digite o título do vídeo: ");
        String title = getNonEmptyInput(scanner);

        System.out.print("Digite a descrição do vídeo: ");
        String description = getNonEmptyInput(scanner);

        System.out.print("Digite a duração do vídeo (em minutos): ");
        int duration = getPositiveInteger(scanner);

        System.out.print("Digite a categoria do vídeo: ");
        Category category = getCategory(scanner);

        System.out.print("Digite a data de publicação (dd/MM/aaaa): ");
        LocalDate date = InputUtil.getLocalDate(scanner);
        int nextId = repository.getLastId() + 1;

        Video video = new Video(nextId, title, description, duration, category, date);
        repository.save(video);
        System.out.println("Vídeo adicionado com sucesso!");
    }

    @Override
    public void listVideos() {
        List<Video> videos = repository.findAll();
        videos.forEach(this::showDetails);
    }

    @Override
    public void searchByTitle(String query) {
        List<Video> videos = repository.findAll();
        List<Video> videosFiltrados = searchStrategy.searchByTitle(videos, query);
        videosFiltrados.forEach(this::showDetails);
    }

    @Override
    public void searchByCategory(Category query) {
        List<Video> videos = repository.findAll();
        List<Video> videosFiltrados = searchStrategy.searchByCategory(videos, query);
        videosFiltrados.forEach(this::showDetails);
    }

    @Override
    public void editVideo(int id) {
        Optional<Video> optionalVideo = repository.findById(id);
        optionalVideo.ifPresentOrElse(
                video -> {
                    System.out.println("Editando vídeo:");
                    System.out.println("Titulo: " + video.getTitle());
                    System.out.println("Digite a nova alteração para titulo:");
                    String newTitle = InputUtil.getNonEmptyInput(scanner);
                    System.out.println("Descrição: " + video.getDescription());
                    System.out.println("Digite a nova alteração para descrição:");
                    String newDescription = InputUtil.getNonEmptyInput(scanner);
                    System.out.println("Duração: " + video.getDuration());
                    System.out.println("Digite a nova alteração para duração:");
                    int newDuration = InputUtil.getPositiveInteger(scanner);
                    System.out.println("Categoria: " + video.getCategory());
                    System.out.println("Digite a nova alteração para categoria:");
                    Category newCategory = InputUtil.getCategory(scanner);
                    System.out.println("Data de publicação: " + serialize(video.getPublishDate()));
                    System.out.println("Digite a nova alteração para a data de publicação:");
                    LocalDate newPublishDate = InputUtil.getLocalDate(scanner);

                    repository.update(id, newTitle, newDescription, newDuration, newCategory, newPublishDate);
                    System.out.println("Vídeo atualizado com sucesso.");
                },
                () -> System.out.println("Não foi encontrado um vídeo com id " + id));
    }

    @Override
    public void removeById(int id) {
        Optional<Video> optionalVideo = repository.findById(id);
        optionalVideo.ifPresentOrElse(
                video -> {
                    repository.remove(video);
                    System.out.println("Vídeo excluído com sucesso.");
                },
                () -> System.out.println("Não foi encontrado um vídeo com id " + id));
    }

    private void showDetails(Video video) {
        System.out.println("Id:" + video.getId());
        System.out.println("Titulo: " + video.getTitle());
        System.out.println("Descrição: " + video.getDescription());
        System.out.println("Duração: " + video.getDuration());
        System.out.println("Categoria: " + video.getCategory());
        System.out.println("Data de publicação: " + serialize(video.getPublishDate()));
        System.out.println();
    }
}