package course3;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private int port;
    private String ip;
    private Scanner scanner;

    public Client(int port, String ip) {
        this.port = port;
        this.ip = ip;
    }

    public void start() throws Exception {
        scanner = new Scanner(System.in);
        System.out.println("Введите Ваше имя");
        String name = scanner.nextLine();
        System.out.println("Добро пожаловать, " + name + " !");
        System.out.println("Для выхода из чата введите команду brake");
        Connection connection = new Connection(getSocket());


        Thread sendThread = new Thread(() -> {
            try {
                while (Thread.currentThread().isInterrupted() == false) {
                    String messageText = scanner.nextLine();
                    if (messageText.equalsIgnoreCase("brake")) {
                        connection.close();
                        Thread.currentThread().interrupt();
                        break;
                    }
                    connection.sendMessage(SimpleMessage.getMessage(name, messageText));
                }
            } catch (Exception e) {
                try {
                    connection.close();
                } catch (Exception e1) {
                    throw new RuntimeException(e1);
                }
                e.printStackTrace();
            }
        });

        Thread receiveThread = new Thread(() -> {
            try {
                while (Thread.currentThread().isInterrupted() == false) {
                    SimpleMessage fromServer = connection.readMessage();
                    System.out.println(fromServer);
                }
            } catch (IOException | ClassNotFoundException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        });
        sendThread.start();
        receiveThread.start();
    }

    private Socket getSocket() throws IOException {
        Socket socket = new Socket(ip, port);
        return socket;
    }
    public static void main(String[] args) {
        int port = 8090;
        String ip = "127.0.0.1";
        try {
            new Client(port, ip).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
