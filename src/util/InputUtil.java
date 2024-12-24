package util;

import model.Categoria;

import java.util.Objects;
import java.util.Scanner;

/**
 * Input Util
 *
 * <p> Essa classe util tem como objetivo auxiliar na entrada de inputs</p>
 *
 * @author Anderson Gadelha
 */
public class InputUtil {

    /**
     * Função para receber um input e retornar valor inteiro.
     * Caso seja recebido algum valor diferente de um inteiro menor ou igual a zero a função recebe o input novamente.
     *
     * @param scanner
     *
     * @return Um inteiro maior que zero.
     */
    public static int getOpcaoInteira(Scanner scanner) {
        int entradaTratada= 0;
        boolean entradaValida = false;
        while (!entradaValida) {
            String entrada = scanner.nextLine();
            try {
                entradaTratada = Integer.decode(entrada);
                if (entradaTratada > 0) {
                    entradaValida = true;
                } else {
                    System.out.println("A entrada deve ser um valor inteiro maior que zero.");
                }
            } catch (Exception e) {
                System.out.println("A entrada deve ser um valor inteiro maior que zero.");
            }
        }

        return entradaTratada;
    }

    /**
     * Função para receber um input enquanto a entrada não seja vazia.
     *
     * @param scanner
     *
     * @return Uma String não vazia.
     */
    public static String getInputNaoVazio(Scanner scanner) {
        String entrada;
        do {
            entrada = scanner.nextLine();

            if (entrada.isEmpty()) {
                System.out.println("A entrada não pode ser vazia.");
            }
        } while (entrada.isEmpty());

        return entrada;
    }

    /**
     * Função para receber input até a entrada ser uma categoria valida.
     *
     * @param scanner
     *
     * @return Categoria
     */
    public static Categoria getCategoria(Scanner scanner) {
        String entrada;
        Categoria categoria;
        do {
            entrada = scanner.nextLine();
            categoria = Categoria.desserializar(entrada);
        } while (Objects.isNull(categoria));

        return categoria;
    }
}
