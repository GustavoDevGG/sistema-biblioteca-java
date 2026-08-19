package com.gustavodev;

import java.util.*;


public class Biblioteca {
    private final Map<String, Livro> livrosPorIsbn = new HashMap<>();

    private final TreeMap<String, Livro> livrosPorTitulo = new TreeMap<>();

    private final Map<String, Usuario> usuarios = new HashMap<>();

    private final List<Emprestimo> emprestimos = new ArrayList<>();
    private final List<Emprestimo> historicoDeEmprestimos = new ArrayList<>();

    private final Map<String, Queue<Reserva>> reservasPorIsbn = new HashMap<>();


    public void cadastrarLivro(Livro livro) {
            livrosPorIsbn.put(livro.getIsbn(), livro);
            livrosPorTitulo.put(livro.getTitulo(),livro);
    }

    public void buscarLivroPorIsbn(String isbn) {
        Livro livro = livrosPorIsbn.get(isbn);
        if (livro == null) {
            System.out.println("Livro não encontrado");
            return;
        }

        System.out.println("Livro buscado com sucesso");
        System.out.println(livro);

    }

    public void buscarLivroPorTitulo(String titulo) {
        Livro livro = livrosPorTitulo.get(titulo);
        if (livro == null) {
            System.out.println("Livro não encontrado");
            return;
        }

        System.out.println("Livro buscado com sucesso");
        System.out.println(livro);
    }

    public void removerLivro(String isbn) {

        Livro livro = livrosPorIsbn.get(isbn);

        if (livro == null) {
            System.out.println("Livro não encontrado para remover");
            return;
        }

        livrosPorIsbn.remove(isbn);
        livrosPorTitulo.remove(livro.getTitulo());

        System.out.println("Livro removido com sucesso");
    }

    public void listarLivros() {

        if (livrosPorTitulo.isEmpty()) {
            System.out.println("Não nenhum livro cadastrado");
            return;
        }

        System.out.println(livrosPorTitulo.values());
    }

    public void cadastrarUsuario(Usuario usuario) {
        if (usuarios.containsKey(usuario.getCpf())) {
            System.out.println("Usuario já cadastrado no sistema");
            return;
        }

        usuarios.put(usuario.getCpf(), usuario);
        System.out.println("Usuario cadastrado com sucesso");
    }

    public void buscarUsuarioPorCpf(String cpf) {
        Usuario usuario = usuarios.get(cpf);

        if (usuario == null) {
            System.out.println("Usuario não encontrado no sistema");
            return;
        }

        System.out.println("Usuario buscado com sucesso");
        System.out.println(usuario);
    }

    public void listarUsuarios() {
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuario cadastrado");
            return;
        }

        System.out.println(usuarios.values());
    }

    public void removerUsuario(String cpf) {
        Usuario usuario = usuarios.get(cpf);
        if (usuario == null) {
            System.out.println("USuario não cadastrado no sistema");
            return;
        }

        usuarios.remove(cpf);
        System.out.println("Usuario removido com sucesso");
    }

    public void emprestarLivro(String isbn, String cpf) {
        Livro livro = livrosPorIsbn.get(isbn);
        Usuario usuario = usuarios.get(cpf);

        if (usuario == null) {
            System.out.println("Usuario não cadastrado no sistema");
            return;
        }

        if (livro == null) {
            System.out.println("Livro não cadastrado no sistema");
            return;
        }

        if (!livro.isDisponivel()){
            return;
        }

        Emprestimo emprestimo = new Emprestimo(livro, usuario);

        livro.emprestarLivro();

        emprestimos.add(emprestimo);
        historicoDeEmprestimos.add(emprestimo);

        System.out.println("Empréstio realizado com sucesso");

    }

    public void devolverLivro(String isbn, String cpf) {
        Livro livro = livrosPorIsbn.get(isbn);
        Usuario usuario = usuarios.get(cpf);

        if (usuario == null) {
            System.out.println("Usuario não cadastrado no sistema");
            return;
        }

        if (livro == null) {
            System.out.println("Livro não cadastrado no sistema");
            return;
        }

        if (livro.isDisponivel()){
            System.out.println("Livro já está disponível para empréstimo");
            return;
        }

        Iterator<Emprestimo> iterator = emprestimos.iterator();

        while(iterator.hasNext()){

            Emprestimo emprestimo = iterator.next();

            if (emprestimo.getUsuario().getCpf().equals(cpf) && emprestimo.getLivro().getIsbn().equals(isbn)){

                emprestimo.getLivro().devolverLivro();
                iterator.remove();
                verificarFilaReservas(livro.getIsbn());
                System.out.println("Livro devolvido com sucesso");

                

                return;

            }


        }



        System.out.println("Nenhum empréstimo encontrado");


    }

    public void listarEmprestimosEmAndamento() {

        if (emprestimos.isEmpty()) {
            System.out.println("Não há nenhum empréstimo em andamento");
            return;
        }

        for (Emprestimo emprestimo : emprestimos){
            System.out.println( "Data : " + emprestimo.getData() +  " Livro : " + emprestimo.getLivro().getTitulo() + " Autor : " + emprestimo.getLivro().getAutor() + " - Usuario : " +  emprestimo.getUsuario().getCpf() + " - " + emprestimo.getUsuario().getNome());
        }

    }

    public void historicoDeEmprestimos() {

        if (historicoDeEmprestimos.isEmpty()) {
            System.out.println("Nenhum empréstimo realizado");
            return;
        }

        for (Emprestimo emprestimo : historicoDeEmprestimos){
            System.out.println( "Data : " + emprestimo.getData() +  " Livro : " + emprestimo.getLivro().getTitulo() + " Autor : " + emprestimo.getLivro().getAutor() + " - Usuario : " +  emprestimo.getUsuario().getCpf() + " - " + emprestimo.getUsuario().getNome());
        }

    }

    public void reservarLivro(String isbn, String cpf) {
        Livro livro = livrosPorIsbn.get(isbn);
        Usuario usuario = usuarios.get(cpf);

        Queue<Reserva> reservas = reservasPorIsbn.get(isbn);

        if (reservas == null) {
            reservas = new ArrayDeque<>();
            reservasPorIsbn.put(isbn, reservas);
        }

        Reserva reserva = new Reserva(livro, usuario);
        reservas.offer(reserva);

        System.out.println("Reserva realizada com sucesso.");

    }

    public boolean livroDisponivel(String isbn) {
        Livro livro = livrosPorIsbn.get(isbn);
        return livro != null && livro.isDisponivel();
    }

    public boolean livroExiste(String isbn) {
        return livrosPorIsbn.containsKey(isbn);
    }

    public void listarReservas(){
        System.out.println(reservasPorIsbn.values());
    }

    public void verificarFilaReservas(String isbn) {

        if ( !reservasPorIsbn.containsKey(isbn)) {
            System.out.println("Não existem reservas para este livro");
            return;
        }

        Queue<Reserva> reservas = reservasPorIsbn.get(isbn);

        if (reservas == null || reservas.isEmpty()) {
            // não existem reservas
            return;
        }
        Reserva reserva = reservas.poll();

        if (reservas.isEmpty()) {
            reservasPorIsbn.remove(isbn);
        }

        emprestarLivro(reserva.getLivro().getIsbn(), reserva.getUsuario().getCpf());

    }







}
