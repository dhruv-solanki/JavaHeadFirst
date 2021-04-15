import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class DiceService implements Service {
    JLabel label;
    JComboBox numOfDice;

    public JPanel getGUIPanel() {
        JPanel panel = new JPanel();
        JButton button = new JButton("Roll 'em!");

        String[] choices = {"1", "2", "3", "4", "5"};
        numOfDice = new JComboBox(choices);

        label = new JLabel("Dice Values Here");
        button.addActionListener(new RollEmListener());

        panel.add(numOfDice);
        panel.add(button);
        panel.add(label);

        return panel;
    }

    public class RollEmListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String diceOutput = "";

            String selection = (String) numOfDice.getSelectedItem();
            int numOfDiceToRoll = Integer.parseInt(selection);

            for (int i=0; i<numOfDiceToRoll; i++) {
                int r = (int) ((Math.random() * 6) + 1);

                diceOutput += (" " + r);
            }

            label.setText(diceOutput);
        }
    }
}