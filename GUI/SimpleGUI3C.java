import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleGUI3C implements ActionListener {
    JFrame frame;

    public static void main(String[] args) {
        SimpleGUI3C gui = new SimpleGUI3C();
        gui.go();
    }

    public void go() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton button = new JButton("Change Colors");
        button.addActionListener(this);

        MyDrawPanel drawPanel = new MyDrawPanel();

        frame.getContentPane().add(BorderLayout.SOUTH, button);
        frame.getContentPane().add(BorderLayout.CENTER, drawPanel);

        frame.setSize(300, 300);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {
        frame.repaint();
    }
}

class MyDrawPanel extends JPanel {
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