package cs3500.music.view;

import java.awt.*;

import javax.sound.midi.InvalidMidiDataException;
import javax.swing.*;

import cs3500.music.controller.KeyboardHandler;
import cs3500.music.controller.MouseHandler;
import cs3500.music.model.ANote;
import cs3500.music.model.MusicEditorModel;

/**
 * A skeleton Frame (i.e., a window) in Swing
 */
public final class GuiViewFrame extends javax.swing.JFrame implements GuiView {

  private JScrollPane displayPanel; // You may want to refine this to a subtype of JPanel
  private final MusicEditorModel model;
  private KeyboardHandler kbh = new KeyboardHandler();
  private MouseHandler mhandler = new MouseHandler();
  private boolean paused = false;

  /**
   * Creates new GuiView
   */
  protected GuiViewFrame(MusicEditorModel model) {
    this.displayPanel = new JScrollPane(new ConcreteGuiViewPanel(model));
    this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    this.getContentPane().add(displayPanel);
    this.pack();
    this.model = model;
  }

  public void initialize() {
    this.setVisible(true);
  }


  /**
   * Creates the view
   */
  @Override
  public void create() throws InvalidMidiDataException {
    this.initialize();
    if (model.getBeat() <= model.getEnd()) {
      if (Math.abs(displayPanel.getHorizontalScrollBar().getValue() -
              (2 * ConcreteGuiViewPanel.BEAT_WIDTH +
                      (model.getBeat() * ConcreteGuiViewPanel.BEAT_WIDTH))) > 1000) {
        displayPanel.getHorizontalScrollBar().
                setValue(displayPanel.getHorizontalScrollBar().getValue() + 1000);
      }
    }
  }

  /**
   * Adds  an ANote
   *
   * @param anote adds this note
   */
  @Override
  public void addNote(ANote anote) {
    this.model.addNote(anote);
  }

  /**
   * Sets the key listener
   *
   * @param k what ur setting it to
   */
  public void setKeyListener(KeyboardHandler k) {
    this.kbh = k;
    this.addKeyListener(kbh);
  }

  public void setMouseListener(MouseHandler m) {
    this.mhandler = m;
    this.displayPanel.addMouseListener(mhandler);
  }

  /**
   * Pauses the gui
   */
  @Override
  public void pause() {
    paused = !paused;
  }


  /**
   * Scrolls all the way left
   */
  @Override
  public void home() {
    displayPanel.getHorizontalScrollBar().setValue
            (displayPanel.getHorizontalScrollBar().getMinimum());
  }

  /**
   * Scrolls all the way right
   */
  @Override
  public void end() {
    displayPanel.getHorizontalScrollBar().setValue
            (displayPanel.getHorizontalScrollBar().getMaximum());
  }

  /**
   * Scrolls to the Left
   */
  @Override
  public void scrollLeft() {
    if (displayPanel.getHorizontalScrollBar().getValue() >= 50) {
      displayPanel.getHorizontalScrollBar().setValue
              (displayPanel.getHorizontalScrollBar().getValue() - 50);
    } else {
      displayPanel.getHorizontalScrollBar().setValue
              (displayPanel.getHorizontalScrollBar().getMinimum());
    }
  }

  /**
   * Scrolls to the right
   */
  @Override
  public void scrollRight() {
    if (displayPanel.getHorizontalScrollBar().getValue() <= this.getPreferredSize().width - 50) {
      displayPanel.getHorizontalScrollBar().setValue
              (displayPanel.getHorizontalScrollBar().getValue() + 50);
    } else {
      displayPanel.getHorizontalScrollBar().setValue
              (displayPanel.getHorizontalScrollBar().getMaximum());
    }
  }

  /**
   * Scrolls down
   */
  @Override
  public void scrollDown() {
    if (displayPanel.getVerticalScrollBar().getValue() <= this.getPreferredSize().height - 50) {
      displayPanel.getVerticalScrollBar().setValue
              (displayPanel.getVerticalScrollBar().getValue() + 50);
    } else {
      displayPanel.getVerticalScrollBar().setValue
              (displayPanel.getVerticalScrollBar().getMaximum());
    }
  }

  /**
   * scroll up
   */
  @Override
  public void scrollUp() {
    if (displayPanel.getVerticalScrollBar().getValue() >= 50) {
      displayPanel.getVerticalScrollBar().setValue
              (displayPanel.getVerticalScrollBar().getValue() - 50);
    } else {
      displayPanel.getVerticalScrollBar().setValue
              (displayPanel.getVerticalScrollBar().getMinimum());
    }
  }

  /**
   * Gets the note at the point
   *
   * @param point the point chosen
   * @return the ANote at that point
   * @throws IllegalArgumentException if no note at that point
   */
  @Override
  public ANote getNoteAt(Point point) {
    int time = point.x + displayPanel.getHorizontalScrollBar().getValue();
    int pitchNumber = point.y + displayPanel.getVerticalScrollBar().getValue();
    time = (time - 2 * ConcreteGuiViewPanel.BEAT_WIDTH) / ConcreteGuiViewPanel.BEAT_WIDTH;
    pitchNumber = (pitchNumber - ConcreteGuiViewPanel.BEAT_LENGTH) /
            ConcreteGuiViewPanel.BEAT_LENGTH;
    int max = model.getMinOrMaxOctave("max");
    pitchNumber = model.getLowOrHighNote("high", model.getMinOrMaxOctave("max")) + (max * 12)
            - pitchNumber;

    for (ANote n : model.notesPlaying(time)) {
      if (n.getPitchNum() == pitchNumber) {
        return n;
      }
    }

    throw new IllegalArgumentException("No note found");

  }

  public JScrollPane getScrollPanel() {
    return displayPanel;
  }


}
