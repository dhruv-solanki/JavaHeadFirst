import java.awt.*;
import javax.swing.*;

class MyDrawPanel3 extends JPanel {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        MyDrawPanel3 p = new MyDrawPanel3();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(p);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
    
        // fill the entire panel with black.
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

        Color startColor = getRandomColor();
        Color endColor = getRandomColor();

        GradientPaint gradient = new GradientPaint(70, 70, startColor, 150, 150, endColor);
        g2d.setPaint(gradient);
        g2d.fillOval(70, 70, 100, 100);
    }

    public Color getRandomColor() {
        int red = (int) (Math.random() * 255);
        int green = (int) (Math.random() * 255);
        int blue = (int) (Math.random() * 255);

        // make a color with RGB
        Color randomColor = new Color(red, green, blue);

        return randomColor;
    }
}