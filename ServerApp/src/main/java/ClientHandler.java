import java.net.*;
import java.io.*;
import java.util.Scanner;

class ClientHandler implements Runnable {

    private Server server;
    private PrintWriter outMessage;
    private Scanner inMessage;
    private Socket clientSocket = null;

    public ClientHandler(Socket socket, Server server) {
        try {
            this.server = server;
            this.clientSocket = socket;
            this.outMessage = new PrintWriter(socket.getOutputStream());
            this.inMessage = new Scanner(socket.getInputStream());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {

        try {
            while(true) {
                server.sendMessage("client connected");

                break;
            }

            while(true) {
                String clientMessage = inMessage.nextLine();
                if (clientMessage.equals("Start")){
                    server.sendMessage("Пошел нахуй!");
                }
                if (clientMessage.equals("Stop")){
                    break;
                }
                System.out.println(clientMessage);
                server.sendMessage(clientMessage);
            }
            Thread.sleep(100);
        } catch (Exception e) {

        }

    }

    public void sendMessage(String message) {
        outMessage.println(message);
    }
}