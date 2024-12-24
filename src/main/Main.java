package main;

import model.Video;
import service.VideoService;
import service.VideoServiceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static util.InputUtil.getOpcaoInteira;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VideoService videoService = new VideoServiceImpl(scanner);

        while (true) {
            System.out.println("\n=== Sistema de Gerenciamento de Vídeos ===");
            System.out.println("1. Adicionar vídeo");
            System.out.println("2. Listar vídeos");
            System.out.println("3. Pesquisar vídeo por título");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = getOpcaoInteira(scanner);

            if (opcao == 1) {
                videoService.addVideo();
            } else if (opcao == 2) {
                List<Video> videos = videoService.listVideos();
                for (Video video : videos) {
                    System.out.println(video);
                }
            } else if (opcao == 3) {
                System.out.print("Digite o título para busca: ");
                String query = scanner.nextLine();
                List<Video> resultados = videoService.searchByTitle(query);
                for (Video video : resultados) {
                    System.out.println(video);
                }
            } else if (opcao == 4) {
                System.out.println("Saindo do sistema...");
                break;
            } else {
                System.out.println("Digite um inteiro de 1 - 4");
            }
        }

        scanner.close();
    }
}