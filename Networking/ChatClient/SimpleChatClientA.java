import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.net.*;
import java.io.*;

public class SimpleChatClientA {
    JTextField outGoing;
    PrintWriter writer;
    Socket socket;

    public void buildGUI() {
        JFrame frame = new JFrame("Ludicrously Simple Chat Client");
        JPanel mainPanel = new JPanel();
        
        outGoing = new JTextField(20);

        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new SendButtonListener());

        mainPanel.add(outGoing);
        mainPanel.add(sendButton);

        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        setUpNetworking();

        frame.setSize(400, 500);
        frame.setVisible(true);
    }

    private void setUpNetworking() {
        String ip = "127.0.0.1";
        int port = 5000;
       
        try {
            socket = new Socket(ip, port);

            writer = new PrintWriter(socket.getOutputStream());
            System.out.println("Networking established!");

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

    public static void main(String[] args) {
        SimpleChatClientA client = new SimpleChatClientA();
        client.buildGUI();
    }
}