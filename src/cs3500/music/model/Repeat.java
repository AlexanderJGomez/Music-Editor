package cs3500.music.model;

/**
 * Created by alexgomez on 12/13/15.
 */
public class Repeat {

  private int start;
  private int end;
  private boolean done = false;
  private int numRepeats;

  Repeat(int start, int end, int numRepeats) {
    this.start = start;
    this.end = end;
    this.numRepeats = numRepeats;
  }

  public int getEnd() {
    return end;
  }

  public int getStart() {
    return start;
  }

  public boolean isDone() {
    return done;
  }

  public void decrementRepeat() {
    numRepeats -=1;
    if(numRepeats < 1) {
      done = true;
    }
  }

}
