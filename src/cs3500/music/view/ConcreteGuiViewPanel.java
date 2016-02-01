package cs3500.music.view;

import java.awt.*;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.*;

import cs3500.music.model.ANote;
import cs3500.music.model.Ending;
import cs3500.music.model.MusicEditorModel;
import cs3500.music.model.Repeat;

/**
 * A dummy view that simply draws a string
 */
public class ConcreteGuiViewPanel extends JPanel {
  MusicEditorModel model;
  int lowestOctave;
  int highestOctave;
  int minNote;
  int maxNote;
  int end;
  int lowPitchNum;
  int maxPitchNum;
  static final int BEAT_WIDTH = 20;
  static final int BEAT_LENGTH = 20;


  public ConcreteGuiViewPanel(MusicEditorModel model) {
    this.model = model;
  }


  @Override
  public void paint(Graphics g) {
    lowestOctave = model.getMinOrMaxOctave("min");
    highestOctave = model.getMinOrMaxOctave("max");
    minNote = model.getLowOrHighNote("low", lowestOctave);
    maxNote = model.getLowOrHighNote("high", highestOctave);
    end = model.getEnd();
    lowPitchNum = lowestOctave * 12 + minNote;
    maxPitchNum = highestOctave * 12 + maxNote;
    int numPitches = maxPitchNum - lowPitchNum + 1;

    super.paint(g);
    if (model.getBeat() <= model.getEnd()) {
      g.setColor(Color.RED);
      g.drawLine(2 * BEAT_WIDTH + (model.getBeat() * BEAT_WIDTH), BEAT_LENGTH,
              2 * BEAT_WIDTH + (model.getBeat() * BEAT_WIDTH), (numPitches + 1) * BEAT_LENGTH);
    }
    this.repaint();


    for (int i = 0; i <= end; i += 16) {
      g.setColor(Color.BLACK);
      g.drawString(Integer.toString(i), BEAT_WIDTH + BEAT_WIDTH * (i + 1) - 7, BEAT_LENGTH / 2);
    }

    int numNotes = 1;
    for (int oct = highestOctave; oct >= lowestOctave; oct--) {
      if (oct == lowestOctave) {
        for (int i = 11; i >= minNote; i--) {
          g.drawString(MusicEditorModel.Pitch.values()[i].value + Integer.toString(oct),
                  0, 15 + (BEAT_LENGTH * numNotes));
          numNotes++;
        }
      } else if (oct == highestOctave) {
        for (int j = maxNote; j >= 0; j--) {
          g.drawString(MusicEditorModel.Pitch.values()[j].value + Integer.toString(oct),
                  0, 15 + (BEAT_LENGTH * numNotes));
          numNotes++;
        }
      } else {
        for (int i = 11; i >= 0; i--) {
          g.drawString(MusicEditorModel.Pitch.values()[i].value + Integer.toString(oct),
                  0, 15 + (BEAT_LENGTH * numNotes));
          numNotes++;
        }
      }

    }


    for (int i = 1; i < numNotes; i++) {
      for (int j = 0; j <= (end / 4); j++) {
        g.drawRect(2 * BEAT_WIDTH + 4 * BEAT_WIDTH * j, BEAT_LENGTH * i,
                4 * BEAT_WIDTH, BEAT_LENGTH);
      }
    }


    for (int i = 0; i <= end; i++) {
      if (!model.notesPlaying(i).isEmpty()) {
        for (ANote n : model.notesPlaying(i)) {
          if (n.getStart() == i) {
            g.setColor(Color.black);
          } else {
            g.setColor(Color.green);
          }
          g.fillRect(2 * BEAT_WIDTH + (BEAT_WIDTH * i),
                  BEAT_LENGTH + BEAT_LENGTH * (maxPitchNum - n.getPitchNum())
                  , BEAT_WIDTH, BEAT_LENGTH);
        }

      }

    }

    for (Repeat r : model.getRepeats()) {
      Graphics2D g2 = (Graphics2D) g;
      g2.setStroke(new BasicStroke(5));
      g.setColor(Color.magenta);
      if (r.getStart() != 0) {
        g.drawLine(2 * BEAT_WIDTH + (r.getStart() * BEAT_WIDTH), BEAT_LENGTH,
                2 * BEAT_WIDTH + (r.getStart() * BEAT_WIDTH), (numPitches + 1) * BEAT_LENGTH);
      }
      g2.setStroke(new BasicStroke(1));
      g.drawLine(2 * BEAT_WIDTH + (r.getStart() * BEAT_WIDTH) + 5, BEAT_LENGTH,
              2 * BEAT_WIDTH + (r.getStart() * BEAT_WIDTH) + 5, (numPitches + 1) * BEAT_LENGTH);


      g2.setStroke(new BasicStroke(5));
      g.drawLine(2 * BEAT_WIDTH + (r.getEnd() * BEAT_WIDTH), BEAT_LENGTH,
              2 * BEAT_WIDTH + (r.getEnd() * BEAT_WIDTH), (numPitches + 1) * BEAT_LENGTH);
      g2.setStroke(new BasicStroke(1));
      g.drawLine(2 * BEAT_WIDTH + (r.getEnd() * BEAT_WIDTH) - 5, BEAT_LENGTH,
              2 * BEAT_WIDTH + (r.getEnd() * BEAT_WIDTH) - 5, (numPitches + 1) * BEAT_LENGTH);

    }

    for (int i = 0; i < model.getEndings().size(); i++) {
      ArrayList<Ending> temp = model.getEndings().get(i);
      for (int x = 0; x < temp.size(); x++) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(5));
        if (x == 0) {
          g2.setStroke(new BasicStroke(3));
          g.setColor(Color.PINK);
          g.drawLine(2 * BEAT_WIDTH + (temp.get(x).getEnd() * BEAT_WIDTH), BEAT_LENGTH,
                  2 * BEAT_WIDTH + (temp.get(x).getEnd() * BEAT_WIDTH), (numPitches
                          + 1) * BEAT_LENGTH);
        } else if (x == temp.size() - 1) {
          g.setColor(Color.YELLOW);

          g.drawLine(2 * BEAT_WIDTH + (temp.get(x).getEnd() * BEAT_WIDTH), BEAT_LENGTH,
                  2 * BEAT_WIDTH + (temp.get(x).getEnd() * BEAT_WIDTH), (numPitches + 1)
                          * BEAT_LENGTH);
        } else {
          g.setColor(Color.YELLOW);

          g.drawLine(2 * BEAT_WIDTH + (temp.get(x).getEnd() * BEAT_WIDTH), BEAT_LENGTH,
                  2 * BEAT_WIDTH + (temp.get(x).getEnd() * BEAT_WIDTH), (model.getEnd() + 1)
                          * BEAT_LENGTH);
          g2.setStroke(new BasicStroke(1));
          g.drawLine(2 * BEAT_WIDTH + (temp.get(x).getEnd() * BEAT_WIDTH) - 5, BEAT_LENGTH,
                  2 * BEAT_WIDTH + (temp.get(x).getEnd() * BEAT_WIDTH) - 5, (numPitches +
                          1) * BEAT_LENGTH);
        }

        g2.setStroke(new BasicStroke(5));
        g.setColor(Color.lightGray);
        g.drawLine(2 * BEAT_WIDTH + (temp.get(x).getEndingStart() * BEAT_WIDTH), BEAT_LENGTH,
                2 * BEAT_WIDTH + (temp.get(x).getEndingStart() * BEAT_WIDTH),
                (numPitches + 1) * BEAT_LENGTH);
      }
    }

  }


  public Dimension getPreferredSize() {
    return new Dimension(250 + BEAT_WIDTH * model.getEnd(),
            160 + 12 * BEAT_LENGTH * (highestOctave - lowestOctave + 1)
                    + minNote - 12 +
                    maxNote);
  }

  /**
   * Adds a mouse listener
   */
  public void addMouseListener(MouseListener mouse) {
    super.addMouseListener(mouse);
  }


}



