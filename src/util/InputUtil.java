package util;

import java.util.Scanner;

/**
 *  Input Util
 *
 *  <p> Essa classe util tem como objetivo auxiliar na entrada de inputs</p>
 *
 * @author Anderson Gadelha
 */
public class InputUtil {

    /**
     * Função para retornar um inteiro e retornar valor default caso seja recebido algum valor diferente de um inteiro.
     *
     * @param scanner
     * @retur um valor inteiro, tendo zero como default.
     */
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
