package cs3500.music.controller;

import org.junit.Test;

import java.awt.*;
import java.awt.event.KeyEvent;

import static org.junit.Assert.assertEquals;

/**
 * Created by alexgomez on 11/25/15.
 */
public class KeyboardHandlerTest {

  @Test
  public void testKeyTyped() throws Exception {
    KeyboardHandler kbh = new KeyboardHandler();
    StringBuilder sb = new StringBuilder();
    Button type = new Button("type");
    kbh.addEvent(KeyEvent.VK_C, new Runnable() {
      @Override
      public void run() {
        sb.append("You typed C \n");
      }
    }, KeyEvent.KEY_TYPED);
    kbh.addEvent(KeyEvent.VK_D, new Runnable() {
      @Override
      public void run() {
        sb.append("You typed D \n");
      }
    }, KeyEvent.KEY_TYPED);
    kbh.keyTyped(new KeyEvent(type, 3, 3, 3, KeyEvent.VK_C, KeyEvent.CHAR_UNDEFINED));
    assertEquals(sb.toString(), "You typed C \n");
    kbh.keyTyped(new KeyEvent(type, 3, 3, 3, KeyEvent.VK_D, KeyEvent.CHAR_UNDEFINED));
    assertEquals(sb.toString(), "You typed C \nYou typed D \n");
  }

  @Test
  public void testKeyPressed() throws Exception {
    KeyboardHandler kbh = new KeyboardHandler();
    StringBuilder sb = new StringBuilder();
    Button press = new Button("press");
    kbh.addEvent(KeyEvent.VK_C, new Runnable() {
      @Override
      public void run() {
        sb.append("You pressed C \n");
      }
    }, KeyEvent.KEY_PRESSED);
    kbh.addEvent(KeyEvent.VK_D, new Runnable() {
      @Override
      public void run() {
        sb.append("You pressed D \n");
      }
    }, KeyEvent.KEY_PRESSED);
    kbh.keyPressed(new KeyEvent(press, 3, 3, 3, KeyEvent.VK_C, KeyEvent.CHAR_UNDEFINED));
    assertEquals(sb.toString(), "You pressed C \n");
    kbh.keyPressed(new KeyEvent(press, 3, 3, 3, KeyEvent.VK_D, KeyEvent.CHAR_UNDEFINED));
    assertEquals(sb.toString(), "You pressed C \nYou pressed D \n");
  }


  @Test
  public void testKeyReleased() throws Exception {
    KeyboardHandler kbh = new KeyboardHandler();
    StringBuilder sb = new StringBuilder();
    Button release = new Button("release");
    kbh.addEvent(KeyEvent.VK_C, new Runnable() {
      @Override
      public void run() {
        sb.append("You released C \n");
      }
    }, KeyEvent.KEY_RELEASED);
    kbh.addEvent(KeyEvent.VK_D, new Runnable() {
      @Override
      public void run() {
        sb.append("You released D \n");
      }
    }, KeyEvent.KEY_RELEASED);
    kbh.keyReleased(new KeyEvent(release, 3, 3, 3, KeyEvent.VK_C, KeyEvent.CHAR_UNDEFINED));
    assertEquals(sb.toString(), "You released C \n");
    kbh.keyReleased(new KeyEvent(release, 3, 3, 3, KeyEvent.VK_D, KeyEvent.CHAR_UNDEFINED));
    assertEquals(sb.toString(), "You released C \nYou released D \n");
  }


  @Test
  public void testAddEvent() throws Exception {
    KeyboardHandler kbh = new KeyboardHandler();
    StringBuilder sb = new StringBuilder();
    Button press = new Button("press");
    Button typed = new Button("type");
    kbh.addEvent(KeyEvent.VK_C, new Runnable() {
      @Override
      public void run() {
        sb.append("You pressed C \n");
      }
    }, KeyEvent.KEY_PRESSED);
    kbh.addEvent(KeyEvent.VK_D, new Runnable() {
      @Override
      public void run() {
        sb.append("You typed D \n");
      }
    }, KeyEvent.KEY_TYPED);
    kbh.keyPressed(new KeyEvent(press, 3, 3, 3, KeyEvent.VK_C, KeyEvent.CHAR_UNDEFINED));
    assertEquals(sb.toString(), "You pressed C \n");
    kbh.keyTyped(new KeyEvent(typed, 3, 3, 3, KeyEvent.VK_D, KeyEvent.CHAR_UNDEFINED));
    assertEquals(sb.toString(), "You pressed C \nYou typed D \n");
  }
}