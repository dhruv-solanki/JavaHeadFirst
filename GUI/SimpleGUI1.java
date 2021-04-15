import javax.swing.*;

public class SimpleGUI1 {
    public static void main(String[] args) {
        // make a frame and a button.
        JFrame frame = new JFrame();
        JButton button = new JButton("Click Me");

        // this line makes the program quit as soon as you close the window.
        // else window will sit there on the screen forever. 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // add the button to the frame's content pane
        frame.getContentPane().add(button);

        // give the frame a size, in pixels
        frame.setSize(300, 300);

        // finally, make it visible!!!
        frame.setVisible(true);
    }
}