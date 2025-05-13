import java.io.*;
import java.net.*;

public class Server {
    private ClientHandler client;
    public Server() throws IOException {
        ServerSocket serverSocket = new ServerSocket(8081);
        System.out.println("Server created!");
        Socket clientSocket = null;
        try {

            while(true){
                clientSocket = serverSocket.accept();
                client = new ClientHandler(clientSocket,this);
                client.run();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {

            clientSocket.close();
            serverSocket.close();
        }
    }

    public void sendMessage(String message) {
        client.sendMessage(message);

    }
}
