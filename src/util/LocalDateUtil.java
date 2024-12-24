package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Local Date Util
 *
 * <p> Essa classe util tem como objetivo auxiliar na manipulação de LocalDate.</p>
 *
 * @author Anderson Gadelha
 */
public class LocalDateUtil {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Serializa um LocalDate para String no formato dd/MM/aaaa
     *
     * @param date LocalDate a ser convertido
     *
     * @return String representando a data
     */
    public static String serializar(LocalDate date) {
        String dataString;
        if (Objects.isNull(date)) {
            System.out.println("Data nula.");
            dataString = "";
        } else {
            dataString = date.format(FORMATTER);
        }

        return dataString;
    }

    /**
     * Desserializa uma String no formato dd/MM/aaaa para LocalDate
     *
     * @param string String representando uma data
     *
     * @return LocalDate convertido
     */
    public static LocalDate desserializar(String string) {
        LocalDate data;
        if (Objects.isNull(string)) {
            System.out.println("Data nula.");
            data = null;
        } else {
            data = LocalDate.parse(string, FORMATTER);
        }

        return data;
    }
}
