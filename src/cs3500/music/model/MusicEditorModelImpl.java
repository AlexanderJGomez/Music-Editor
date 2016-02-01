package cs3500.music.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import cs3500.music.util.CompositionBuilder;

/**
 * Created by alexgomez on 11/1/15.
 */
public class MusicEditorModelImpl implements MusicEditorModel {

  /*
  What has been changed from the original model is that the bag of notes has been changed to
  a hashmap in order to make getting notes more efficient.
   */


  //the lines of the piece
  private HashMap<Integer, ArrayList<ANote>> lines = new HashMap<>();
  //the current beat you are on
  private int currBeat = 0;
  //the tempo of the song
  private int tempo;
  //the repeats
  private ArrayList<Repeat> repeats;
  //the alternate endings
  private ArrayList<ArrayList<Ending>> endings = new ArrayList<>();

  /**
   * The constructor for a ModelEditorModelImpl
   *
   * @param coll all the notes that will be played
   */
  protected MusicEditorModelImpl(Collection<ANote> coll, int tempo, Collection<Repeat> repeats,
                                 ArrayList<ArrayList<Ending>> endings) {
    //find the maximum and minimum octave in the piece and when piece ends
    int minTime = 999999999;
    int endTime = 0;
//get the minimum and maximum octave
    for (ANote n : coll) {
      int currNoteStart = n.getStart();
      int currNoteEnd = n.getEnd() - 1;
      if (currNoteStart < minTime) {
        minTime = currNoteStart;
      }
      if (currNoteEnd > endTime) {
        endTime = currNoteEnd;
      }
    }

    if (coll.isEmpty()) {

    } else {
      for (int x = minTime; x <= endTime; x++) {
        ArrayList<ANote> beat = new ArrayList<>();

        for (ANote anote : coll) {
          if (anote.getStart() == x || anote.inRange(x)) {
            beat.add(anote);
          }
        }

        this.lines.put(x, beat);
      }
    }
    this.tempo = tempo;
    ArrayList<Repeat> temp1 = new ArrayList<>();
    for (Repeat r : repeats) {
      temp1.add(r);
    }


    for (Collection<Ending> ed : endings) {
      ArrayList<Ending> temp2 = new ArrayList<>();
      for (Ending e : ed) {
        temp2.add(e);
      }
      temp2.sort((o1, o2) -> o1.getEnd() - o2.getEnd());
      this.endings.add(temp2);
    }

    this.repeats = temp1;
  }


  @Override
  public ArrayList<ArrayList<Ending>> getEndings() {
    return endings;
  }

  /**
   * Adds a repeat
   */
  @Override
  public void addRepeat(int start, int end, int numRepeats) {
    this.repeats.add(new Repeat(start, end, numRepeats));
  }

  /**
   * adds an alternate ending
   *
   * @param start        the start of the alternate ending
   * @param endingsStart the start of all alternate endings
   * @param group        which group of endings to add it to
   */
  @Override
  public void addEnding(int start, int endingsStart, int group) {

    if (start <= endingsStart || (!endings.get(group).isEmpty() && endingsStart !=
            this.endings.get(group).get(0).getEndingStart())) {
      throw new IllegalArgumentException("illegal arg");
    }

    if (this.endings.isEmpty() || group == endings.size()) {
      ArrayList<Ending> ends = new ArrayList<>();
      ends.add(new Ending(start, endingsStart));
      this.endings.add(ends);
    } else {
      this.endings.get(group).add(new Ending(start, endingsStart));
    }
    this.endings.get(group).sort((o1, o2) -> o1.getEnd() - o2.getEnd());
  }

  /**
   * Adds a group of new alternate endings
   */
  @Override
  public void addEndingsGroup(int endingsStart, int start, int end) {
    ArrayList<Ending> enders = new ArrayList<>();
    enders.add(new Ending(start, endingsStart));
    enders.add(new Ending(end, endingsStart));
    endings.add(enders);
    endings.sort((o1, o2) -> o1.get(0).getEndingStart() - o2.get(0).getEndingStart());
  }

  /**
   * Gets the Next beat
   *
   * @param beat the beat you are on now
   * @return the next beat
   */
  @Override
  public int getNextBeat(int beat) {
    int actual = beat;
    for (Repeat r : repeats) {
      if (!r.isDone()) {
        if (r.getEnd() == beat) {
          actual = r.getStart();
          r.decrementRepeat();
          currBeat = r.getStart();
          break;
        }
      }
    }


    for (int i = 0; i < endings.size(); i++) {
      boolean next = false;
      for (int x = 0; x < endings.get(i).size(); x++) {
        Ending e = endings.get(i).get(x);
        if (e.getEnd() == actual && !e.isDone() && x == 0) {
          e.finish();
          break;
        } else if (e.getEnd() == actual && !e.isDone() && x != endings.get(i).size() - 1) {
          currBeat = e.getEndingStart();
          break;
        } else if (e.getEnd() == actual && e.isDone()) {
          next = true;
        } else if (next && !e.isDone() && e.getEnd() > actual) {
          actual = e.getEnd();
          currBeat = e.getEnd();
          e.finish();
          break;
        }
      }
    }

    return actual;
  }

