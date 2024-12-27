package model;

public enum Category {
    DOCUMENTARIO("Documentário"),
    FILME("Filme"),
    SERIE("Série"),
    ;

    Category(String description) {
    }

    public static Category deserialize(String string) {
        try {
            return Category.valueOf(string.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Categoria inválida.");
            System.out.println("Categorias disponíveis: DOCUMENTARIO, FILME, SERIE.");
        }

        return null;
    }
}