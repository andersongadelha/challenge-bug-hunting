package model;

public enum Categoria {
    DOCUMENTARIO("Documentário"),
    FILME("Filme"),
    SERIE("Série"),
    ;

    Categoria(String descricao) {
    }

    public static Categoria desserializar(String string) {
        try {
            return Categoria.valueOf(string.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Categoria inválida.");
            System.out.println("Categorias disponíveis: DOCUMENTARIO, FILME, SERIE.");
        }

        return null;
    }
}