package main;

import service.VideoService;
import service.VideoServiceImpl;

import java.util.Scanner;

import static util.InputUtil.getPositiveInteger;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VideoService videoService = new VideoServiceImpl(scanner);
        int option;

        do {
            System.out.println("\n=== Sistema de Gerenciamento de Vídeos ===");
            System.out.println("1. Adicionar vídeo");
            System.out.println("2. Listar vídeos");
            System.out.println("3. Pesquisar vídeo por título");
            System.out.println("4. Editar vídeo");
            System.out.println("8. Sair");
            System.out.print("Escolha uma opção: ");
            option = getPositiveInteger(scanner);

            switch (option) {
                case 1:
                    videoService.addVideo();
                    break;
                case 2:
                    videoService.listVideos();
                    break;
                case 3:
                    System.out.print("Digite o título para busca: ");
                    String query = scanner.nextLine();
                    videoService.searchByTitle(query);
                    break;
                case 4:
                    System.out.println("Digite o id do vídeo: ");
                    int id = getPositiveInteger(scanner);
                    videoService.editVideo(id);
                    break;
                case 8:
                    System.out.println("Saindo do sistema...");
                    break;
            }
        } while (option != 8);

        scanner.close();
    }
}