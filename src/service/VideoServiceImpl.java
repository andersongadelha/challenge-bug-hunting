package service;

import model.Category;
import model.Video;
import repository.VideoRepository;
import repository.VideoRepositoryImpl;
import strategy.SearchStrategy;
import strategy.SearchStrategyImpl;
import util.InputUtil;
import static util.InputUtil.getCategory;
import static util.InputUtil.getNonEmptyInput;
import static util.InputUtil.getPositiveInteger;
import static util.LocalDateUtil.serialize;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

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

        System.out.print("Digite a categoria do vídeo dentre as categorias disponíveis ");
        System.out.print("( ");
        Arrays.stream(Category.values()).forEach(category -> System.out.print(category.name() + " "));
        System.out.print("): ");
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
    public void listVideosOrderByDate() {
        List<Video> videos = repository.findAll();
        List<Video> orderedVideos = searchStrategy.orderByDate(videos);
        orderedVideos.forEach(this::showDetails);
    }

    @Override
    public void searchByTitle(String query) {
        List<Video> videos = repository.findAll();
        List<Video> filteredVideos = searchStrategy.searchByTitle(videos, query);
        filteredVideos.forEach(this::showDetails);
    }

    @Override
    public void searchByCategory(Category query) {
        List<Video> videos = repository.findAll();
        List<Video> filteredVideos = searchStrategy.searchByCategory(videos, query);
        filteredVideos.forEach(this::showDetails);
    }

    @Override
    public void editVideo(int id) {
        Optional<Video> optionalVideo = repository.findById(id);
        optionalVideo.ifPresentOrElse(
                video -> {
                    boolean editing = true;
                    while (editing) {
                        System.out.println("Editando vídeo:");
                        System.out.println("1. Título: " + video.getTitle());
                        System.out.println("2. Descrição: " + video.getDescription());
                        System.out.println("3. Duração: " + video.getDuration());
                        System.out.println("4. Categoria: " + video.getCategory());
                        System.out.println("5. Data de publicação: " + serialize(video.getPublishDate()));
                        System.out.println("6. Salvar e sair");
                        System.out.println("Escolha o número do atributo que deseja alterar:");

                        int choice = InputUtil.getPositiveInteger(scanner);

                        switch (choice) {
                            case 1 -> {
                                System.out.println("Digite a nova alteração para título:");
                                String newTitle = InputUtil.getNonEmptyInput(scanner);
                                video.setTitle(newTitle);
                            }
                            case 2 -> {
                                System.out.println("Digite a nova alteração para descrição:");
                                String newDescription = InputUtil.getNonEmptyInput(scanner);
                                video.setDescription(newDescription);
                            }
                            case 3 -> {
                                System.out.println("Digite a nova alteração para duração:");
                                int newDuration = InputUtil.getPositiveInteger(scanner);
                                video.setDuration(newDuration);
                            }
                            case 4 -> {
                                System.out.println("Digite a nova alteração para categoria:");
                                Category newCategory = InputUtil.getCategory(scanner);
                                video.setCategory(newCategory);
                            }
                            case 5 -> {
                                System.out.println("Digite a nova alteração para a data de publicação:");
                                LocalDate newPublishDate = InputUtil.getLocalDate(scanner);
                                video.setPublishDate(newPublishDate);
                            }
                            case 6 -> {
                                repository.update(id, video.getTitle(), video.getDescription(), video.getDuration(),
                                        video.getCategory(), video.getPublishDate());
                                System.out.println("Vídeo atualizado com sucesso.");
                                editing = false;
                            }
                            default -> System.out.println("Opção inválida. Tente novamente.");
                        }
                    }
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

    @Override
    public void showStatistics() {
        List<Video> videos = repository.findAll();
        int totalDuration = videos.stream().mapToInt(Video::getDuration).sum();
        System.out.println("\n=== Relatório de estatísticas ===");
        System.out.println("Quantidade de vídeos: " + videos.size());
        System.out.println("Duração total dos vídeos: " + totalDuration + " minutos");
        System.out.println("Quantidade de vídeos por categoria");
        System.out.println("DOCUMENTARIO: " + searchStrategy.searchByCategory(videos, Category.DOCUMENTARIO).size());
        System.out.println("FILME: " + searchStrategy.searchByCategory(videos, Category.FILME).size());
        System.out.println("SERIE: " + searchStrategy.searchByCategory(videos, Category.SERIE).size());
    }

    private void showDetails(Video video) {
        System.out.println("Id:" + video.getId());
        System.out.println("Titulo: " + video.getTitle());
        System.out.println("Descrição: " + video.getDescription());
        System.out.println("Duração: " + video.getDuration() + " minutos");
        System.out.println("Categoria: " + video.getCategory());
        System.out.println("Data de publicação: " + serialize(video.getPublishDate()));
        System.out.println();
    }
}