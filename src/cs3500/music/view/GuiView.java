package cs3500.music.view;

import java.awt.*;

import cs3500.music.controller.KeyboardHandler;
import cs3500.music.model.ANote;

/**
 * Created by alexgomez on 11/21/15.
 */
public interface GuiView extends View {
  /**
   * Pauses the gui
   */
  void pause();

  /**
   * Scrolls all the way to the beginning
   */
  void home();

  /**
   * Scrolls all the way to the end
   */
  void end();

  /**
   * Scrolls to the Left
   */
  void scrollLeft();

  /**
   * Scrolls to the right
   */
  void scrollRight();

  /**
   * Scrolls down
   */
  void scrollDown();

  /**
   * scroll up
   */
  void scrollUp();

  /**
   * Gets the note at the point
   *
   * @param point the point chosen
   * @return the ANote at that point
   */
  ANote getNoteAt(Point point);

  /**
   * Sets the key listener
   * @param k what ur setting it to
   */
   void setKeyListener(KeyboardHandler k);
}
