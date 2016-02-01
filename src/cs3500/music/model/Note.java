package cs3500.music.model;

/**
 * Created by alexgomez on 11/1/15.
 */
public final class Note extends ANote {
  /**
   * INVARIANT: octave >=1 and <=10
   * INVARIANT: start >= 0
   * INVARIANT: duration > 0
   */

  /**
   * @param pitch      the pitch of the note
   * @param octave     the octave of the note
   * @param start      the start time of the note
   * @param end        the duration of the note
   * @param volume     the volume of the note
   * @param instrument the instrument being played
   */

  protected Note(MusicEditorModel.Pitch pitch, int octave, int start, int end, int
          instrument, int volume) {
    super(pitch, octave, start, end, instrument, volume);
  }

  /**
   * @param pitch      the integer pitch of the note
   * @param start      the start of the note
   * @param end        the end of the note
   * @param instrument the instr being played
   * @param volume     the volume of the note
   */
  public Note(int pitch, int start, int end, int instrument, int volume) {
    super(pitch, start, end, instrument, volume);
  }


  /**
   * Sets the note to the changed characteristics
   *
   * @param newPitch      the new pitch
   * @param newOctave     the new Octave
   * @param newStart      the new start time
   * @param newEnd        the new end
   * @param newInstrument the new instrument
   * @param newVolume     the new volume
   */
  protected void setNote(MusicEditorModel.Pitch newPitch, int newOctave, int newStart, int newEnd,
                         int newInstrument, int newVolume) {
    if (newOctave < 0 || newOctave > 10 || newStart < 0 || newEnd <= newStart ||
            newInstrument < 1 || newInstrument > 128 || newVolume < 0) {
      throw new IllegalArgumentException("Invalid parameters");
    }
    this.pitch = newPitch;
    this.octave = newOctave;
    this.start = newStart;
    this.end = newEnd;
    this.volume = newVolume;
    this.instrument = newInstrument;
  }

  /**
   * Are these 2 notes the same?
   *
   * @return does this equal the other note
   */
  public boolean equals(Note other) {
    if (this.pitch == other.getPitch() && this.octave == other.getOctave()
            && this.start == other.getStart() && this.end == other.getEnd() && this.instrument ==
            other.getInstrument() && this.volume == other.getVolume()) {
      return true;
    }
    return false;
  }


  /**
   * gets the pitch
   *
   * @return the Pitch
   */
  public MusicEditorModel.Pitch getPitch() {
    return this.pitch;
  }

  /**
   * gets the pitch number
   *
   * @return the Pitch number
   */
  @Override
  public int getPitchNum() {
    return this.pitchNum;
  }

  /**
   * gets the start
   *
   * @return the start
   */
  public int getStart() {
    return this.start;
  }

  /**
   * gets the octave
   *
   * @return the Octave
   */
  public int getOctave() {
    return this.octave;
  }


  /**
   * gets the end
   *
   * @return the end
   */
  public int getEnd() {
    return this.end;
  }


  /**
   * gets the volume
   *
   * @return the volume number
   */
  public int getVolume() {
    return this.volume;
  }


  /**
   * Get the the instrument
   *
   * @return insturment number
   */
  public int getInstrument() {
    return this.instrument;
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
    if (time > this.start && time < this.end) {
      return true;
    }
    return false;
  }


}