  /**
   * @return the repeats in the piece
   */
  public Collection<Repeat> getRepeats() {
    return repeats;
  }

  /**
   * Gives the current beat you are on
   *
   * @return the beat number
   */
  public int getBeat() {
    return this.currBeat;
  }

  /**
   * Gets the tempo
   *
   * @return the tempo
   */
  public int getTempo() {
    return this.tempo;
  }


  public static final class Builder implements CompositionBuilder<MusicEditorModel> {

    //the lines of the piece
    private ArrayList<ANote> lines2 = new ArrayList<>();
    //the tempo of the song
    private int tempo = 0;
    //list of repeats
    private ArrayList<Repeat> repeats = new ArrayList<>();
    //list of endings
    private ArrayList<ArrayList<Ending>> endings = new ArrayList<>();


    /**
     * Constructs an actual composition, given the notes that have been added
     *
     * @return The new composition
     */
    @Override
    public MusicEditorModel build() {
      return new MusicEditorModelImpl(this.lines2, this.tempo, this.repeats, this.endings);
    }

    /**
     * Sets the tempo of the piece
     *
     * @param tempo The speed, in microseconds per beat
     * @return This builder
     */
    @Override
    public CompositionBuilder<MusicEditorModel> setTempo(int tempo) {
      this.tempo = tempo;
      return this;
    }

    /**
     * Adds a new note to the piece
     *
     * @param start      The start time of the note, in beats
     * @param end        The end time of the note, in beats
     * @param instrument The instrument number (to be interpreted by MIDI)
     * @param pitch      The pitch (in the range [0, 127], where 60 represents C4, the middle-C on a
     *                   piano)
     * @param volume     The volume (in the range [0, 127])
     */
    @Override
    public CompositionBuilder<MusicEditorModel> addNote(int start, int end, int instrument,
                                                        int pitch, int volume) {
      this.lines2.add(new Note(pitch, start, end, instrument, volume));
      return this;
    }

    /**
     * Adds a new Repeat to the piece
     *
     * @param start the start time of the repeat
     * @param end   the end time of the repeat
     */
    @Override
    public CompositionBuilder<MusicEditorModel> addRepeat(int start, int end, int numRepeats) {
      this.repeats.add(new Repeat(start, end, numRepeats));
      return this;
    }

    /**
     * Adds a new Ending
     */
    @Override
    public CompositionBuilder<MusicEditorModel> addEnding(Collection<Ending> end) {
      ArrayList<Ending> a = new ArrayList<>();
      a.addAll(end);
      this.endings.add(a);
      return this;
    }

    public CompositionBuilder<MusicEditorModel> addLines(Collection<ANote> collect) {
      for (ANote n : collect) {
        this.lines2.add(n);
      }
      return this;
    }
  }


  /**
   * Adds a unique note to the piece
   *
   * @param newNote the newNote you would like to add exists
   * @return true if it adds, return false if it doesn't
   * @throws IllegalArgumentException if it is an illegal arg
   */
  @Override
  public boolean addNote(ANote newNote) {
    if (newNote.getStart() < 0 || newNote.getEnd() <= newNote.getStart() || newNote.getPitchNum()
            < 0 || newNote.getPitchNum() > 128 || newNote.getInstrument() < 0
            || newNote.getVolume() < 0) {
      throw new IllegalArgumentException("Invalid Note");
    }
    for (int time = newNote.getStart(); time < newNote.getEnd(); time++) {
      if (!this.lines.keySet().contains(time)) {
        ArrayList<ANote> newVal = new ArrayList<>();
        newVal.add(newNote);
        this.lines.put(time, newVal);
      } else if (this.lines.get(time).contains(newNote)) {
        return false;
      } else {
        ArrayList<ANote> newVal = this.lines.get(time);
        newVal.add(newNote);
        this.lines.put(time, newVal);
      }

    }

    return true;
  }


  /**
   * Edits the given note.
   *
   * @param editedNote the note you are editing
   * @param newPitch   the new pitch for the note
   * @param newOctave  the new octave for the note
   * @param newStart   the new start for the note
   * @param newEnd     the new duration for the note
   */
  @Override
  public void edit(ANote editedNote, Pitch newPitch, int newOctave, int newStart, int newEnd,
                   int newInstrument, int newVolume) {
    if (!this.lines.containsValue(editedNote)) {
      throw new IllegalArgumentException("Invalid note");
    }
    for (int i = newStart; i < newEnd; i++) {
      ArrayList<ANote> newVal = this.lines.get(i);
      newVal.remove(editedNote);
      newVal.add(new Note(newPitch, newOctave, newStart, newEnd, newInstrument, newVolume));
      this.lines.put(i, newVal);
    }

  }


