package main;

import model.Video;
import service.VideoService;
import service.VideoServiceImpl;

import java.util.List;
import java.util.Scanner;

import static util.InputUtil.getOpcaoInteira;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VideoService videoService = new VideoServiceImpl(scanner);
        int opcao;

        do {
            System.out.println("\n=== Sistema de Gerenciamento de Vídeos ===");
            System.out.println("1. Adicionar vídeo");
            System.out.println("2. Listar vídeos");
            System.out.println("3. Pesquisar vídeo por título");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = getOpcaoInteira(scanner);

            switch (opcao) {
                case 1:
                    videoService.addVideo();
                    break;
                case 2:
                    videoService.listVideos();
                    break;
                case 3:
                    System.out.print("Digite o título para busca: ");
                    String query = scanner.nextLine();
                    List<Video> resultados = videoService.searchByTitle(query);
                    for (Video video : resultados) {
                        System.out.println(video);
                    }
                    break;
                case 4:
                    System.out.println("Saindo do sistema...");
                    break;
            }
        } while (opcao != 4);

        scanner.close();
    }
}