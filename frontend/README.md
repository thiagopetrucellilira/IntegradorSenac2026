# Frontend — Plataforma de Doações Locais

Interface web desenvolvida com Angular para a plataforma de doações de alimentos. Conecta doadores e beneficiários permitindo publicar, buscar e solicitar doações de forma local e transparente.

## 🛠️ Tecnologias Utilizadas

- **Angular 20** — Framework principal
- **Angular Material** — Biblioteca de componentes UI
- **TypeScript** — Linguagem de programação
- **RxJS** — Programação reativa
- **SCSS** — Pré-processador CSS
- **Angular CDK** — Utilitários de componentes

## 📋 Pré-requisitos

- **Node.js 18+** — [Download](https://nodejs.org/)
- **Angular CLI** — `npm install -g @angular/cli`
- Backend `donations-api` em execução em `http://localhost:8080`

## 🔧 Como executar

```bash
# Instalar dependências
npm install

# Iniciar servidor de desenvolvimento
npm start
```

A aplicação estará disponível em: `http://localhost:4200`

## 🗺️ Rotas da Aplicação

| Rota | Componente | Proteção |
|---|---|---|
| `/` | Home | Pública |
| `/login` | Login | Pública |
| `/register` | Register | Pública |
| `/donations` | Donations | Pública |
| `/create-donation` | CreateDonation | 🔒 Requer login |
| `/profile` | Profile | 🔒 Requer login |
| `/dashboard` | Dashboard | 🔒 Requer login |

> Rotas protegidas redirecionam para `/login` caso o usuário não esteja autenticado (`AuthGuard`).

## 🎨 Componentes Principais

- **Home** — Página inicial com apresentação da plataforma
- **Login / Register** — Autenticação e cadastro de novos usuários
- **Donations** — Listagem pública de doações disponíveis com filtros
- **CreateDonation** — Formulário para publicar uma nova doação de alimento
- **Dashboard** — Painel do usuário com suas doações e solicitações
- **Profile** — Visualização e edição do perfil do usuário
- **Navbar** — Barra de navegação global

## 🔐 Autenticação

O frontend utiliza **JWT** para autenticação:
- O token é armazenado no `localStorage` após o login
- O `AuthInterceptor` injeta automaticamente o header `Authorization: Bearer <token>` em todas as requisições ao backend
- O `AuthGuard` protege rotas que exigem usuário autenticado

## 🌐 Configuração do Backend

A URL da API é definida em `src/environments/environment.ts`:

```typescript
export const environment = {
  production: false,
  apiUrl: 'http://localhost:8080/api'
};
```

Altere `apiUrl` para apontar para a URL de produção quando necessário.

## 🏗️ Build para produção

```bash
ng build
```

Os artefatos de build serão gerados no diretório `dist/`. A build de produção é otimizada automaticamente pelo Angular.

## 🧪 Testes

```bash
# Testes unitários com Karma
ng test
```
