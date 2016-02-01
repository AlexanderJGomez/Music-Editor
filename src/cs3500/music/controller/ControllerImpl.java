package cs3500.music.controller;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Consumer;

import javax.sound.midi.InvalidMidiDataException;
import javax.swing.*;

import cs3500.music.model.ANote;
import cs3500.music.model.Ending;
import cs3500.music.model.MusicEditorModel;
import cs3500.music.model.Note;
import cs3500.music.model.Repeat;
import cs3500.music.view.CompoundView;

/**
 * Created by alexgomez on 11/21/15.
 */
public class ControllerImpl implements Controller {
  protected KeyboardHandler kbh = new KeyboardHandler();
  protected MouseHandler mhandler = new MouseHandler();
  private CompoundView view;
  protected boolean paused = false;
  private boolean ended = false;
  protected boolean removing = false;
  protected boolean moving = false;
  private Timer timer = new Timer();
  private MusicEditorModel model;
  protected Point pt = new Point(0, 0);
  protected ANote noteMoving;

  /**
   * @param view  the view you want to use
   * @param model the Model you want to use
   */
  public ControllerImpl(CompoundView view, MusicEditorModel model) {
    this.view = view;
    this.model = model;


    kbh.addEvent(KeyEvent.VK_SPACE, new Runnable() {
      @Override
      public void run() {
        paused = !paused;
        view.pause();
      }
    }, KeyEvent.KEY_PRESSED);


    kbh.addEvent(KeyEvent.VK_A, new Runnable() {
      @Override
      public void run() {
        if (!paused) {
          paused = !paused;
          String pn = JOptionPane.showInputDialog("Enter Pitch Number 0-127");
          String st = JOptionPane.showInputDialog("Enter Start time above 0");
          String et = JOptionPane.showInputDialog("Enter end time greater than start time");
          String in = JOptionPane.showInputDialog("Enter Instrument Number > 0");
          String vol = JOptionPane.showInputDialog("Enter volume > 0");
          try {
            int pitchNum = Integer.parseInt(pn);
            int startTime = Integer.parseInt(st);
            int endTime = Integer.parseInt(et);
            int instrumentNum = Integer.parseInt(in);
            int volumeNum = Integer.parseInt(vol);
            if (endTime <= startTime || instrumentNum <= 0 || volumeNum < 0 || startTime < 0) {
              throw new NumberFormatException();
            }
            model.addNote(ANote.makeNote(pitchNum, startTime, endTime, instrumentNum, volumeNum));

          } catch (NumberFormatException e) {

          }

          paused = !paused;
        } else {
          String pn = JOptionPane.showInputDialog("Enter Pitch Number");
          String st = JOptionPane.showInputDialog("Enter Start time");
          String et = JOptionPane.showInputDialog("Enter end time");
          String in = JOptionPane.showInputDialog("Enter Instrument Number");
          String vol = JOptionPane.showInputDialog("Enter volume");

          try {
            int pitchNum = Integer.parseInt(pn);
            int startTime = Integer.parseInt(st);
            int endTime = Integer.parseInt(et);
            int instrumentNum = Integer.parseInt(in);
            int volumeNum = Integer.parseInt(vol);
            if (endTime <= startTime || instrumentNum <= 0 || volumeNum < 0 || startTime < 0) {
              throw new NumberFormatException();
            }
            model.addNote(ANote.makeNote(pitchNum, startTime, endTime, instrumentNum, volumeNum));

          } catch (NumberFormatException e) {

          }

        }


      }
    }, KeyEvent.KEY_RELEASED);


    kbh.addEvent(KeyEvent.VK_R, new Runnable() {
      @Override
      public void run() {
        removing = true;
      }
    }, KeyEvent.KEY_PRESSED);

    kbh.addEvent(KeyEvent.VK_Q, new Runnable() {
      @Override
      public void run() {
        String string1 = JOptionPane.showInputDialog("Enter positive repeat start time");
        String string2 = JOptionPane.showInputDialog("Enter repeat end time after" + string1);
        String string3 = JOptionPane.showInputDialog("How many more times would" +
                " you like the notes to be repeated?");
        try {
          int start = Integer.parseInt(string1);
          int end = Integer.parseInt(string2);
          int numRepeats = Integer.parseInt(string3);
          if (start < 0 || start >= end || numRepeats <= 0) {
            throw new NumberFormatException();
          }
          for (Repeat r : model.getRepeats()) {
            if (r.getStart() == start || r.getEnd() == end || r.getStart() == end ||
                    r.getEnd() == start) {
              throw new NumberFormatException();
            }
          }
          int lastStart = -10;
          int earliestStart = 99999999;
          for (int i = 0; i < model.getEndings().size(); i++) {
            for (Ending e : model.getEndings().get(i)) {
              lastStart = Math.max(lastStart, e.getEnd());
              earliestStart = Math.min(earliestStart, e.getEnd());
            }
          }
          if (!((start < earliestStart && end < earliestStart) || (start >
                  lastStart && end > lastStart))) {
            throw new NumberFormatException();
          }
          model.addRepeat(start, end, numRepeats);
        } catch (NumberFormatException e) {

        }
      }
    }, KeyEvent.KEY_RELEASED);


    kbh.addEvent(KeyEvent.VK_R, new Runnable() {
      @Override
      public void run() {
        removing = false;
      }
    }, KeyEvent.KEY_RELEASED);

    kbh.addEvent(KeyEvent.VK_M, new Runnable() {
      @Override
      public void run() {
        moving = !moving;
      }
    }, KeyEvent.KEY_PRESSED);

    kbh.addEvent(KeyEvent.VK_E, new Runnable() {
      @Override
      public void run() {
        int endingStart = 0;
        if (model.getEndings().isEmpty()) {


        } else {
          String string0 = JOptionPane.showInputDialog("Which group of alternate endings would" +
                  " you like to add to? (up to " + model.getEndings().size() + ")");
          String string = JOptionPane.showInputDialog("Enter Ending time for new ending for group " +
                  "" + string0);
          try {

            int group = Integer.parseInt(string0);
            if (group < 0 || group > model.getEndings().size()) {
              throw new NumberFormatException();
            }

            for (Ending e : model.getEndings().get(group - 1)) {
              endingStart = e.getEndingStart();
              break;
            }

            int end = Integer.parseInt(string);
            if (end <= endingStart) {
              throw new NumberFormatException();
            }

            boolean alreadyExists = false;


            for (Ending e : model.getEndings().get(group - 1)) {
              if (e.getEnd() == end) {
                alreadyExists = true;
                break;
              }
            }

            if (!alreadyExists && end < model.getEnd() && end > 0) {
              model.addEnding(end, endingStart, group - 1);
            }
          } catch (NumberFormatException e) {

          }
        }

      }
    }, KeyEvent.KEY_RELEASED);

    kbh.addEvent(KeyEvent.VK_F, new Runnable() {
      @Override
      public void run() {
        String string0 = JOptionPane.showInputDialog("Where would you like your new group of " +
                "alternate endings to start?");
        String string1 = JOptionPane.showInputDialog("Where would you like your " +
                "first ending to begin?");
        String string2 = JOptionPane.showInputDialog("Where would you like it to end?");

        try {
          int endingStart = Integer.parseInt(string0);
          int start = Integer.parseInt(string1);
          int end = Integer.parseInt(string2);
          if (start < 0 || end <= start || endingStart >= start) {
            throw new NumberFormatException();
          }
          model.addEndingsGroup(endingStart, start, end);
        } catch (NumberFormatException e) {

        }
      }
    }, KeyEvent.KEY_RELEASED);


    kbh.addEvent(KeyEvent.VK_HOME, new Runnable() {
      @Override
      public void run() {
        view.home();
      }
    }, KeyEvent.KEY_PRESSED);

    kbh.addEvent(KeyEvent.VK_END, new Runnable() {
      @Override
      public void run() {
        view.end();
      }
    }, KeyEvent.KEY_PRESSED);

    kbh.addEvent(KeyEvent.VK_LEFT, new Runnable() {
      @Override
      public void run() {
        view.scrollLeft();
      }
    }, KeyEvent.KEY_PRESSED);

    kbh.addEvent(KeyEvent.VK_RIGHT, new Runnable() {
      @Override
      public void run() {
        view.scrollRight();
      }
    }, KeyEvent.KEY_PRESSED);

    kbh.addEvent(KeyEvent.VK_UP, new Runnable() {
      @Override
      public void run() {
        view.scrollUp();
      }
    }, KeyEvent.KEY_PRESSED);

    kbh.addEvent(KeyEvent.VK_DOWN, new Runnable() {
      @Override
      public void run() {
        view.scrollDown();
      }
    }, KeyEvent.KEY_PRESSED);

    mhandler.addMouseEvent(MouseEvent.BUTTON1 + MouseEvent.MOUSE_CLICKED, new Consumer<Point>() {
      @Override
      public void accept(Point point) {
        if (removing) {
          try {
            model.remove(view.getNoteAt(point));
          } catch (IllegalArgumentException e) {

          }
        }
      }
    });
    mhandler.addMouseEvent(MouseEvent.BUTTON1 + MouseEvent.MOUSE_PRESSED, new Consumer<Point>() {
      @Override
      public void accept(Point point) {
        if (moving) {
          try {
            noteMoving = view.getNoteAt(point);
            model.remove(view.getNoteAt(point));
            pt = point;
          } catch (IllegalArgumentException e) {

          }
        }
      }
    });
    mhandler.addMouseEvent(MouseEvent.BUTTON1 + MouseEvent.MOUSE_RELEASED, new Consumer<Point>() {
      @Override
      public void accept(Point point) {
        if (moving && (pt.x != 0 && pt.y != 0)) {
          try {
            model.addNote(Note.makeNote(noteMoving.getPitchNum() - (point.y - pt.y) / 20,
                    noteMoving.getStart() - ((pt.x - point.x) / 20), noteMoving.getEnd() -
                            ((pt.x - point.x) / 20),
                    noteMoving.getInstrument(), noteMoving.getVolume()));
            pt = new Point(0, 0);
          } catch (IllegalArgumentException e) {

          }
        }
      }
    });

    this.view.setKeyListener(kbh);
    this.view.setMouseListener(mhandler);

  }


  /**
   * Makes the controller go
   */
  public void go() throws InvalidMidiDataException, InterruptedException, IOException {
    timer.schedule(new TimerTask() {
      @Override
      public void run() {
        try {
          if (ended) {
            view.go(model.getBeat());
          } else if (!paused) {
            view.go(model.getBeat());
            model.setBeat(model.getBeat() + 1);
            if (model.getBeat() > model.getEnd()) {
              ended = true;
            }
          }
        } catch (Exception e) {
        }
      }
    }, 0, model.getTempo() / 1000);
  }
}
