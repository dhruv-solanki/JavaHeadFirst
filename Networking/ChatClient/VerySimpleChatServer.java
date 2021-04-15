import java.net.*;
import java.io.*;
import java.util.*;

public class VerySimpleChatServer {
    ArrayList<PrintWriter> clientOutputStreams;

    public class ClientHandler implements Runnable {
        BufferedReader reader;
        Socket socket;

        public ClientHandler(Socket clientSocket) {
            try {
                socket = clientSocket;

                InputStreamReader isReader = new InputStreamReader(socket.getInputStream());
                reader = new BufferedReader(isReader);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        public void run() {
            String message;
            try {
                while((message = reader.readLine()) != null) {
                    System.out.println("read, " + message);

                    tellEveryone(message);
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        VerySimpleChatServer server = new VerySimpleChatServer();
        server.go();
    }

    public void go() {
        clientOutputStreams = new ArrayList<PrintWriter>();
        int port = 5000;

        try {
            ServerSocket serverSocket = new ServerSocket(5000);

            while(true) {
                Socket clientSocket = serverSocket.accept();

                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
                clientOutputStreams.add(writer);

                Thread t = new Thread(new ClientHandler(clientSocket));
                t.start();

                System.out.println("Got a connection");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void tellEveryone(String message) {
        Iterator<PrintWriter> it = clientOutputStreams.iterator();

        while(it.hasNext()) {
            try {
                PrintWriter writer = it.next();
                writer.println(message);
                writer.flush();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}