  /**
   * removes the note from the piece
   *
   * @param n the note you will remove
   * @throws IllegalArgumentException this note cannot be found
   */
  public void remove(ANote n) {
    if (!this.notesPlaying(n.getStart()).contains(n)) {
      throw new IllegalArgumentException("No note found");
    }
    for (int i = n.getStart(); i < n.getEnd(); i++) {
      ArrayList<ANote> newVal = this.lines.get(i);
      newVal.remove(n);
      this.lines.put(i, newVal);
    }
  }


  /**
   * Appends the notes being playes in the given range, in the given octave, at the given time to
   * the appendable
   *
   * @param octave  the given octave
   * @param minNote the minimum note
   * @param maxNote the maximum note
   * @param time    the current time
   * @param output  the appendable output
   * @throws IllegalArgumentException if any of the paramaters are invalid
   */
  public void printNotes(int octave, int minNote, int maxNote, int time, Appendable output) throws
          IOException {
    if (octave < -1 || octave > 10 || minNote < 0 || maxNote > 11 || time < 0) {
      throw new IllegalArgumentException("Illegal argument");
    }
    if (!this.lines.keySet().contains(time)) {

    } else {
      for (int pitches = minNote; pitches <= maxNote; pitches++) {
        boolean foundStart = false;
        boolean foundDuration = false;
        for (ANote n : this.notesPlaying(time)) {
          if (n.getPitch() == Pitch.values()[pitches] && n.getOctave() == octave) {
            if (n.getStart() == time) {
              foundStart = true;
            } else if (n.inRange(time)) {
              foundDuration = true;
            }
          }
        }
        if (foundStart) {
          output.append(this.padString("X"));
        } else if (foundDuration) {

          output.append(this.padString("|"));
        } else {
          output.append("    ");
        }
      }
    }

  }

  /**
   * sets the current beat it is on
   */
  @Override
  public void setBeat(int beat) {
    this.currBeat = beat;
  }

  /**
   * Pads the string
   */
  String padString(String s) {
    switch (s.length()) {
      case 1:
        s += "   ";
        break;
      case 2:
        s += "  ";
        break;
      case 3:
        s += " ";
        break;
      default:
        break;
    }
    return s;
  }

  /**
   * Gets max or min oct
   *
   * @param maxOrMin is this a max or min
   * @return the max or min
   */
  public int getMinOrMaxOctave(String maxOrMin) {
    int maxOctave = 0;
    int minOctave = 99;
    switch (maxOrMin) {
      case "max":
        for (int n : this.lines.keySet()) {
          for (ANote anote : this.lines.get(n)) {
            int currNoteOctave = anote.getOctave();
            maxOctave = Math.max(maxOctave, currNoteOctave);
          }
        }
        return maxOctave;
      case "min":
        for (int n : this.lines.keySet()) {
          for (ANote anote : this.lines.get(n)) {
            int currNoteOctave = anote.getOctave();
            minOctave = Math.min(minOctave, currNoteOctave);
          }
        }
        return minOctave;
      default:
        return 0;
    }
  }

  /**
   * @param note          high or low note?
   * @param desiredOctave in which octave?
   */
  public int getLowOrHighNote(String note, int desiredOctave) {
    int minNote = 11;
    int maxNote = 0;
    switch (note) {
      case "high":
        for (int n : this.lines.keySet()) {
          for (ANote anote : this.lines.get(n)) {
            if (anote.getOctave() == desiredOctave) {
              maxNote = Math.max(anote.getPitch().order, maxNote);
            }
          }
        }
        return maxNote;
      case "low":
        for (int n : this.lines.keySet()) {
          for (ANote anote : this.lines.get(n)) {
            if (anote.getOctave() == desiredOctave) {
              minNote = Math.min(anote.getPitch().order, minNote);
            }
          }
        }
        return minNote;
      default:
        return 0;
    }

  }


  /**
   * Find the notes playing at this time
   *
   * @param time at what time are we looking for the notes
   * @return a collection of notes playing
   */
  @Override
  public Collection<ANote> notesPlaying(int time) {

    if (lines.keySet().contains(time)) {
      return this.lines.get(time);
    } else {
      return new ArrayList<>();
    }
  }


  /**
   * @return the last beat where a note is played
   */
  @Override
  public int getEnd() {
    int max = 0;
    for (int x : this.lines.keySet()) {
      max = Math.max(x, max);
    }

    return max;
  }


}

