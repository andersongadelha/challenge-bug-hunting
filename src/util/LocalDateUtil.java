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
    public static String serialize(LocalDate date) {
        String stringDate;
        if (Objects.isNull(date)) {
            System.out.println("Data nula.");
            stringDate = "";
        } else {
            stringDate = date.format(FORMATTER);
        }

        return stringDate;
    }

    /**
     * Desserializa uma String no formato dd/MM/aaaa para LocalDate
     *
     * @param string String representando uma data
     *
     * @return LocalDate convertido
     */
    public static LocalDate deserialize(String string) {
        LocalDate date;
        if (Objects.isNull(string)) {
            System.out.println("Data nula.");
            date = null;
        } else {
            date = LocalDate.parse(string, FORMATTER);
        }

        return date;
    }
}
