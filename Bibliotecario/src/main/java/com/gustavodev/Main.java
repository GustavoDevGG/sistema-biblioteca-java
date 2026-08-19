package com.gustavodev;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        Biblioteca biblioteca = new Biblioteca();

        String isbn;
        String titulo;
        String autor;
        String cpf;
        String nome;

        System.out.println(" ==== Programa de Biblioteca ====");

        int opcao;
        String escolha;

        do {

            System.out.println("1 - Livros ");
            System.out.println("2 - Usuarios ");
            System.out.println("3 - Emprestimos ");
            System.out.println("0 - Sair ");
            System.out.print("Escolha uma opção : ");
            opcao = sc.nextInt();

            sc.nextLine();

            switch (opcao) {
                case 1: // Menu Livro

                    do {

                        System.out.println("1 - Cadastrar Livro");
                        System.out.println("2 - Listar Livros");
                        System.out.println("3 - Buscar Livro por ISBN");
                        System.out.println("4 - Buscar Livro pelo Título");
                        System.out.println("5 - Remover Livro");
                        System.out.println("6 - Voltar ao Menu anterior ");
                        System.out.print("Escolha uma opção : ");
                        opcao = sc.nextInt();

                        sc.nextLine();

                        switch (opcao) {
                            case 1: // Cadastrar Livros

                                System.out.print("Digite o ISBN: ");
                                isbn = sc.nextLine();

                                System.out.print("Digite o Titulo: ");
                                titulo = sc.nextLine();

                                System.out.print("Digite o Autor: ");
                                autor = sc.nextLine();

                                Livro livro = new Livro(isbn, titulo, autor);
                                biblioteca.cadastrarLivro(livro);
                                System.out.println("Livro cadastrado com sucesso");
                                break;

                            case 2: // Listar Livros
                                System.out.println("==== Livros listados na biblioteca ====");
                                biblioteca.listarLivros();
                                break;

                            case 3: // Buscar livro por ISBN
                                System.out.print("Digite o ISBN: ");
                                isbn = sc.nextLine();

                                biblioteca.buscarLivroPorIsbn(isbn);
                                break;

                            case 4: // Buscar livro por Titulo
                                System.out.print("Digite o Titulo do Livro: ");
                                titulo = sc.nextLine();

                                biblioteca.buscarLivroPorTitulo(titulo);
                                break;

                            case 5: // Remover livro
                                System.out.print("Digite o isbn : ");
                                isbn = sc.nextLine();

                                biblioteca.removerLivro(isbn);
                                break;

                            case 6:
                                break;

                            default:// Opção Invalida
                                System.out.println("Opção Invalida escolha uma opção válida no menu");
                                break;
                        }

                    }while(opcao < 6);
                    break;

                case 2: // Menu Usuário

                    do {

                        System.out.println("1 - Cadastrar Usuario");
                        System.out.println("2 - Listar Usuarios Cadastrados");
                        System.out.println("3 - Buscar Usuario pelo CPF");
                        System.out.println("4 - Remover Usuario");
                        System.out.println("5 - Voltar ao Menu anterior ");
                        System.out.print("Escolha uma opção : ");
                        opcao = sc.nextInt();

                        sc.nextLine();

                        switch (opcao) {

                            case 1:// Cadastrar Usuario

                                System.out.print("Digite o CPF do Usuario: ");
                                cpf =  sc.nextLine();

                                System.out.print("Digite o nome do Usuario: ");
                                nome = sc.nextLine();

                                Usuario usuario = new Usuario(nome,cpf);

                                biblioteca.cadastrarUsuario(usuario);
                                break;

                            case 2: // Listar Usuarios CAdastrados

                                System.out.println("==== Usuarios cadastrados na biblioteca ====");
                                biblioteca.listarUsuarios();
                                break;

                            case 3: // Buscar Usuario pelo CPF

                                System.out.print("Digite o CPF do Usuario: ");
                                cpf =  sc.nextLine();

                                biblioteca.buscarUsuarioPorCpf(cpf);
                                break;

                            case 4: //Remover Usuario
                                System.out.println("Digite o CPF do Usuario: ");
                                cpf =  sc.nextLine();

                                biblioteca.removerUsuario(cpf);
                                break;

                            case 5:
                                break;

                            default:// Opção Invalida
                                System.out.println("Opção Invalida, volte ao menu e escolha uma opção válida");
                        }

                    }while(opcao < 5);

                    break;

                case 3:// Menu Empréstimo

                    do {

                        System.out.println("1 - Emprestar Livro");
                        System.out.println("2 - Devolver Livro");
                        System.out.println("3 - Empréstimos em andamento");
                        System.out.println("4 - Histórico de Emprestimos");
                        System.out.println("5 - Listar Reservas ");
                        System.out.println("6 - Voltar ao Menu anterior ");

                        System.out.print("Escolha uma opcao : ");
                        opcao = sc.nextInt();

                        sc.nextLine();

                        switch (opcao) {

                            case 1:// Empréstimo

                                System.out.print("Digite o ISBN do Livro: ");
                                isbn = sc.nextLine();

                                System.out.println("Digite o CPF do Usuario a se fazer o empréstimo : ");
                                cpf =  sc.nextLine();

                                if (!biblioteca.livroExiste(isbn)) {
                                    System.out.println("Livro não Cadastrado no sistema");
                                    break;
                                }

                                if (biblioteca.livroDisponivel(isbn)) {
                                    biblioteca.emprestarLivro(isbn,cpf);
                                }else {

                                    System.out.print("Livro não disponivel deseja entrar na fila de Espera  (S/N) : ");
                                    escolha = sc.nextLine();

                                    if (escolha.equalsIgnoreCase("S")) {
                                        biblioteca.reservarLivro(isbn,cpf);
                                    }else if (escolha.equalsIgnoreCase("N")) {
                                        break;
                                    }
                                }


                                break;

                            case 2:// Devolver Livro

                                System.out.print("Digite o ISBN do Livro: ");
                                isbn = sc.nextLine();

                                System.out.println("Digite o CPF do Usuario que está devolvendo o livro : ");
                                cpf =  sc.nextLine();

                                biblioteca.devolverLivro(isbn,cpf);
                                break;

                            case 3://Emprestimos em Andamento

                                System.out.println("===== Lista de Empréstimos em Andamento ====");

                                biblioteca.listarEmprestimosEmAndamento();
                                break;

                            case 4://Histórico de Empréstimos

                                System.out.println("==== Histórico de Empréstimos ====");
                                biblioteca.historicoDeEmprestimos();
                                break;

                            case 5:
                                biblioteca.listarReservas();
                                break;

                            case 6:
                                break;

                            default://Opção invalida

                                System.out.println("Opção invalida volte ao menu e selecione uma válida");
                                break;
                        }

                    }while(opcao < 6);
            }

        }while (opcao > 0);


    }
}
