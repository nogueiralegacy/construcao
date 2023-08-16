# Gerenciador de projeto
## Descrição
Projeto da disciplina de Construção de Software.
Sistema capaz de auxiliar na organizarção de atividades de uma equipe de trabalho no esforço de concluir um projeto.
###
Inspriração [Trello](trello.com)

## Tecnologias
- Java
- PosgreSQL
- Spring: Data, Web e Security
- API REST
- DevOps: Digital Ocean e GitHub Actions

## Como executar

### Pré-requisitos
- JRE 17
- Maven
- PostgreSQL
- Configurar variaveis de ambiente:
    * `URL_DATABASE`: Com a url do banco de dados PostgreSQL
    * `USERNAME_DATABASE`: Com o nome de usuário do banco de dados
    * `PASSWORD_DATABASE`: Com a, respectiva, senha  do banco de dados
    * `SECRET_KEY`: Com a chave secreta para criptografar o token JWT. (**OPCIONAL**)
### Execução
- Executar o comando `mvn spring-boot:run` na pasta raiz do projeto
- Acessar o endereço `localhost:8081` no navegador. A port é a **8081**
- pronto a aplicação está rodando localmente!

### Testar API
- Importe a coleção de requisições do Postman que se encontra no diretório `suporte` para o Postman
- Configure a variável do Postman `{{server}}` com o endereço `localhost:8081`
- O primeiro passo para ter acesso as outras rotas é realizar a operação de login.
    * Tem dois tipos de usuário nessa ação o tipo **ADMIN** e o tipo **USER**. A diferença desses dois papeis é que o tipo **ADMIN** tem permissão para utilizar a rota `register` e inserir outros usuários no sistema.
    * Utilize para logar como administrador o username `daniboy` e a senha `daniel123`
    * Utilize para logar como usuário o username `neymarjr` e a senha `neymar123`

## Testar API na nuvem
*Até a presente data (16/08/2023) a aplicação está funcionando na nuvem utilizando os serviços da Digital Ocean*.

Para testar a API basta acessar o endereço `http://157.230.84.81:8081/` no navegador e utilizar a mesma coleção do Postman disponibilizada no diretório `suporte`, configurando a variável `{{server}}` para a url fornecida. A port é a **8081**.

