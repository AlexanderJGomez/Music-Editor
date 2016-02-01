package cs3500.music.controller;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.function.Consumer;

/**
 * Created by alexgomez on 11/23/15.
 */
public class MouseHandler implements MouseListener {
  Consumer<Point> button1click;
  Consumer<Point> button1press;
  Consumer<Point> button1release;
  /**
   * Adds a mouse event
   * @param value what event is it
   * @param consumer the consumer you would like to run
   */
  public void addMouseEvent(int value, Consumer<Point> consumer) {

    if (value == MouseEvent.BUTTON1 + MouseEvent.MOUSE_CLICKED) {
      button1click = consumer;
    }
    else if(value == MouseEvent.BUTTON1 + MouseEvent.MOUSE_PRESSED) {
      button1press = consumer;
    }
    else if(value == MouseEvent.BUTTON1 + MouseEvent.MOUSE_RELEASED) {
      button1release = consumer;
    }
  }

  /**
   * Invoked when the mouse button has been clicked (pressed and released) on a component.
   */
  @Override
  public void mouseClicked(MouseEvent e) {

    if (e.getButton() == MouseEvent.BUTTON1) {
      button1click.accept(new Point(e.getX(), e.getY()));
    }

  }

  /**
   * Invoked when a mouse button has been pressed on a component.
   */
  @Override
  public void mousePressed(MouseEvent e) {
    if(e.getButton() == MouseEvent.BUTTON1) {
      button1press.accept(new Point(e.getX(), e.getY()));
    }

  }

  /**
   * Invoked when a mouse button has been released on a component.
   */
  @Override
  public void mouseReleased(MouseEvent e) {
    if(e.getButton() == MouseEvent.BUTTON1) {
      button1release.accept(new Point(e.getX(), e.getY()));
    }
  }

  /**
   * Invoked when the mouse enters a component.
   */
  @Override
  public void mouseEntered(MouseEvent e) {

  }

  /**
   * Invoked when the mouse exits a component.
   */
  @Override
  public void mouseExited(MouseEvent e) {

  }

}
