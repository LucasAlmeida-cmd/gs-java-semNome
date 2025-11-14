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

---

## 🚀 Tecnologias Utilizadas

Este projeto foi desenvolvido utilizando as seguintes tecnologias:

* *Linguagem:* [Java 17]
* *Framework Backend:* [Spring Boot]
* *Banco de Dados:* [Oracle]
* *Frontend:* [HTML5, CSS3, JavaScript, Thymeleaf]
* *Gerenciador de Dependências:* [Maven]
* *Spring AI:* [ChatGPT]
* *Mensageria:* [Rabbitmq]

---

## 🏛️ Estrutura do Projeto


* 📁 *src/main/java*: Diretório principal da aplicação.
    * 📁 *Config*: Configuração da aplicação. 
        * 📁 *Security*: Arquivos de configuração do Spring Security
             * *CustomLoginSuccessHandler*: Arquivo que redireciona para os dashboards.
             * *CustomUserDetailsService*: Arqeuivo que encontra o usuário.
             * *JwtTokenFilter*: Arquivo que transforma o token em Bearer e vincula com o email.
             * *JwtTokenService*: Arquivo que faz a maior parte da configuração do token.
             * *SecurityConfig*: Arqeuivo de confuguração da segurança em si.
          * *DataInitializer*: Arquivo que inicializa um admin quando roda pela primeira vez.
          * *RabbitMQConfig*: Arquivo que configura a queue do rabbit.
    * 📁 *Consumer*: Pasta onde esta a configuração do envio do e-mail.
         * *EmailConsumer*: Arquivo de configuração de envio do e-mail.
    * 📁 *Controllers*: O controle de toda a operação. Recebem as requisições do usuário, interagem com as Services para buscar, salvar, atualizar e excluir dados e decidem qual tela irá exibir.
         * 📁 *api*: Controller separado exclusivamente para a api.
              * *AutenticacaoAPIController*: Arquivo que faz a autneticação de usuários.
              * *LogDiarioApiController*: Arquivo para controlar funções do log.
              * *UsuarioAPIController*: Arquivo para controlar funções do usuário.
        * *AdministradorController*: Controla a lógica dos Admins.
        * *AuthApiController* : Controla a lógica do MVC.
        * *ContentController*: Gerencia o controle de rotas dos dashboards.
        * *InsightController*: Gerencia o controle de rotas dos insights.
        * *LogDiarioController*: Gerencia o controle de rotas dos logs.
        * *TestAiController*: Teste para ver se esta funcionando a api key.
        * *UsuarioController*: Gerencia o controle de rotas dos usuários.
    * 📁 *dtos*: Arquivos de DTOS para diversas funcionalidados.
    * 📁 *Exceptions*: Diretório reservado para exceções. 
    * 📁 *Models*: Responsáveis pela lógica de negócio. Cada arquivo aqui geralmente representa uma tabela do banco de dados.
        * *Administrador*: Model para as operações relacionadas aos Administrador, classe filha do User.
        * *Insight*: Model para as operações relacionadas aos Insights.
        * *LogDiario*: Model para as operações relacionadas aos LosgDiarios.
        * *Role*: ENUM para roles.
        * *User*: Model para as operações relacionadas aos User, classe pai do Administrador e Usuario.
        * *Usuario*: Model para as operações relacionadas aos Usuarios, classe filha do User.
        * *VerificaCPF*: Model para fazer verificações de CPF.
    * 📁 *Repository*: Classes responsáveis pela conexão do banco de dados.
    * 📁 *Service*: Classes que tem contêm a lógica da aplicação, elas que fazem a ponte entre controllers e o resto da aplicação.
* 📁 *resources/*: Pastas que complementam a aplicação.
    * *Static.css*: Arquivos de CSS.
    * *templates*: Pastas e diretórios que montam a View.
* 🍃application.yml*: Configurações essenciais para o funcionamento do projeto (Credenciais e configurações dos recursos)

Essa estrutura ajuda a manter o código organizado, onde cada parte tem um papel bem definido.

---

## ⚙️ Como Rodar o Projeto Localmente

Siga os passos abaixo para executar o projeto na sua máquina.

*Pré-requisitos:*

* ☕ Java Development Kit (JDK): Versão 17 ou superior.

* 🐘 Apache Maven: Para gerenciar as dependências e o build do projeto.

* 🐘 PostgreSQL: Banco de dados que o projeto utiliza.

* 🐙 Git: Para clonar o repositório.

* <img width="20" height="20" alt="image" src="https://github.com/user-attachments/assets/a4bce524-5d3f-4aed-bd8d-b0f34102b601" /> Docker: Para subir o rabbit, comando a baixo(só rodar no console):
  
   ```docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management```


*Passos:*

1.  *Clone o repositório:*
    bash
    git clone [https://github.com/LucasAlmeida-cmd/challenge-mottu.git

2. Mudar a branch para a  ```main```
    

3.  *Verifique as dependências do .yml:*

      Na entrega vai ter o um arquivo chamado: *variaveis_ambiente*, nele é só pegar os valores e substituir pelos que estão em ${} com o mesmo nome. 

4.  **Execute a aplicação:**
    
      Aperto a seta verde para inicializar.

7.  A aplicação estará disponível em `http://localhost:8080`.

---

## 🤔 Como Utilizar:

Para usar 100% do projeto recomendo rodar localmente. 

1.  Faça um cadastro com email valido.
2.  Faça login com as credenciais que voce colocou.
3.  Dentro da aplicação faça alguns logs diarios.
5.  Gere um insight.
6.  Utilziar CPF válido. Site para geração de CPF: [ neste link](https://www.4devs.com.br/gerador_de_cpf).
7.  Existem alguns campos que são unicos.

# Links: 

1. Deploy: https://gs-java-0em0.onrender.com
2. Link de apresentação: 

