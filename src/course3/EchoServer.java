package course3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;


public class EchoServer {


    private int port;
    private Connection connection;
    private ArrayBlockingQueue<SimpleMessage> blockingQueue = new ArrayBlockingQueue<>(15, true);
    private CopyOnWriteArrayList<Connection> connections = new CopyOnWriteArrayList<>();

    public EchoServer(int port){
        this.port = port;
    }

    public void start() throws IOException, ClassNotFoundException {
        try (ServerSocket serverSocket = new ServerSocket(port)){
            System.out.println("Чат запущен...");
            SendAll sendAll = new SendAll();
            sendAll.start();
            while (true){
                Socket socket = serverSocket.accept();
                ////
                User user = new User(socket);
                user.start();
            }
        }
    }

    class User extends Thread {
        private Connection connection;
        private long id;

        public User(Socket socket) throws IOException{
            this.connection = new Connection(socket);
        }

        @Override
        public void run() {
            id = Thread.currentThread().getId();
            connection.setId((int)id);
            connections.add(connection);
            while (Thread.currentThread().isInterrupted() == false) {
                try {
                    SimpleMessage message = connection.readMessage();
                    message.setId((int)id);
                    printMessage(message);
                    blockingQueue.add(message);
                } catch (IOException | ClassNotFoundException e) {
                    connections.remove(connection);
                    Thread.currentThread().interrupt();
                    System.out.println(Thread.currentThread().getName() + " out of chat");

                }
            }
        }
    }
    class SendAll extends Thread {
        @Override
        public void run() {
            while (Thread.currentThread().isInterrupted() == false) {
                SimpleMessage message;
                try {
                    message = blockingQueue.take();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                for (Connection currentConnection : connections) {
                    try {
                        if (currentConnection.getId() != message.getId()) {
                            currentConnection.sendMessage(message);
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }


    private void printMessage(SimpleMessage message){
        System.out.println("получено сообщение: " + message);
    }

    public static void main(String[] args) {
        int port = 8090;
        EchoServer messageServer = new EchoServer(port);
        try {
            messageServer.start();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}