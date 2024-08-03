# Prática: Sockets TCP e UDP

## Pré-requisitos

- `git`
- Java 8+ (JDK)

## Descrição

Baixe o código da prática, usando o comando do git:

`git clone https://github.com/marcuswac/sd-ufpb.git`

Entre no diretório desta prática:

`cd labs/lab-sockets`

Investigue os códigos dos clientes e servidores TCP e UDP. Em seguida, execute os códigos e entenda o que está acontecendo.

O código está localizado no diretório `src` e os executáveis no diretório `bin`. Se precisar recompilar o código na interface de linha de comando, entre no diretório raíz do projeto e execute o comando abaixo:

`javac src/* -d bin`

OBS 1: se os comandos acima não funcionarem no Windows, tente substituir a barra (`/`) por contra-barra (`\`). Vocês também podem usar alguma IDE da preferência de vocês.

OBS 2: As implementações do sistema TCP e UDP não se comunicam e são dois sistemas diferentes. Quando for realizar os testes, rode o sistema de um protocolo de cada vez. O cliente TCP só fala com o servidor TCP e o cliente UDP só fala com o servidor UDP. Além disso, sempre rode o servidor antes de rodar seus clientes.


## Exemplo Socket TCP

O exemplo de socket TCP possui dois arquivos: `SimpleTCPServer.java` e `SimpleTCPClient.java`. Analise o código das duas classes e em seguida execute o exemplo, rodando primeiro o servidor e depois o cliente.

Para executar o servidor TCP na linha de comando, rode:

`java -cp bin SimpleTCPServer`

Para executar o cliente TCP na linha de comando, rode:

`java -cp bin SimpleTCPClient`

Em seguida, digite na linha de comando do cliente a mensagem que desejar enviar para o servidor e veja o que acontece. Analise novamente o código para identificar cada passo do que acontece.

## Exemplo Socket UDP

O exemplo de socket UDP possui dois arquivos: `SimpleUDPServer.java` e `SimpleUDPClient.java`. Analise o código das duas classes e em seguida execute o exemplo, rodando primeiro o servidor e depois o cliente.

Para executar o servidor UDP na linha de comando, rode:

`java -cp bin SimpleUDPServer`

Para executar o cliente UDP na linha de comando, rode:

`java -cp bin SimpleUDPClient`

Em seguida, digite na linha de comando do cliente a mensagem que desejar enviar para o servidor e veja o que acontece. Analise novamente o código para identificar cada passo do que acontece.

Explore diferentes situações, por exemplo, você pode rodar mais de um cliente ao mesmo tempo para ver se o servidor consegue receber conexões e mensagens simultâneas de clientes.



# Chat em Linha de Comando

Este projeto é um sistema de chat baseado em linha de comando que utiliza sockets TCP para comunicação entre um servidor e múltiplos clientes. O código permite a troca de mensagens em tempo real entre os usuários.

## Estrutura do Código

O código é dividido em duas partes principais:

- **Servidor (`ChatServer.java`)**: Gerencia conexões de clientes e distribui mensagens entre eles.
- **Cliente (`ChatClient.java`)**: Permite que o usuário envie e receba mensagens do servidor.

## Servidor

### `ChatServer`

- **Descrição**: O servidor escuta por conexões de clientes em uma porta especificada e retransmite mensagens recebidas para todos os clientes conectados.
- **Como Funciona**:
    - Cria um `ServerSocket` na porta 6666 e aguarda conexões de clientes.
    - Para cada cliente conectado, um `ClientHandler` é criado em uma nova thread.
    - Recebe mensagens de clientes e as distribui para todos os clientes conectados.

## Cliente

### `ChatClient`

- **Descrição**: O cliente conecta-se ao servidor e permite ao usuário enviar e receber mensagens.
- **Como Funciona**:
    - Conecta-se ao servidor e obtém um identificador único.
    - Envia mensagens digitadas pelo usuário para o servidor.
    - Recebe e exibe mensagens de outros clientes.
    - Não exibe suas próprias mensagens recebidas para evitar redundância.

## Funções Importantes

### Servidor (`ChatServer.java`)

- **`start`**:
    - **Descrição**: Inicia o servidor, escuta por conexões e distribui mensagens.
    - **Como Funciona**:
        - Cria o `ServerSocket` e aceita conexões de clientes.
        - Para cada cliente, cria uma nova thread para gerenciar a comunicação.

### Cliente (`ChatClient.java`)

- **`start`**:
    - **Descrição**: Conecta o cliente ao servidor e permite a troca de mensagens.
    - **Como Funciona**:
        - Conecta-se ao servidor e inicia threads para enviar e receber mensagens.
        - Exibe as mensagens recebidas com um prefixo indicando o cliente remetente.

## Utilização

1. **Iniciar o Servidor**:
    - Execute o servidor em uma linha de comando:
      ```sh
      java -cp bin ChatServer
      ```
    - O servidor começará a escutar na porta 6666 por conexões de clientes.

2. **Iniciar o Cliente**:
    - Execute o cliente em uma linha de comando:
      ```sh
      java -cp bin ChatClient
      ```
    - Cada cliente se conectará ao servidor e exibirá um identificador único.

3. **Troca de Mensagens**:
    - No terminal do cliente, digite mensagens para enviar ao servidor.
    - As mensagens enviadas por um cliente serão exibidas para todos os clientes conectados.

## Observações

- **Identificação do Cliente:** Mensagens enviadas são prefixadas com "Você:" para o cliente que enviou e "Cliente X:" para os outros clientes.
- **Suporte a Múltiplos Clientes:** O servidor lida com múltiplos clientes simultaneamente.
