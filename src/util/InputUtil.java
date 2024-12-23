package util;

import java.util.Scanner;

public class InputUtil {

    public static int getOpcaoInteira(Scanner scanner) {
        String entrada = scanner.nextLine();
        int entradaTratada = 0;
        try {
            entradaTratada = Integer.decode(entrada);
        } catch (Exception e) {
            System.out.println("A entrada deve ser um valor inteiro.");
        }

        return entradaTratada;
    }
}
