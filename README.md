# Java Conversion API

Este projeto é uma calculadora de conversão de moedas desenvolvida em Java. Ele utiliza a [ExchangeRate API](https://www.exchangerate-api.com/) para obter as taxas de câmbio em tempo real e permite que o usuário converta valores entre diferentes moedas.

## Funcionalidades

- Conversão de valores entre moedas suportadas.
- Suporte às seguintes moedas:
  - ARS - Peso argentino
  - BOB - Boliviano boliviano
  - BRL - Real brasileiro
  - CLP - Peso chileno
  - COP - Peso colombiano
  - USD - Dólar americano
- Loop contínuo para realizar múltiplas conversões até que o usuário escolha sair.
- Configuração da chave da API em um arquivo `.env` para maior segurança.

## Estrutura do Projeto

```
src/
├── App.java                # Classe principal que gerencia a interação com o usuário
├── service/
│   ├── CurrencyService.java # Classe responsável por consumir a API e realizar as conversões
├── model/
│   ├── Currency.java        # Classe modelo para representar uma moeda (opcional)
.env                         # Arquivo de configuração para armazenar a chave da API
.gitignore                   # Arquivo para ignorar arquivos sensíveis, como .env
```

## Pré-requisitos

- Java 11 ou superior.
- Biblioteca Gson configurada no projeto para manipulação de JSON.
- Biblioteca `dotenv-java` configurada para gerenciar variáveis de ambiente.
- Conexão com a internet para acessar a API de taxas de câmbio.

## Configuração

1. Crie um arquivo `.env` na raiz do projeto com o seguinte conteúdo:
   ```env
   API_KEY=SUA_CHAVE_AQUI
   ```
2. Certifique-se de que o arquivo `.env` está listado no `.gitignore` para evitar que seja versionado:
   ```
   # Ignorar arquivos de configuração sensíveis
   .env
   ```

## Como Executar

1. Clone o repositório:
   ```bash
   git clone <URL_DO_REPOSITORIO>
   cd java-conversion-api
   ```

2. Compile o projeto:
   ```bash
   javac -cp .:gson.jar:dotenv-java.jar src/**/*.java
   ```

3. Execute o programa:
   ```bash
   java -cp .:gson.jar:dotenv-java.jar src/App
   ```

4. Siga as instruções no terminal para realizar conversões de moedas.

## Observações

- Certifique-se de que a chave da API (`API_KEY`) está configurada corretamente no arquivo `.env`.
- Caso a API esteja indisponível ou a chave seja inválida, o programa exibirá uma mensagem de erro.
- Para adicionar suporte a novas moedas, verifique a documentação da [ExchangeRate API](https://www.exchangerate-api.com/).
