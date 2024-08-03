import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    private static final int PORT = 6666;
    private static Set<ClientHandler> clientHandlers = Collections.synchronizedSet(new HashSet<>());
    private static int clientIdCounter = 1;

    public static void main(String[] args) {
        System.out.println("Servidor de chat iniciado...");
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(clientSocket, clientIdCounter++);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ClientHandler implements Runnable {
        private Socket socket;
        private PrintWriter output;
        private BufferedReader input;
        private int clientId;

        public ClientHandler(Socket socket, int clientId) {
            this.socket = socket;
            this.clientId = clientId;
        }

        public void run() {
            try {
                input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                output = new PrintWriter(socket.getOutputStream(), true);

                // Envia o ID do cliente para ele
                output.println(clientId);

                synchronized (clientHandlers) {
                    clientHandlers.add(this);
                }

                String message;
                while ((message = input.readLine()) != null) {
                    String formattedMessage = "Cliente " + clientId + ": " + message;
                    System.out.println(formattedMessage);

                    synchronized (clientHandlers) {
                        for (ClientHandler handler : clientHandlers) {
                            handler.output.println(formattedMessage);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                synchronized (clientHandlers) {
                    clientHandlers.remove(this);
                }
            }
        }
    }
}
