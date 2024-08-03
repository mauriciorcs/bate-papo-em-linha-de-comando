import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient {
    // Cores ANSI
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";

    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 6666;

    private static String clientId;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            System.out.println("Conectado ao servidor de chat.");

            // Obtém o ID do cliente
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            clientId = in.readLine(); // Assume que o servidor envia o ID do cliente na conexão inicial

            // Thread para leitura de mensagens do servidor
            new Thread(new MessageReader(socket)).start();

            // Thread para envio de mensagens ao servidor
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String message = scanner.nextLine();
                out.println(message);
                System.out.println(ANSI_CYAN + "Você: " + message + ANSI_RESET);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class MessageReader implements Runnable {
        private Socket socket;

        public MessageReader(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String message;
                while ((message = in.readLine()) != null) {
                    // Filtra mensagens enviadas pelo próprio cliente
                    if (!message.startsWith("Você: ") && !message.startsWith("Cliente " + clientId + ": ")) {
                        System.out.println(message);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
