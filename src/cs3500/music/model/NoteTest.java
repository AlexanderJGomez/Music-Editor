package cs3500.music.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by alexgomez on 11/3/15.
 */
public class NoteTest {

  @Test
  public void testSetNote() {
    ANote note = new Note(MusicEditorModel.Pitch.A, 1, 10, 11, 1,1);
    note.setNote(MusicEditorModel.Pitch.B, 7, 8, 9, 1 ,1);
    assertEquals(note.getStart(), 8);
    assertEquals(note.getPitch(), MusicEditorModel.Pitch.B);
    assertEquals(note.getOctave(), 7);
    assertEquals(note.getEnd(), 11);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetNote2() {
    ANote note = new Note(MusicEditorModel.Pitch.A, 1, 10, 10, 1, 1);
    note.setNote(MusicEditorModel.Pitch.B, -7, 8, 9, 1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetNote3() {
    ANote note = new Note(MusicEditorModel.Pitch.A, 1, 10, 10, 1, 1);
    note.setNote(MusicEditorModel.Pitch.B, 7, -8, 9, 1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetNote4() {
    ANote note = new Note(MusicEditorModel.Pitch.A, 1, 10, 10, 1, 1);
    note.setNote(MusicEditorModel.Pitch.B, 7, 8, -9, 1, 1);
  }


  @Test
  public void testEquals() {
    assertFalse(new Note(MusicEditorModel.Pitch.E, 1, 1, 7, 1, 1).equals(
            new Note(MusicEditorModel.Pitch.E, 2, 2, 7, 1, 1)));
    assertTrue(new Note(MusicEditorModel.Pitch.A, 5, 5, 7, 1, 1).equals(
            new Note(MusicEditorModel.Pitch.A, 5, 5, 7, 1, 1)));
  }

  @Test
  public void testInRange() {
    assertTrue(new Note(MusicEditorModel.Pitch.A, 5, 5, 9, 1, 1).inRange(7));
    assertFalse(new Note(MusicEditorModel.Pitch.A, 5, 5, 10, 1, 1).inRange(20));
    assertFalse(new Note(MusicEditorModel.Pitch.A, 5, 5, 7, 1, 1).inRange(5));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInRange2() {
    ANote n = new Note(MusicEditorModel.Pitch.A, 5, 5, 10, 1, 1);
    n.inRange(-5);
  }


  @Test(expected = IllegalArgumentException.class)
  public void constructorTest1() {
    ANote x = new Note(MusicEditorModel.Pitch.C, -3, 5, 5, 1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructorTest2() {
    ANote x = new Note(MusicEditorModel.Pitch.C, 3, -5, 5, 1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructorTest3() {
    ANote x = new Note(MusicEditorModel.Pitch.C, 3, 5, -5, 1, 1);
  }

}