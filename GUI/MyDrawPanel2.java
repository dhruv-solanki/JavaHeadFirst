import java.awt.*;
import javax.swing.*;

class MyDrawPanel2 extends JPanel {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        MyDrawPanel2 p = new MyDrawPanel2();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(p);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        // fill the entire panel with black.
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        int red = (int) (Math.random() * 255);
        int green = (int) (Math.random() * 255);
        int blue = (int) (Math.random() * 255);

        // make a color with RGB
        Color randomColor = new Color(red, green, blue);

        g.setColor(randomColor);
        g.fillOval(70, 70, 100, 100);
    }
}