package service;

import model.Categoria;
import model.Video;
import repository.VideoRepository;
import repository.VideoRepositoryImpl;
import strategy.SearchStrategy;
import strategy.SearchStrategyImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static util.InputUtil.*;

public class VideoServiceImpl implements VideoService {
    private static final String ARQUIVO_VIDEOS = "videos.txt";
    private final VideoRepository repository;
    private final Scanner scanner;
    private final SearchStrategy searchStrategy;

    public VideoServiceImpl(Scanner scanner) {
        this.repository = new VideoRepositoryImpl(ARQUIVO_VIDEOS);
        this.scanner = scanner;
        this.searchStrategy = new SearchStrategyImpl();
    }

    @Override
    public void addVideo() {
        System.out.print("Digite o título do vídeo: ");
        String titulo = getInputNaoVazio(scanner);
        System.out.print("Digite a descrição do vídeo: ");
        String descricao = getInputNaoVazio(scanner);
        System.out.print("Digite a duração do vídeo (em minutos): ");
        int duracao = getOpcaoInteira(scanner);
        System.out.print("Digite a categoria do vídeo: ");
        Categoria categoria = getCategoria(scanner);
        System.out.print("Digite a data de publicação (dd/MM/yyyy): ");
        String dataStr = scanner.nextLine();

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date dataPublicacao = sdf.parse(dataStr);
            Video video = new Video(titulo, descricao, duracao, categoria, dataPublicacao);
            repository.save(video);
            System.out.println("Vídeo adicionado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao adicionar vídeo.");
        }

    }

    @Override
    public List<Video> listVideos() {
        return repository.findAll();
    }

    @Override
    public List<Video> searchByTitle(String query) {
        var videos = listVideos();

        return searchStrategy.searchByTitle(videos, query);
    }
}