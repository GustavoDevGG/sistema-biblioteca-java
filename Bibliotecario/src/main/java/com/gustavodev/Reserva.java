package com.gustavodev;

import java.util.Objects;

public class Reserva {
    private Livro livro;
    private Usuario usuario;

    public Reserva(Livro livro, Usuario usuario) {
        this.livro = livro;
        this.usuario = usuario;
    }

    public Livro getLivro() {
        return livro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Reserva reserva = (Reserva) o;
        return Objects.equals(livro, reserva.livro) && Objects.equals(usuario, reserva.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(livro, usuario);
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "livro=" + livro +
                ", usuario=" + usuario +
                '}';
    }
}
