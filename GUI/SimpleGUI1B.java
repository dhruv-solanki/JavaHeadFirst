import javax.swing.*;
import java.awt.event.*;

public class SimpleGUI1B implements ActionListener {
    JButton button;

    public static void main(String[] args) {
        SimpleGUI1B gui = new SimpleGUI1B();
        gui.go();
    }

    public void go() {
        JFrame frame = new JFrame();
        button = new JButton("Click Me");

        // register your object with button
        button.addActionListener(this);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(button);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {
        button.setText("I've been clicked!");
    }
}