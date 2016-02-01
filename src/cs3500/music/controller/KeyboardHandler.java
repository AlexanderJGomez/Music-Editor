package cs3500.music.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

/**
 * Created by alexgomez on 11/21/15.
 */
public class KeyboardHandler implements KeyListener {
  HashMap<Integer, Runnable> typed = new HashMap<>();
  HashMap<Integer, Runnable> pressed = new HashMap<>();
  HashMap<Integer, Runnable> released = new HashMap<>();

  /**
   * Invoked when a key has been typed. See the class description for {@link KeyEvent} for a
   * definition of a key typed event.
   */
  @Override
  public void keyTyped(KeyEvent e) {
    if(typed.containsKey(e.getKeyCode())) {
      typed.get(e.getKeyCode()).run();
    }
  }

  /**
   * Invoked when a key has been pressed. See the class description for {@link KeyEvent} for a
   * definition of a key pressed event.
   */
  @Override
  public void keyPressed(KeyEvent e) {
    if(pressed.containsKey(e.getKeyCode())) {
      pressed.get(e.getKeyCode()).run();
    }
  }

  /**
   * Invoked when a key has been released. See the class description for {@link KeyEvent} for a
   * definition of a key released event.
   */
  @Override
  public void keyReleased(KeyEvent e) {
    if(released.containsKey(e.getKeyCode())) {
      released.get(e.getKeyCode()).run();
    }
  }

  /**
   * Adds and event to the kbh
   * @param code the code
   * @param r the runnable
   * @param keyType the type of key
   */
  public void addEvent(int code, Runnable r, int keyType) {
    if(keyType == KeyEvent.KEY_TYPED) {
      typed.put(code, r);
    }
    if(keyType == KeyEvent.KEY_PRESSED) {
      pressed.put(code, r);
    }
    if(keyType == KeyEvent.KEY_RELEASED) {
      released.put(code, r);
    }

  }


}
