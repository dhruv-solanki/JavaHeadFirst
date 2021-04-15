import javax.swing.*;
import javax.sound.midi.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class MiniMusicService implements Service {
    MyDrawPanel myPanel;

    public JPanel getGUIPanel() {
        JPanel mainPanel = new JPanel();

        myPanel = new MyDrawPanel();

        JButton playItButton = new JButton("Play It");
        playItButton.addActionListener(new PlayItListener());

        mainPanel.add(myPanel);
        mainPanel.add(playItButton);

        return mainPanel;
    }

    public class PlayItListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            try {
                Sequencer sequencer = MidiSystem.getSequencer();
                sequencer.open();

                sequencer.addControllerEventListener(myPanel, new int[] {127});

                Sequence seq = new Sequence(Sequence.PPQ, 4);
                Track track = seq.createTrack();

                for (int i=0; i<100; i++) {
                    int rNum = (int) ((Math.random() * 50) + 1);

                    if(rNum < 38) {
                        track.add(makeEvent(144, 1, rNum, 100, i));
                        track.add(makeEvent(176, 1, 127, 0, i));
                        track.add(makeEvent(128, 1, rNum, 100, i+2));
                    }
                }

                sequencer.setSequence(seq);
                sequencer.start();
                sequencer.setTempoInBPM(220);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        public MidiEvent makeEvent(int command, int channel, int one, int two, int tick) {
            MidiEvent event = null;
            try {
                ShortMessage msg = new ShortMessage();
                msg.setMessage(command, channel, one, two);
                event = new MidiEvent(msg, tick);
            } catch(Exception e) {
                e.printStackTrace();
            }

            return event;
        }

        class MyDrawPanel extends JPanel implements ControllerEventListener {
            boolean flag = false;

            public void controlChange(ShortMessage msg) {
                flag = true;
                repaint();
            }

            public Dimension getPrefferedSize() {
                return new Dimension(300, 300);
            }

            public void paintComponent(Graphics g) {
                if(flag) {
                    Graphics2D g2d = (Graphics2D) g;

                    int red = (int) (Math.random() * 250);
                    int green = (int) (Math.random() * 250);
                    int blue = (int) (Math.random() * 250);

                    g2d.setColor(new Color(red, green, blue));

                    int ht = (int) ((Math.random() * 120) + 10);
                    int width = (int) ((Math.random() *120) + 10);

                    int x = (int) ((Math.random() * 40) + 10);
                    int y = (int) ((Math.random() * 40) + 10);

                    g2d.fillRect(x, y, ht, width);
                    flag = false;
                }
            }
        }
    }
}