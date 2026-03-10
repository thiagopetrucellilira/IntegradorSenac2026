# API de Doações de Alimentos

API RESTful desenvolvida com Spring Boot para gerenciar doações de alimentos, permitindo que usuários cadastrem doações e outros usuários solicitem essas doações.

## ☁️ Deploy em Produção (Railway)

### Serviço recomendado: [Railway](https://railway.app) — gratuito com US$5/mês de crédito

#### Passo a passo

**1. Preparar o repositório**
```bash
# Na raiz do projeto
git add .
git commit -m "feat: configuração para deploy no Railway"
git push
```

**2. Criar conta e novo projeto no Railway**
- Acesse [railway.app](https://railway.app) e faça login com GitHub
- Clique em **New Project → Deploy from GitHub repo**
- Selecione o repositório `IntegradorSenac2026`
- Quando perguntar o diretório de deploy, aponte para `/donations-api`

**3. Adicionar o banco de dados MySQL**
- No painel do projeto clique em **New → Database → MySQL**
- O Railway cria o banco e injeta automaticamente as variáveis: `MYSQLHOST`, `MYSQLPORT`, `MYSQLDATABASE`, `MYSQLUSER`, `MYSQLPASSWORD`

**4. Configurar as variáveis de ambiente do serviço da API**

No painel do serviço vá em **Variables** e adicione:

| Variável | Valor |
|---|---|
| `JWT_SECRET` | Uma string longa e aleatória (ex: `kDf9P2mX8nQ7rT4vL0sW5jA3gH6cE1bY`) |
| `SPRING_PROFILES_ACTIVE` | `prod` |

> As variáveis MySQL são injetadas automaticamente pelo Railway ao vincular o banco ao serviço.

**5. Aguardar o deploy**
- Railway detecta o `railway.toml` (builder `DOCKERFILE`) e constrói a imagem a partir do `Dockerfile` do projeto
- O `Dockerfile` realiza um build multi-stage: compila o JAR com Maven na primeira etapa e gera uma imagem JRE enxuta na segunda
- Nenhuma instalação de Docker é necessária na sua máquina — Railway executa tudo em nuvem
- O health check em `/actuator/health` confirma que a API está no ar
- A URL pública será exibida no painel (ex: `https://sua-api.up.railway.app`)

**6. Verificar o deploy**
```
GET https://sua-api.up.railway.app/actuator/health
GET https://sua-api.up.railway.app/swagger-ui/index.html
```

---

## 🚀 Funcionalidades

### Autenticação
- **POST** `/api/auth/register` - Cadastro de novo usuário
- **POST** `/api/auth/login` - Login e obtenção do token JWT
- **GET** `/api/auth/me` - Obter dados do usuário logado

### Usuários
- **GET** `/api/users/profile` - Obter perfil do usuário
- **PUT** `/api/users/profile` - Atualizar perfil do usuário

### Doações
- **GET** `/api/donations` - Listar doações (com filtros)
- **POST** `/api/donations` - Criar nova doação
- **GET** `/api/donations/{id}` - Obter detalhes da doação
- **PUT** `/api/donations/{id}` - Atualizar doação
- **DELETE** `/api/donations/{id}` - Deletar doação
- **GET** `/api/donations/my` - Listar minhas doações
- **GET** `/api/donations/categories` - Listar categorias disponíveis
- **GET** `/api/donations/cities` - Listar cidades disponíveis

### Matches (Solicitações)
- **POST** `/api/matches` - Solicitar doação
- **GET** `/api/matches/my` - Minhas solicitações
- **GET** `/api/matches/received` - Solicitações recebidas para minhas doações
- **PUT** `/api/matches/{id}/status` - Atualizar status da solicitação
- **GET** `/api/matches/donation/{donationId}` - Listar solicitações de uma doação

## 🛠️ Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.1.5**
- **Spring Security** (Autenticação JWT)
- **Spring Data JPA** (Persistência)
- **MySQL** (Banco de dados para desenvolvimento e produção)
- **SpringDoc OpenAPI** (Documentação Swagger)
- **Maven** (Gerenciamento de dependências)

## 📋 Pré-requisitos

- Java 17 ou superior
- Maven 3.6 ou superior

## 🔧 Como executar

1. **Clone o repositório**
   ```bash
   git clone <repository-url>
   cd donation-api
   ```

2. **Execute a aplicação**
   ```bash
   mvn spring-boot:run
   ```

3. **Acesse a aplicação**
   - API: http://localhost:8080
   - Swagger UI: http://localhost:8080/swagger-ui/index.html

## 📖 Documentação da API

A documentação completa da API está disponível através do Swagger UI em:
http://localhost:8080/swagger-ui/index.html

### Autenticação

A API utiliza autenticação JWT. Para acessar endpoints protegidos:

1. Faça login através do endpoint `/api/auth/login`
2. Use o token retornado no cabeçalho `Authorization: Bearer {token}`

### Exemplo de uso

1. **Cadastrar usuário:**
   ```json
   POST /api/auth/register
   {
     "name": "João Silva",
     "email": "joao@email.com",
     "password": "senha123",
     "role": "DONOR",
     "phone": "(11) 99999-9999",
     "city": "São Paulo",
     "state": "SP"
   }
   ```
   > Valores válidos para `role`: `DONOR`, `REQUESTER`, `ADMIN`

2. **Fazer login:**
   ```json
   POST /api/auth/login
   {
     "email": "joao@email.com",
     "password": "senha123"
   }
   ```

3. **Criar doação:**
   ```json
   POST /api/donations
   Authorization: Bearer {token}
   {
     "title": "Cesta Básica Completa",
     "description": "Arroz 5kg, feijão 1kg, óleo 2L, macarrão 500g, açúcar 1kg. Todos dentro do prazo de validade.",
     "category": "GRAOS_CEREAIS",
     "quantity": 3,
     "perishable": false,
     "expirationDate": "2026-06-01",
     "city": "São Paulo",
     "state": "SP"
   }
   ```
   > Categorias disponíveis: `GRAOS_CEREAIS`, `HORTIFRUTI`, `LATICINIOS`, `PROTEINAS`, `ENLATADOS_CONSERVAS`, `BEBIDAS`, `PADARIA_CONFEITARIA`, `TEMPEROS_CONDIMENTOS`, `REFEICAO_PRONTA`, `OUTROS`

## 🗄️ Banco de Dados

### Para Desenvolvimento
- Utiliza **MySQL** (padrão configurado em `application.properties`)
- Crie o banco antes de iniciar: `CREATE DATABASE donations_db;`
- As tabelas são criadas automaticamente pelo Hibernate (`ddl-auto=update`)
- O `DataInitializer` popula o banco com dados de teste na primeira execução (apenas se estiver vazio)

### Para Produção (Railway)
- As variáveis de ambiente `MYSQLHOST`, `MYSQLPORT`, `MYSQLDATABASE`, `MYSQLUSER`, `MYSQLPASSWORD` são injetadas automaticamente pelo Railway ao vincular um banco MySQL
- Configure também `JWT_SECRET` e `SPRING_PROFILES_ACTIVE=prod` no painel de variáveis do serviço

## 🔒 Segurança

- Senhas são criptografadas com BCrypt
- Autenticação via JWT com expiração de 24 horas
- Endpoints públicos: registro, login e listagem de doações
- Endpoints protegidos requerem token JWT válido

## 📊 Status das Doações

- **AVAILABLE**: Disponível para solicitação
- **RESERVED**: Reservada (solicitação aprovada)
- **COMPLETED**: Doação concluída
- **EXPIRED**: Doação expirada
- **CANCELLED**: Doação cancelada

## 📝 Status dos Matches

- **PENDING**: Aguardando resposta do doador
- **APPROVED**: Solicitação aprovada pelo doador
- **REJECTED**: Solicitação rejeitada pelo doador
- **IN_PROGRESS**: Doação em andamento
- **COMPLETED**: Doação concluída
- **CANCELLED**: Solicitação cancelada

## 🤝 Contribuindo

1. Fork o projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo `LICENSE` para mais detalhes.

## 👥 Equipe

- Desenvolvido para o Projeto Integrador - SENAC

## 📞 Suporte

Para dúvidas ou suporte, entre em contato através do email: contato@doacoes.com.br
