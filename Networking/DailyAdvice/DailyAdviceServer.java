import java.net.*;
import java.io.*;

public class DailyAdviceServer {
    private String getAdvice() {
        String[] adviceList = {
            "Take smaller bites", "Go for the tight jeans. No they do not make you look fat", "one word: Inappropriate", "Just for today, be honest. Tell your boss what you *really* think", "You might want to rethink that haircut.", "Baby steps, while you are in relationship.", "Do not drink coffee, everytime you are down. It will save money!"
        };

        int random = (int) (Math.random() * adviceList.length);

        return adviceList[random];
    }

    public void connectToClient() {
        String ip = "127.0.0.1";
        int port = 4242;

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server is running, " + ip + ":" + port);

            while(true) {
                Socket socket = serverSocket.accept();

                PrintWriter writer = new PrintWriter(socket.getOutputStream());

                String advice = getAdvice();
                writer.println(advice);

                writer.close();
                System.out.println("My Advice: " + advice);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DailyAdviceServer server = new DailyAdviceServer();
        server.connectToClient();
    }
}