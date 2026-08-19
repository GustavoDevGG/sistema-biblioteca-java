package com.gustavodev;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Emprestimo {
    private Livro livro;
    private Usuario usuario;
    private LocalDateTime data;

    public Emprestimo(Livro livro, Usuario usuario) {
        this.livro = livro;
        this.usuario = usuario;
        this.data = LocalDateTime.now();
    }

    public Livro getLivro() {
        return livro;
    }

    public LocalDateTime getData() {
        return data;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Emprestimo that = (Emprestimo) o;
        return Objects.equals(livro, that.livro) && Objects.equals(usuario, that.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(livro, usuario);
    }

    @Override
    public String toString() {
        return "Emprestimo{" +
                "livro=" + livro +
                ", usuario=" + usuario +
                '}';
    }
}
