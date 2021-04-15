import java.net.*;
import java.io.*;

public class DailyAdviceClient {
    public void connectToServer() {
        String ip = "127.0.0.1";
        int port = 4242;
        
        try {
            Socket s = new Socket(ip, port);

            InputStreamReader streamReader = new InputStreamReader(s.getInputStream());
            BufferedReader reader = new BufferedReader(streamReader);

            String advice = reader.readLine();
            System.out.println("Today you should: " + advice);

            reader.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DailyAdviceClient client = new DailyAdviceClient();
        client.connectToServer();
    }
}
