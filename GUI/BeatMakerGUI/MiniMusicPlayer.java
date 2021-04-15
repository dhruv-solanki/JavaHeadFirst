import javax.sound.midi.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;

// we need to listen for controller events
public class MiniMusicPlayer {
    static JFrame frame = new JFrame("My first Music video");
    static MyDrawPanel drawPanel;
    
    public static void main(String[] args) {
        MiniMusicPlayer player = new MiniMusicPlayer();
        player.play();
    }

    public void setupGUI() {
        drawPanel = new MyDrawPanel();
        frame.setContentPane(drawPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(30, 30, 300, 300);
        frame.setVisible(true);
    }

    public void play() {
        setupGUI();

        try {
            // make and open sequencer
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();

            // register for events with the sequencer.
            // method takes listener and an int array representing list of ControllerEvents you want, we want only one event #127
            int[] eventIWant = {127};
            sequencer.addControllerEventListener(drawPanel, eventIWant);

            // make a sequence and a track
            Sequence seq = new Sequence(Sequence.PPQ, 4);
            Track track = seq.createTrack();

            int r = 0;
            // make a bunch of events to make the notes keep going up
            // piano note 5 to piano note 61
            for(int i=0; i<120; i+=2) {
                r = (int) ((Math.random() * 50) + 1);
                // call our new makeEvent() method to make the message and event
                // and add the result to track.
                // NOTE ON (144) and NOTE OFF (128)
                track.add(makeEvent(144, 12, r, 100, i));

                // Here's how we pick up beat,
                // we insert our own ControllerEvent with an argument for event number #127, #176 is for Controller Event,
                // This event will do nothing, it will fire only so that we can get it.
                track.add(makeEvent(176, 1, 127, 0, i));

                track.add(makeEvent(128, 12, r, 100, i+2));
            }

            // start it running
            sequencer.setSequence(seq);
            sequencer.start();
            sequencer.setTempoInBPM(220);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    class MyDrawPanel extends JPanel implements ControllerEventListener {
        boolean msg = false;

        public void controlChange(ShortMessage event) {
            msg = true;
            repaint();
        }

        public void paintComponent(Graphics g) {
            if(msg) {
                Graphics2D g2d = (Graphics2D) g;

                int red = (int) (Math.random() * 250);
                int green = (int) (Math.random() * 250);
                int blue = (int) (Math.random() * 250);

                g2d.setColor(new Color(red, green, blue));

                int ht = (int) ((Math.random() * 120) + 10);
                int width = (int) ((Math.random() * 120) + 10);
                int x = (int) ((Math.random() * 60));
                int y = (int) ((Math.random() * 60));

                g2d.fillOval(x, y, ht, width);

                msg = false;
            }
        }
    } 

    // static utility method to create message and event
    public static MidiEvent makeEvent(int command, int channel, int one, int two, int tick) {
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
}