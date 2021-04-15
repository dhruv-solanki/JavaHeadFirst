import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.net.*;
import java.io.*;
import java.util.*;

public class SimpleChatClient {
    JTextArea inComing;
    JTextField outGoing;
    BufferedReader reader;
    PrintWriter writer;
    Socket socket;

    public static void main(String[] args) {
        SimpleChatClient client = new SimpleChatClient();
        client.buildGUI();
    }

    public void buildGUI() {
        JFrame frame = new JFrame("Ludicrously Simple Chat Client");
        JPanel mainPanel = new JPanel();

        inComing = new JTextArea(15, 50);
        inComing.setLineWrap(true);
        // inComing.setWrapStyleWord(true);
        inComing.setEditable(false);

        JScrollPane qScroller = new JScrollPane(inComing);
        qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    
        outGoing = new JTextField(20);
        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new SendButtonListener());

        mainPanel.add(qScroller);
        mainPanel.add(outGoing);
        mainPanel.add(sendButton);

        setUpNetworking();

        Thread readerThread = new Thread(new IncomingReader());
        readerThread.start();

        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.setSize(400, 500);
        frame.setVisible(true);
    }

    private void setUpNetworking() {
        String ip = "127.0.0.1";
        int port = 5000;

        try {
            socket = new Socket(ip, port);

            InputStreamReader streamReader = new InputStreamReader(socket.getInputStream());
            reader = new BufferedReader(streamReader);
            writer = new PrintWriter(socket.getOutputStream());

            System.out.println("Networking Established!");
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public class SendButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            try {
                writer.println(outGoing.getText());
                writer.flush();
            } catch(Exception e) {
                e.printStackTrace();
            }

            outGoing.setText("");
            outGoing.requestFocus();
        }
    }

    public class IncomingReader implements Runnable {
        public void run() {
            String message;
            try {
                while((message = reader.readLine()) != null) {
                    System.out.println("read, " + message);

                    inComing.append(message + "\n");
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}