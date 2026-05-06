# UniReserva – Backend 🏫🗓️

Sistema de reserva de salas (salas de aula, laboratórios, auditórios) para uso interno de uma instituição de ensino.  
Este repositório contém a **API REST** responsável por gerenciar usuários, salas e reservas.

---

## 🎯 Objetivo do Projeto

O objetivo do UniReserva é permitir que **usuários autenticados** (alunos, professores, administradores) possam:

- Consultar salas disponíveis
- Realizar reservas de salas em datas e horários específicos
- Evitar conflitos de reserva para o mesmo horário/sala
- Manter um histórico das reservas realizadas

O projeto está sendo desenvolvido como **trabalho final da disciplina**, seguindo arquitetura em camadas e boas práticas com Spring Boot.

---

## 🏗️ Tecnologias Utilizadas

- **Java** (versão 17 ou superior, conforme definido no projeto)
- **Spring Boot**
    - Spring Web
    - Spring Data JPA
    - (Opcional) Spring Security / JWT
- **Banco de Dados**
    - H2 (para desenvolvimento) ou outro definido pela equipe (MySQL/PostgreSQL)
- **Maven** (gerenciador de dependências)
- **JPA / Hibernate**
- (Opcional) Lombok, MapStruct, etc.

---

## 📁 Arquitetura e Estrutura de Pacotes

O projeto segue uma arquitetura em camadas, organizada em pacotes:

```text
src/main/java/com/uniesp/unireserva
 ├─ config          # Configurações gerais da aplicação (CORS, beans, etc.)
 ├─ controller      # Controllers REST (endpoints da API)
 ├─ dto             # Objetos de transferência de dados (Request / Response)
 ├─ entity          # Entidades JPA (mapeamento das tabelas)
 ├─ enums           # Enums do domínio (UserRole, ReservationStatus, etc.)
 ├─ exception       # Tratamento e classes de exceção
 ├─ mapper          # Conversão entre Entity e DTO
 ├─ report          # Relatórios (se aplicável na etapa final)
 ├─ repository      # Interfaces de acesso ao banco (Spring Data JPA)
 ├─ security        # Configuração de autenticação/autorização (futuro)
 ├─ service         # Regras de negócio (interfaces e implementações)
 ├─ validation      # Validações customizadas (se necessário)
 └─ UniReservaApplication.java  # Classe principal do Spring Boot
```

### Entidades principais (`entity`)

As entidades representam os elementos centrais do domínio:

- `User`
    - Dados do usuário do sistema (ex.: nome, e-mail, senha, tipo de usuário)
    - Possui relacionamento com `Reservation`
- `Room`
    - Representa uma sala física (nome, capacidade, recursos, etc.)
    - Possui relacionamento com `Reservation`
- `Reservation`
    - Representa uma reserva de uma sala em uma data e horário
    - Relacionamentos principais:
        - `ManyToOne` → `User`
        - `ManyToOne` → `Room`
    - Utiliza `ReservationStatus` para indicar o status da reserva

### Enums (`enums`)

- `UserRole`
    - Define os papéis do usuário no sistema (ex.: `ADMIN`, `TEACHER`, `STUDENT`)
- `ReservationStatus`
    - Define o status de uma reserva (ex.: `PENDING`, `CONFIRMED`, `CANCELED`)

### Repositórios (`repository`)

Interfaces que herdam de `JpaRepository` para acessar o banco de dados, por exemplo:

- `UserRepository`
- `RoomRepository`
- `ReservationRepository`

### Camada de Serviço (`service`)

Responsável pelas **regras de negócio**, normalmente dividida em:

```text
service
 ├─ interfaces      # Interfaces dos serviços (contratos)
 └─ impl            # Implementações das interfaces
```

Exemplos:

- `UserService` / `UserServiceImpl`
- `RoomService` / `RoomServiceImpl`
- `ReservationService` / `ReservationServiceImpl`

### Controllers (`controller`)

Camada responsável por expor os **endpoints REST** da aplicação.  
Cada entidade principal possui (ou possuirá) um controller dedicado:

- `UserController`
- `RoomController`
- `ReservationController`

---

## 📌 Escopo da Primeira Etapa (Modelagem do Domínio)

Nesta primeira entrega do projeto final, o foco foi:

### ✅ Entidades principais

- Criação das entidades principais do sistema em `entity`:
    - `User`
    - `Room`
    - `Reservation`
- Definição de atributos coerentes com o domínio de reservas de sala.

### ✅ Mapeamentos JPA

- Utilização das principais anotações:
    - `@Entity`
    - `@Table`
    - `@Id`
    - `@GeneratedValue`
    - `@Column` (quando necessário)
- Implementação dos relacionamentos principais:
    - `@ManyToOne` + `@JoinColumn` para ligar `Reservation` a `User` e `Room`
    - `@Enumerated(EnumType.STRING)` para enums (`UserRole`, `ReservationStatus`)

### ✅ Organização em camadas

Criação dos pacotes:

- `entity` → entidades do domínio
- `repository` → repositórios JPA
- `service` → camada de serviço (interfaces e implementações)
- `controller` → camada de controle (endpoints REST)
- `dto` → objetos de transferência (Request/Response), mesmo que ainda em construção

Essa organização garante que o projeto está alinhado com a arquitetura em camadas trabalhada em aula.

---

## 🚀 Como executar o projeto

1. **Pré-requisitos**
    - Java 17+ instalado
    - Maven instalado (ou usar o Maven embutido da IDE)
    - IDE recomendada: IntelliJ IDEA / VS Code / Eclipse

2. **Clonar o repositório**

   ```bash
   git clone <url-do-repositorio>
   cd UniReserva
   ```

3. **Executar pela linha de comando**

   ```bash
   mvn spring-boot:run
   ```

   Ou, na IDE, executar a classe:

   ```text
   UniReservaApplication
   ```

4. **Banco de dados**

   Caso esteja usando H2 em memória, o acesso ao console (se habilitado) geralmente é:

    - URL: `/h2-console`
    - JDBC URL: `jdbc:h2:mem:unireserva-db` (ajustar conforme `application.properties`)

---

## 🔮 Próximas etapas do projeto

Nas próximas entregas, serão desenvolvidos:

- Endpoints completos (CRUD) para `User`, `Room` e `Reservation`
- Regras de negócio de reservas (validação de conflitos de horário)
- Validações de dados de entrada (DTOs, Bean Validation)
- Autenticação e autorização (login, perfis de acesso)
- Tratamento de erros padronizado (exception handler)
- Relatórios ou consultas específicas (caso previsto no escopo)

---

## 👥 Equipe

- **Messias da Silva Guedes**
- **Tamires Carvalho da Silva**

Curso / Período: 4 Período 
Disciplina: Back-End Avançado
Professor(a): Jonas

---

## 📄 Licença

Projeto desenvolvido para fins acadêmicos.