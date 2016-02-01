package cs3500.music.view;

import javax.sound.midi.InvalidMidiDataException;

/**
 * Created by alexgomez on 11/25/15.
 */
public interface MidiView extends View {
  /**
   * Plays the Anotes at this time
   *
   * @param time the time you want to be played
   */
  void play(int time);

  /**
   * Pauses the view
   */
  void pause() throws InvalidMidiDataException;



}
