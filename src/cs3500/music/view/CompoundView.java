package cs3500.music.view;

import java.awt.*;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;

import cs3500.music.controller.KeyboardHandler;
import cs3500.music.controller.MouseHandler;
import cs3500.music.model.ANote;
import cs3500.music.model.MusicEditorModel;

/**
 * Created by alexgomez on 11/21/15.
 */
public class CompoundView implements GuiView {
  //Not private solely for testing, would otherwise be set to private
  public GuiViewFrame gui;
  public MidiViewImpl midi;
  KeyboardHandler kbh = new KeyboardHandler();
  MouseHandler mhandler = new MouseHandler();

  public CompoundView(MusicEditorModel m) {
    this.gui = new GuiViewFrame(m);
    this.midi = new MidiViewImpl(m);
  }


  /**
   * Creates the view
   */
  @Override
  public void create() throws InvalidMidiDataException, IOException, InterruptedException {
    gui.create();
    midi.create();
  }

  /**
   * Adds  an ANote
   *
   * @param anote adds this note
   */
  @Override
  public void addNote(ANote anote) {
    this.gui.addNote(anote);
    this.midi.addNote(anote);
  }

  public void go(int time) {
    midi.play(time);
    try {
      gui.create();
    } catch (InvalidMidiDataException e) {
    }
  }

  public void setKeyListener(KeyboardHandler k) {
    this.kbh = k;
    this.gui.setKeyListener(kbh);
  }

  public void setMouseListener(MouseHandler m) {
    this.mhandler = m;
    this.gui.setMouseListener(m);
  }


  public void pause() {
    try {
      midi.pause();
    } catch (InvalidMidiDataException e) {
      System.exit(0);
    }
  }

  /**
   * Presses the home button and goes to the beginning
   */
  public void home() {

    gui.home();

  }

  /**
   * Presses the end button and goes to the beginning
   */
  public void end() {

    gui.end();

  }

  /**
   * Scrolls to the Left
   */
  @Override
  public void scrollLeft() {
    gui.scrollLeft();
  }

  /**
   * Scrolls to the right
   */
  @Override
  public void scrollRight() {
    gui.scrollRight();
  }

  /**
   * Scrolls down
   */
  @Override
  public void scrollDown() {
    gui.scrollDown();
  }

  /**
   * scroll up
   */
  @Override
  public void scrollUp() {
    gui.scrollUp();
  }

  /**
   * Gets the note at the point
   *
   * @param point the point chosen
   * @return the ANote at that point
   */
  @Override
  public ANote getNoteAt(Point point) {
    return gui.getNoteAt(point);
  }
}
