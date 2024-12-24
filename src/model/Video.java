package model;

import util.LocalDateUtil;

import java.time.LocalDate;

public class Video {
    private String titulo;
    private String descricao;
    private int duracao;
    private Categoria categoria;
    private LocalDate dataPublicacao;

    public Video(String titulo, String descricao, int duracao, Categoria categoria, LocalDate dataPublicacao) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.duracao = duracao;
        this.categoria = categoria;
        this.dataPublicacao = dataPublicacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getDuracao() {
        return duracao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    @Override
    public String toString() {
        return titulo + ";" + descricao + ";" + duracao + ";" + categoria + ";" + LocalDateUtil.serializar(dataPublicacao);
    }

    public static Video fromString(String linha) {
        try {
            String[] partes = linha.split(";");
            return new Video(partes[0], partes[1], Integer.parseInt(partes[2]), Categoria.desserializar(partes[3]), LocalDateUtil.desserializar(partes[4]));
        } catch (Exception e) {
            return null; // Ignora erros de parsing
        }
    }
}