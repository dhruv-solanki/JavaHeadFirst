import java.awt.*;
import javax.swing.*;

class MyDrawingPanel extends JPanel {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        MyDrawingPanel p = new MyDrawingPanel();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(p);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.orange);
        g.fillRect(20, 50, 100, 100);
    }
}