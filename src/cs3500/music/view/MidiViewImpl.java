package cs3500.music.view;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;

import cs3500.music.model.ANote;
import cs3500.music.model.MusicEditorModel;

/**
 * A skeleton for MIDI playback
 */


public final class MidiViewImpl implements MidiView {
  private Synthesizer synth;
  private Receiver receiver;
  private MusicEditorModel model;
  public Appendable output;
  private MockReceiver mockReceiver;
  private MockSynth mockSynth;
  private Timer timer = new Timer();
  private boolean paused = false;
  //private HashMap<Integer, ArrayList<ANote>> notesPlayed = new HashMap<>();
  //private ArrayList<ANote> notesPlayed2 = new ArrayList<>();


  protected MidiViewImpl(MusicEditorModel model) {
    try {
      this.synth = MidiSystem.getSynthesizer();
      this.receiver = synth.getReceiver();
      this.synth.open();
      this.model = model;
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
  }

  public MidiViewImpl(MusicEditorModel model, Appendable output) {
    this.model = model;
    this.output = output;
    this.mockReceiver = new MockReceiver();
    this.mockSynth = new MockSynth();
  }

  /**
   * Relevant classes and methods from the javax.sound.midi library: <ul> <li>{@link
   * MidiSystem#getSynthesizer()}</li> <li>{@link Synthesizer} <ul> <li>{@link
   * Synthesizer#open()}</li> <li>{@link Synthesizer#getReceiver()}</li> <li>{@link
   * Synthesizer#getChannels()}</li> </ul> </li> <li>{@link Receiver} <ul> <li>{@link
   * Receiver#send(MidiMessage, long)}</li> <li>{@link Receiver#close()}</li> </ul> </li> <li>{@link
   * MidiMessage}</li> <li>{@link ShortMessage}</li> <li>{@link MidiChannel} <ul> <li>{@link
   * MidiChannel#getProgram()}</li> <li>{@link MidiChannel#programChange(int)}</li> </ul> </li>
   * </ul>
   *
   * @see <a href="https://en.wikipedia.org/wiki/General_MIDI"> </a>
   */

  public void create() throws InvalidMidiDataException, InterruptedException {
    timer.schedule(new PlayNotes(), 0, model.getTempo() / 1000);
    //this.receiver.close(); // Only call this once you're done playing *all* notes
  }

  /**
   * Adds  an ANote
   *
   * @param anote adds this note
   */
  @Override
  public void addNote(ANote anote) {
    model.addNote(anote);
  }


  /**
   * A timer task class to playNotes at a given time
   */
  class PlayNotes extends TimerTask {

    /**
     * The action to be performed by this timer task.
     */
    @Override
    public void run() {
      if (!paused) {
        play(model.getBeat());
        model.setBeat(model.getBeat() + 1);
      }
    }
  }

  /**
   * Plays the Anotes at this time
   *
   * @param time the time you want to be played
   */
  public void play(int time) {
    try {
      if (time <= model.getEnd()) {
        playNotes(time);
      }
    } catch (Exception e) {

    }
  }


  /**
   * Plays the note at a given time
   *
   * @param currTime the time of notes you'd like to be played
   */
  void playNotes(int currTime) throws InvalidMidiDataException {

    int actual = model.getNextBeat(currTime);

    MidiMessage start;//new ShortMessage(ShortMessage.NOTE_ON, 0, 60, 64);
    MidiMessage stop;
    // new ShortMessage(ShortMessage.NOTE_OFF, 0, 60, 64);
    for (ANote n : model.notesPlaying(actual)) {
      //starts the notes that should start
      if (n.getStart() == currTime) {
        MidiChannel[] x = synth.getChannels();
        x[(n.getInstrument() - 1) % 16].programChange(n.getInstrument() - 1);
        start = new ShortMessage(ShortMessage.NOTE_ON, (n.getInstrument() - 1) % 16,
                n.getPitchNum(), n.getVolume());

        stop = new ShortMessage(ShortMessage.NOTE_OFF, (n.getInstrument() - 1) % 16,
                n.getPitchNum(), n.getVolume());

        this.receiver.send(start, this.synth.getMicrosecondPosition());
        this.receiver.send(stop, this.synth.getMicrosecondPosition()
                + (n.getEnd() - n.getStart()) * model.getTempo());
      }
    }

  }


  /**
   * Used to test midi output
   */
  public void mockOutput() throws InvalidMidiDataException, IOException {
    MidiMessage start = new ShortMessage();//new ShortMessage(ShortMessage.NOTE_ON, 0, 60, 64);
    MidiMessage stop = new ShortMessage();//new ShortMessage(ShortMessage.NOTE_OFF, 0, 60, 64);


    for (int i = 0; i < model.getEnd(); i++) {
      for (ANote n : model.notesPlaying(i)) {
        start = new ShortMessage(ShortMessage.NOTE_ON, (n.getInstrument() - 1) % 16,
                n.getPitchNum(), n.getVolume());

        stop = new ShortMessage(ShortMessage.NOTE_OFF, (n.getInstrument() - 1) % 16,
                n.getPitchNum(), n.getVolume());

        this.mockReceiver.send(start,
                this.mockSynth.getMicrosecondPosition() + n.getStart() * model.getTempo());
        this.mockReceiver.send(stop,
                this.mockSynth.getMicrosecondPosition() + n.getEnd() * model.getTempo());
      }


    }

    this.output.append(mockReceiver.sb.toString());
  }


  /**
   * Pauses the view.
   */
  public void pause() throws InvalidMidiDataException {
    if (!paused) {
      //MidiMessage stop;
      /*
      if (!notesPlayed.isEmpty()) {
        for (int y : notesPlayed.keySet()) {
          if (y < time) {
          } else {
            for (ANote n : notesPlayed.get(y)) {
              MidiChannel[] x = synth.getChannels();
              x[(n.getInstrument() - 1) % 16].programChange(n.getInstrument() - 1);

              stop = new ShortMessage(ShortMessage.NOTE_OFF, (n.getInstrument() - 1) % 16,
                      n.getPitchNum(), n.getVolume());

              this.receiver.send(stop, -1);
            }
          }
        }
      }
      */
      paused = !paused;

    } else {
      paused = !paused;
      if (model.getBeat() < model.getEnd()) {
        for (ANote n : model.notesPlaying(model.getBeat())) {
          MidiMessage start;
          if (n.getStart() != model.getBeat()) {
            MidiChannel[] x = synth.getChannels();
            x[(n.getInstrument() - 1) % 16].programChange(n.getInstrument() - 1);
            start = new ShortMessage(ShortMessage.NOTE_ON, (n.getInstrument() - 1) % 16,
                    n.getPitchNum(), n.getVolume());


            this.receiver.send(start,
                    -1);
          }
        }
      }
    }
  }


}
