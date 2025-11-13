# 💻 Global Solution - ZenLog


---

## 🎥 Demonstração

Você pode acessar uma demonstração ao vivo do projeto [neste link]().



<img width="1136" height="766" alt="image" src="https://github.com/user-attachments/assets/c8675014-df34-4684-a095-3bf7c5b04211" />


Tela principal do sistema de gerenciamento.

---

## ✨ Funcionalidades

Aqui está uma lista das principais funcionalidades da aplicação:

* *✅ Cadastro, Atualização de Usuários*
* *✅ Leitura, Atualização, Criação e Remoção de Logs Diarios*
* *✅ Gerar, Leitura de Insights*

* CONTINUAR DAQUI
---

## 🚀 Tecnologias Utilizadas

Este projeto foi desenvolvido utilizando as seguintes tecnologias:

* *Linguagem:* [Ex:Java 17]
* *Framework Backend:* [Spring Boot]
* *Banco de Dados:* [Ex:PostgreSQL]
* *Frontend:* [Ex: HTML5, CSS3, JavaScript, Thymeleaf]
* *Gerenciador de Dependências:* [Ex:Maven]

---

## 🏛️ Estrutura do Projeto


* 📁 *src/java/*: Diretório principal da aplicação.
    * 📁 *Config*: Configuração da aplicação.
        * DataInitializer: Adiciona um Admin toda vez que a operação rodar.
    * 📁 *Controllers*: O controle de toda a operação. Recebem as requisições do usuário, interagem com as Services para buscar, salvar, atualizar e excluir dados e decidem qual tela irá exibir.
        * AdministradorController: Controla a lógica dos Admins.
        * ContentController: Gerencia o controle de rotas dos dashboards.
        * MotoContoller: Controla a lógica das Motos.
        * MotoqueiroController: Controla a lógica dos Motoqueiros.
        * PatioController: Controla a lógica dos Patios.
        * SecaoController: Controla a lógica das Seções.
        * VagaController: Controla a lógica das Vagas.
    * 📁 *Exceptions*: Diretório reservado para exceções. 
    * 📁 *Models*: Responsáveis pela lógica de negócio. Cada arquivo aqui geralmente representa uma tabela do banco de dados.
        * Administrador: Modelo para as operações relacionadas aos Administrador, classe filha do User.
        * Endereco: Classe de endereço usada por uma API externa.
        * Moto: Modelo para as operações relacionadas às Motos.
        * Motoqueiros: Modelo para as operações relacionadas aos Motoqueiros.
        * Patio: Modelo para as operações relacionadas aos Patios.
        * Role: Enum para dar roles.
        * Secao: Modelo para as operações relacionadas às Seções.
        * StatusMoto: Enum para status das Motos.
        * User: Modelo que gerencia os dados dos usuários (Motoqueiros e Admins).
        * Vaga: Modelo para as operações relacionadas às Vagas.
    
    * 📁 *DTOS*: Classes DTOs para receber/passar dados;
    * 📁 *Repository*: Classes responsáveis pela conexão do banco de dados.
    * 📁 *Security*: Classes que extendem configurações relacionadas com a segurança.
        * CustomLoginSuccessHandler: Classe responsável pelo direcionamento entre dashboards dependendo da função.
        * SecurityConfig: Classe responsável pelo gerenciamento de acessos pelas funções dos usuario. 
    * 📁 *Service*: Classes que tem contêm a lógica da aplicação, elas que fazem a ponte entre controllers e o resto da aplicação.

* 📁 *resources/*: Pastas que complementam a aplicação.
    * *db.migration*: Pasta que se localiza as migrações do FlyWay.
    * *js*: Pasta de arquivos JavaScript.
    * *Static.css*: Arquivos de CSS.
    * *templates*: Pastas e diretórios que montam a View.
* 🍃application.yml*: Configurações essenciais para o funcionamento do projeto (Credenciais do banco, configuração do FlyWay, configuração do MVC)

Essa estrutura ajuda a manter o código organizado, onde cada parte tem um papel bem definido.

---

## ⚙️ Como Rodar o Projeto Localmente

Siga os passos abaixo para executar o projeto na sua máquina.

*Pré-requisitos:*

* ☕ Java Development Kit (JDK): Versão 17 ou superior.

* 🐘 Apache Maven: Para gerenciar as dependências e o build do projeto.

* 🐘 PostgreSQL: Banco de dados que o projeto utiliza.

* 🐙 Git: Para clonar o repositório.

*Passos:*

1.  *Clone o repositório:*
    bash
    git clone [https://github.com/LucasAlmeida-cmd/challenge-mottu.git

2. Mudar a branch para a  ```mvc```
    

3.  *Verifique as dependências do Maven:*
    
    * Crie um novo banco de dados no seu PostgreSQL (ex: `challenge_mottu`).
       * Navegue até o arquivo `src/main/resources/application.yml`.
       * Configure as propriedades de conexão com o seu banco de dados local:
           ```properties
           spring:
             datasource:
               url: jdbc:postgresql://localhost:5432/challenge_mottu
               username: seu_usuario_postgres
               password: sua_senha_postgres
           ```
       * O **Flyway** cuidará de criar e atualizar as tabelas do banco de dados automaticamente quando a aplicação iniciar.
4.  **Compile e instale as dependências com o Maven:**
    ```bash
    mvn clean install -DskipTests
    ```

5.  **Execute a aplicação:**
    ```bash
    mvn spring-boot:run
    ```
    *Alternativamente, você pode executar o arquivo JAR gerado no passo anterior:*
    ```bash
    java -jar target/challenge-mottu-0.0.1-SNAPSHOT.jar
    ```

6.  A aplicação estará disponível em `http://localhost:8080`.

---

## 🤔 Como Utilizar:

Se você quiser contribuir com este projeto, siga estas etapas:

1.  Faça login com email:'admin', senha:'admin'.
2.  Dentro da aplicação(logado como admin) você poderá fazer várias inserções, atualizações e exclusões nas diferentes áreas.
3.  Recomendo seguir essas inserções: Administrador>Motoqueiro>Pátios>Seções>Vagas>Motos.
4.  Recomendo fazer pelo menos 2 inserções de cada.
5.  Utilziar CPF válido para o Motoqueiro. Site para geração de CPF: [ neste link](https://www.4devs.com.br/gerador_de_cpf).
6.  Existem alguns campos que são unicos.
7.  Para uma demonstração mais adequada assitir a esse [ video](https://www.youtube.com/watch?v=0XtMN1We-Dw).

