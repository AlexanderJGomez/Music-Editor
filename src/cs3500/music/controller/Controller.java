package cs3500.music.controller;

import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;

/**
 * Created by alexgomez on 11/21/15.
 */
public interface Controller {
  /**
   * Makes the controller go
   */
  void go() throws InvalidMidiDataException, InterruptedException, IOException;

}
