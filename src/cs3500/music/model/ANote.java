package cs3500.music.model;

/**
 * Created by alexgomez on 11/4/15.
 */
public abstract class ANote {

  /*
  This has been added to our original model in order to not leak implementation details
   */


  protected MusicEditorModel.Pitch pitch;
  protected int octave;
  protected int start;
  protected int end;
  protected int instrument;
  protected int volume;
  protected int pitchNum;
  /**
   * INVARIANT: octave >= -1 and <=10
   * INVARIANT: start >= 0
   * INVARIANT: end > start
   */

  /**
   * @param pitch  the pitch of the note
   * @param octave the octave of the note
   * @param start  the start time of the note
   * @param end    the end of the note
   */
  protected ANote(MusicEditorModel.Pitch pitch, int octave, int start, int end, int instrument,
                  int volume) {
    if (octave < -1 || octave > 10 || start < 0 || end <= start) {
      throw new IllegalArgumentException("Invalid Note");
    }
    this.pitch = pitch;
    this.octave = octave;
    this.start = start;
    this.end = end;
    this.instrument = instrument;
    this.volume = volume;
    this.pitchNum = octave * 12 + pitch.order;

  }

  /**
   * @param pitch      the integer pitch of the note
   * @param start      the start time of the note
   * @param end        the end of the note
   * @param instrument the instrument
   * @param volume     volume number
   */
  protected ANote(int pitch, int start, int end, int instrument, int volume) {
    if (octave < 0 || octave > 10 || start < 0 || end <= start) {
      throw new IllegalArgumentException("Invalid Note");
    }

    int octave = pitch / 12;
    MusicEditorModel.Pitch pitch2 = MusicEditorModel.Pitch.values()[pitch % 12];

    this.octave = octave;
    this.pitch = pitch2;
    this.start = start;
    this.end = end;
    this.instrument = instrument;
    this.volume = volume;
    this.pitchNum = pitch;

  }

  /**
   * Sets the note to the changed characteristics
   *
   * @param newPitch  the new pitch
   * @param newOctave the new Octave
   * @param newStart  the new start time
   * @param newEnd    the new end
   */
  protected abstract void setNote(MusicEditorModel.Pitch newPitch, int newOctave, int newStart,
                                  int newEnd,
                                  int newInstrument, int newVolume);

  /**
   *
   * @param other
   * @return
   */
  public abstract boolean equals(Note other);

  /**
   * Gets the volume
   *
   * @return volume number
   */
  public abstract int getVolume();


  /**
   * gets the pitch
   *
   * @return the Pitch
   */
  public abstract MusicEditorModel.Pitch getPitch();

  /**
   * gets the pitch
   *
   * @return the Pitch
   */
  public abstract int getPitchNum();

  /**
   * gets the start
   *
   * @return the start
   */
  public abstract int getStart();

  /**
   * gets the octave
   *
   * @return the Octave
   */
  public abstract int getOctave();


  /**
   * gets the end
   *
   * @return the end
   */
  public abstract int getEnd();

  /**
   * Gets the instrument
   *
   * @return the instrument number
   */
  public abstract int getInstrument();

  /**
   * Makes a new note with these characteristics
   */
  public static ANote makeNote(int pitchNum, int start, int end, int instrument, int volume) {
    return new Note(pitchNum, start, end, instrument, volume);
  }


  /**
   * Checks if the note's duration plays in this given range of time
   *
   * @param time the time we are checking
   * @return whether it is in the range
   * @throws IllegalArgumentException if the time is negative
   */
  public boolean inRange(int time) {
    if (time < 0) {
      throw new IllegalArgumentException("time is negative");
    }
    return time > this.start && time < this.end;

  }


}
