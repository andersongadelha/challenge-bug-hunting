package main;

import model.Category;
import service.VideoService;
import service.VideoServiceImpl;

import java.util.Scanner;

import static util.InputUtil.getCategory;
import static util.InputUtil.getPositiveInteger;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VideoService videoService = new VideoServiceImpl(scanner);
        int option;
        int id;

        do {
            System.out.println("\n=== Sistema de Gerenciamento de Vídeos ===");
            System.out.println("1. Adicionar vídeo");
            System.out.println("2. Listar vídeos");
            System.out.println("3. Pesquisar vídeo por título");
            System.out.println("4. Editar vídeo");
            System.out.println("5. Excluir vídeo");
            System.out.println("6. Filtrar vídeos por categoria");
            System.out.println("7. Listar vídeos por ordem cronológica");
            System.out.println("8. Relatório de estatísticas");
            System.out.println("9. Sair");
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
                    id = getPositiveInteger(scanner);
                    videoService.editVideo(id);
                    break;
                case 5:
                    System.out.println("Digite o id do vídeo:");
                    id = getPositiveInteger(scanner);
                    videoService.removeById(id);
                    break;
                case 6:
                    System.out.println("Digite a categoria que deseja filtrar os vídeos:");
                    Category categoryFilter = getCategory(scanner);
                    videoService.searchByCategory(categoryFilter);
                    break;
                case 7:
                    videoService.listVideosOrderByDate();
                    break;
                case 8:
                    videoService.showStatistics();
                    break;
                case 9:
                    System.out.println("Saindo do sistema...");
                    break;
            }
        } while (option != 9);

        scanner.close();
    }
}