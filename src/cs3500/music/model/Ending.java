package cs3500.music.model;

/**
 * Created by alexgomez on 12/13/15.
 */
public class Ending {
  //has the ending been played yet
  private boolean done = false;
  //the start beat of the ending
  private int end;
  //start of ending
  private int endingStart;

  /**
   * @param end the end of the ending
   */
  public Ending(int end, int endingStart) {
    this.end = end;
    this.endingStart = endingStart;
  }


  /**
   * get Start of this ending
   * @return get this ending's start
   */
  public int getEnd() {
    return end;
  }

  /**
   * Get where all alt endings begin
   * @return the alt endings start
   */
  public int getEndingStart() {
    return endingStart;
  }

  /**
   * Finished ending
   */
  public void finish() {
    done = true;
  }

  /**
   * Is it done yet
   * @return
   */
  public boolean isDone() {
    return done;
  }
}
