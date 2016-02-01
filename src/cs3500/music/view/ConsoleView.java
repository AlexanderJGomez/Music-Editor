package cs3500.music.view;

import java.io.IOException;

import cs3500.music.model.ANote;
import cs3500.music.model.MusicEditorModel;

/**
 * Created by alexgomez on 11/10/15.
 */
public class ConsoleView implements View {

  public Appendable output;
  MusicEditorModel model;

  /**
   * @param model the model you're going to work with
   */
  protected ConsoleView(MusicEditorModel model, Appendable output) {
    this.model = model;
    this.output = output;
  }
  /**
   * Prints the top line for the console
   *
   * @param minOctave the maximum octave
   * @param maxOctave the minimum octave
   * @param minNote   the lowest note
   * @param maxNote   the hightest note
   * @param output    the appendable output
   * @throws IllegalArgumentException if given any invalid numbers
   */
  private void printTop(int minOctave, int maxOctave, int minNote, int maxNote, Appendable output)
          throws IOException {
    if (minOctave <= 0 || (maxOctave <= 0 || maxOctave > 10) || (minNote > 11 || minNote < 0) ||
            (maxNote < 0 || maxNote > 11)) {
      throw new IllegalArgumentException("Invalid arg");
    }

    output.append("    ");
    for (int oct = minOctave; oct <= maxOctave; oct++) {
      if (oct == minOctave && oct == maxOctave) {
        for (int pitches = minNote; pitches <= maxNote; pitches++) {
          output.append(this.padString(MusicEditorModel.Pitch.values()[pitches].value + oct));
        }
      } else if (oct == minOctave) {
        for (int pitches = minNote; pitches < 12; pitches++) {
          output.append(this.padString(MusicEditorModel.Pitch.values()[pitches].value + oct));
        }
        output.append("  ");
      } else if (oct == maxOctave) {
        for (int pitches = 0; pitches <= maxNote; pitches++) {
          output.append(this.padString(MusicEditorModel.Pitch.values()[pitches].value + oct));
        }
      } else {
        for (MusicEditorModel.Pitch pitch : MusicEditorModel.Pitch.values()) {
          output.append(this.padString(pitch.value + oct));
        }
        output.append("  ");
      }

    }
    output.append("\n");
  }


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
   * Draws the state of the game.
   */
  public void create() throws IOException {
    //find the maximum and minimum octave in the piece and when piece ends
    int maxOctave = model.getMinOrMaxOctave("max");
    int minOctave = model.getMinOrMaxOctave("min");
    int endTime = model.getEnd();
    int minNote = model.getLowOrHighNote("low", minOctave);
    int maxNote = model.getLowOrHighNote("high", maxOctave);


    printTop(minOctave, maxOctave, minNote, maxNote, output);


    //pads the time to 4 spaces
    for (int time = 0; time <= endTime; time++) {
      String timeString = Integer.toString(time);
      //outputs the time
      output.append(padString(timeString));
      //goes thru each octave included in the piece
      for (int octaves = minOctave; octaves <= maxOctave; octaves++) {

        boolean found = false;
        if (octaves == minOctave && octaves == maxOctave) {
          this.model.printNotes(octaves, minNote, maxNote, time, output);

        } else if (octaves == minOctave) {
          this.model.printNotes(octaves, minNote, 11, time, output);
        }

        ///if at last octave
        else if (octaves == maxOctave) {
          this.model.printNotes(octaves, 0, maxNote, time, output);
        }

        ///if in the middle
        else {
          this.model.printNotes(octaves, 0, 11, time, output);
        }

        output.append("  ");
      }
      output.append("  \n");
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
}
