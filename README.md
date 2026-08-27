# 📚 Sistema de Biblioteca em Java

## 📖 Sobre o projeto

Sistema de gerenciamento de biblioteca desenvolvido em **Java** com o objetivo de praticar e consolidar conhecimentos em **Programação Orientada a Objetos (POO)** e **Java Collections**.

O projeto permite gerenciar livros, usuários, empréstimos e reservas. Também foi desenvolvido um sistema de fila que realiza automaticamente o empréstimo para o primeiro usuário da reserva quando um livro é devolvido.

> ⚠️ Projeto ainda passando por melhorias e refatorações.

---

## ⚙️ Funcionalidades

### 📚 Livros
- Cadastrar livros;
- Buscar por ISBN ou título;
- Listar livros;
- Remover livros;
- Verificar disponibilidade.

### 👤 Usuários
- Cadastrar usuários;
- Buscar por CPF;
- Listar usuários;
- Remover usuários.

### 🤝 Empréstimos
- Realizar empréstimos;
- Validar livros e usuários;
- Impedir empréstimos de livros indisponíveis;
- Devolver livros.

### ⏳ Reservas

Quando um livro está indisponível, o usuário pode entrar em uma fila de espera.

A fila utiliza a regra **FIFO (First In, First Out)**: o primeiro usuário a reservar é o primeiro a receber o livro.

---

## 🧠 Collections utilizadas

- **HashMap** → Livros por ISBN, usuários por CPF e filas de reserva por ISBN;
- **TreeMap** → Livros organizados por título;
- **ArrayList** → Empréstimos ativos;
- **Queue / ArrayDeque** → Filas de reservas.

---

## 🔄 Fluxo de empréstimo e reserva

```text
Usuário solicita um livro
        │
        ▼
Livro está disponível?
       │
   ┌───┴───┐
   │       │
  Sim      Não
   │        │
   ▼        ▼
Empréstimo  Usuário deseja
realizado   entrar na fila?
               │
           ┌───┴───┐
           │       │
          Sim      Não
           │       │
           ▼       ▼
      Cria reserva  Fim
```

## 🔄 Fluxo da devolução

Quando um livro é devolvido, o sistema verifica se existe uma fila de reservas:

```text
Livro devolvido
      │
      ▼
Existe fila de reservas?
      │
   ┌──┴──┐
   │     │
  Não   Sim
   │     │
   ▼     ▼
  Fim  poll()
         │
         ▼
Pega o primeiro usuário
da fila
         │
         ▼
Realiza o empréstimo
automaticamente
```

---

## ▶️ Como executar

Clone o repositório:

```bash
git clone SEU_LINK_DO_REPOSITORIO
```

Abra o projeto em uma IDE Java, como **IntelliJ IDEA**, Eclipse ou VS Code, e execute a classe:

```text
Main.java
```

---

## 🚀 Tecnologias

- Java;
- Programação Orientada a Objetos;
- Java Collections Framework.

---

## 📌 Possíveis melhorias futuras

- [ ] Melhorar a validação das entradas;
- [ ] Implementar persistência de dados;
- [ ] Criar uma interface gráfica.

---

## 👨‍💻 Autor

Desenvolvido por **Gustavo Marques**.

Projeto criado para praticar **Java, POO e Collections**, aplicando os conhecimentos estudados em um sistema prático.
