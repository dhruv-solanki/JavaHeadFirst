import javax.swing.*;
import javax.sound.midi.*;

import java.awt.*;
import java.awt.event.*;

import java.util.*;
import java.io.*;

public class BeatBox {
    JPanel mainPanel;
    JFrame theFrame;
    ArrayList<JCheckBox> checkboxList;

    Sequencer sequencer;
    Sequence sequence;
    Track track;

    String[] instrumentNames = {
        "Bass Drum", "Closed Hi-Hat", "Open Hi-Hat", "Acoustic Snare", "Crash Cymbal", "Hand Clap", "High Tom", "Hi Bongo", "Maracas", "Whistle", "Low Conga", "Cowbell", "Vibraslap", "Low-mid Tom", "High Agogo", "Open Hi Conga" 
    };
    int[] instruments = {
        35, 42, 46, 38, 49, 39, 50, 60, 70, 72, 64, 56, 58, 47, 67, 63
    };

    public static void main(String[] args) {
        BeatBox box = new BeatBox();
        box.buildGUI();
    }

    public void buildGUI() {
        theFrame = new JFrame("Cyber BeatBox");
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BorderLayout layout = new BorderLayout();
        JPanel background = new JPanel(layout);
        background.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        checkboxList = new ArrayList<JCheckBox>();
        Box buttonBox = new Box(BoxLayout.Y_AXIS);

        JButton start = new JButton("Start");
        start.addActionListener(new MyStartListener());
        buttonBox.add(start);

        JButton stop = new JButton("Stop");
        stop.addActionListener(new MyStopListener());
        buttonBox.add(stop);

        JButton upTempo = new JButton("Tempo Up");
        upTempo.addActionListener(new MyUpTempoListener());
        buttonBox.add(upTempo);

        JButton downTempo = new JButton("Tempo Down");
        downTempo.addActionListener(new MyDownTempoListener());
        buttonBox.add(downTempo);

        JButton saveIt = new JButton("Save It");
        saveIt.addActionListener(new MySendListener());
        buttonBox.add(saveIt);

        JButton restore = new JButton("Restore");
        restore.addActionListener(new MyReadInListener());
        buttonBox.add(restore);

        Box nameBox = new Box(BoxLayout.Y_AXIS);
        for (int i=0; i<16; i++) {
            nameBox.add(new Label(instrumentNames[i]));
        }

        background.add(BorderLayout.EAST, buttonBox);
        background.add(BorderLayout.WEST, nameBox);

        theFrame.getContentPane().add(background);

        GridLayout grid = new GridLayout(16, 16);
        grid.setVgap(1);
        grid.setHgap(2);

        mainPanel = new JPanel(grid);
        background.add(BorderLayout.CENTER, mainPanel);

        for(int i=0; i<256; i++) {
            JCheckBox cb = new JCheckBox();
            cb.setSelected(false);
            
            checkboxList.add(cb);
            mainPanel.add(cb);
        }

        setUpMIDI();

        theFrame.setBounds(50, 50, 300, 300);
        theFrame.pack();
        theFrame.setVisible(true);
    }

    public void setUpMIDI() {
        try {
            sequencer = MidiSystem.getSequencer();
            sequencer.open();

            sequence = new Sequence(Sequence.PPQ, 4);
            track = sequence.createTrack();

            sequencer.setTempoInBPM(120);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void buildTrackAndStart() {
        int[] trackList = null;

        sequence.deleteTrack(track);
        track = sequence.createTrack();

        for(int i=0; i<16; i++) {
            trackList = new int[16];

            int key = instruments[i];

            for(int j=0; j<16; j++) {
                JCheckBox jc = (JCheckBox) checkboxList.get(j + (16*i));

                if(jc.isSelected()) {
                    trackList[j] = key;
                } else {
                    trackList[j] = 0;
                }
            }

            makeTracks(trackList);
            track.add(makeEvent(176, 1, 127, 0, 16));
        }

        track.add(makeEvent(192, 9, 1, 0, 15));

        try{
            sequencer.setSequence(sequence);
            sequencer.setLoopCount(sequencer.LOOP_CONTINUOUSLY);
            sequencer.start();
            sequencer.setTempoInBPM(120);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public class MyStartListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            buildTrackAndStart();
        }
    }

    public class MyStopListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            sequencer.stop();
        }
    }

    public class MyUpTempoListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            float tempoFactor = sequencer.getTempoFactor();
            sequencer.setTempoFactor((float) (tempoFactor * 1.03));
        }
    }

    public class MyDownTempoListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            float tempoFactor = sequencer.getTempoFactor();
            sequencer.setTempoFactor((float) (tempoFactor * 0.97));
        }
    }

    // new code
    public class MySendListener implements ActionListener {
        boolean[] checkBoxState = new boolean[256];

        public void actionPerformed(ActionEvent e) {

            for(int i=0; i<256; i++) {
                JCheckBox check = (JCheckBox) checkboxList.get(i);

                if(check.isSelected()) {
                    checkBoxState[i] = true;
                }
            }

            JFileChooser fileSave = new JFileChooser();
            fileSave.showSaveDialog(theFrame);

            saveFile(fileSave.getSelectedFile());
        }

        private void saveFile(File file) {
            try {
                FileOutputStream fileOutStream = new FileOutputStream(new File(file+".ser"));
                ObjectOutputStream os = new ObjectOutputStream(fileOutStream);

                os.writeObject(checkBoxState);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    // new code 
    public class MyReadInListener implements ActionListener {
        boolean[] checkBoxState = null;

        public void actionPerformed(ActionEvent e) {
            JFileChooser fileOpen = new JFileChooser();
            fileOpen.showOpenDialog(theFrame);

            openFile(fileOpen.getSelectedFile());
        
            for(int i=0; i<256; i++) {
                JCheckBox check = (JCheckBox) checkboxList.get(i);

                if(checkBoxState[i]) {
                    check.setSelected(true);
                } else {
                    check.setSelected(false);
                }
            }

            sequencer.stop();
            buildTrackAndStart();
        }

        private void openFile(File file) {
            try {
                FileInputStream fileInStream = new FileInputStream(file);
                ObjectInputStream is = new ObjectInputStream(fileInStream);

                checkBoxState = (boolean[]) is.readObject();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void makeTracks(int[] list) {
        for(int i=0; i<16; i++) {
            int key = list[i];

            if(key != 0) {
                track.add(makeEvent(144, 9, key, 100, i));
                track.add(makeEvent(128, 9, key, 100, i+1));
            }
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
}