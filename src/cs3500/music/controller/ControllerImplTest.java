package cs3500.music.controller;

import org.junit.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import cs3500.music.model.ANote;
import cs3500.music.model.MusicEditorModel;
import cs3500.music.model.MusicEditorModelImpl;
import cs3500.music.model.Note;
import cs3500.music.view.CompoundView;
import cs3500.music.view.GuiViewFrame;
import cs3500.music.view.ViewFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by alexgomez on 11/25/15.
 */
public class ControllerImplTest {

  @Test
  public void testControllerImpl1() {
    MusicEditorModel m = new MusicEditorModelImpl.Builder().addLines(
            new ArrayList<ANote>()).setTempo(10).build();
    CompoundView comp = new CompoundView(m);
    ControllerImpl c = new ControllerImpl(comp, m);
    GuiViewFrame gui = (GuiViewFrame) ViewFactory.make("gui", m);

    Button press = new Button("press");
    c.kbh.keyPressed(new KeyEvent(press, 3, 3, 3, KeyEvent.VK_SPACE, KeyEvent.CHAR_UNDEFINED));
    assertTrue(c.paused);
    c.kbh.keyPressed(new KeyEvent(press, 3, 3, 3, KeyEvent.VK_R, KeyEvent.CHAR_UNDEFINED));
    assertTrue(c.removing);
    c.kbh.keyReleased(new KeyEvent(press, 3, 3, 3, KeyEvent.VK_R, KeyEvent.CHAR_UNDEFINED));
    assertFalse(c.removing);
    c.kbh.keyPressed(new KeyEvent(press, 3, 3, 3, KeyEvent.VK_HOME, KeyEvent.CHAR_UNDEFINED));

    gui.home();
    assertEquals(gui.getScrollPanel().getHorizontalScrollBar().getValue(),
            comp.gui.getScrollPanel().getHorizontalScrollBar().getValue());

    c.kbh.keyPressed(new KeyEvent(press, 3, 3, 3, KeyEvent.VK_END, KeyEvent.CHAR_UNDEFINED));
    gui.end();
    assertEquals(gui.getScrollPanel().getHorizontalScrollBar().getValue(),
            comp.gui.getScrollPanel().getHorizontalScrollBar().getValue());

    c.kbh.keyPressed(new KeyEvent(press, 3, 3, 3, KeyEvent.VK_LEFT, KeyEvent.CHAR_UNDEFINED));
    gui.scrollLeft();
    assertEquals(gui.getScrollPanel().getHorizontalScrollBar().getValue(),
            comp.gui.getScrollPanel().getHorizontalScrollBar().getValue());

    c.kbh.keyPressed(new KeyEvent(press, 3, 3, 3, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED));
    gui.scrollRight();
    assertEquals(gui.getScrollPanel().getHorizontalScrollBar().getValue(),
            comp.gui.getScrollPanel().getHorizontalScrollBar().getValue());

    c.kbh.keyPressed(new KeyEvent(press, 3, 3, 3, KeyEvent.VK_DOWN, KeyEvent.CHAR_UNDEFINED));
    gui.scrollDown();
    assertEquals(gui.getScrollPanel().getVerticalScrollBar().getValue(),
            comp.gui.getScrollPanel().getVerticalScrollBar().getValue());

    c.kbh.keyPressed(new KeyEvent(press, 3, 3, 3, KeyEvent.VK_UP, KeyEvent.CHAR_UNDEFINED));
    gui.scrollUp();
    assertEquals(gui.getScrollPanel().getVerticalScrollBar().getValue(),
            comp.gui.getScrollPanel().getVerticalScrollBar().getValue());



  }

  @Test
  public void testControllerImpl2() {
    ANote note = Note.makeNote(10, 0, 2, 1, 64);
    ArrayList<ANote> list = new ArrayList<>();
    list.add(note);
    MusicEditorModel m = new MusicEditorModelImpl.Builder().addLines(
            list).setTempo(10).build();
    CompoundView comp = new CompoundView(m);
    ControllerImpl c = new ControllerImpl(comp, m);
    GuiViewFrame gui = (GuiViewFrame) ViewFactory.make("gui", m);

    Button press = new Button("press");

    c.kbh.keyPressed(new KeyEvent(press, 3, 3, 3, KeyEvent.VK_R, KeyEvent.CHAR_UNDEFINED));
    assertTrue(c.removing);
    m.remove(note);
//    assertTrue(comp.gui.getNoteAt(new Point(40, 20)).getPitchNum() == 10);
    m.addNote(note);

  }


}