package cs3500.music.model;

import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by alexgomez on 11/3/15.
 */
public class MusicEditorModelImplTest {

  @Test
  public void testAddNote1() {
    ANote c = new Note(MusicEditorModel.Pitch.C, 1, 0, 3, 1, 1);
    ANote d = new Note(MusicEditorModel.Pitch.D, 1, 1, 5, 1, 1);
    ANote e = new Note(MusicEditorModel.Pitch.E, 1, 3, 4, 1, 1);
    ANote f = new Note(MusicEditorModel.Pitch.F, 3, 0, 2, 1, 1);
    ANote c2 = new Note(MusicEditorModel.Pitch.C, 1, 1, 6, 1, 1);
    ANote c3 = new Note(MusicEditorModel.Pitch.C, 1, 4, 6, 1, 1);
    ArrayList<ANote> lines1 = new ArrayList<>();
    lines1.add(c);
    lines1.add(d);
    lines1.add(e);
    lines1.add(f);
    lines1.add(c2);
    //lines1.add(c3);
    MusicEditorModel m1 = new MusicEditorModelImpl.Builder().addLines(lines1).build();
  }

  //tries to add a note that starts at the same time
  @Test(expected = IllegalArgumentException.class)
  public void testAddNote2() {
    ANote c = new Note(MusicEditorModel.Pitch.C, 1, 0, 3, 1, 1);
    ANote d = new Note(MusicEditorModel.Pitch.D, 1, 1, 4, 1, 1);
    ANote e = new Note(MusicEditorModel.Pitch.E, 1, 3, 1, 1, 1);
    ANote f = new Note(MusicEditorModel.Pitch.F, 3, 0, 2, 1, 1);
    ANote c2 = new Note(MusicEditorModel.Pitch.C, 1, 1, 5, 1, 1);
    ANote c3 = new Note(MusicEditorModel.Pitch.C, 1, 1, 2, 1, 1);
    ArrayList<ANote> lines1 = new ArrayList<>();
    lines1.add(c);
    lines1.add(d);
    lines1.add(e);
    lines1.add(f);
    lines1.add(c2);
    MusicEditorModel m1 = new MusicEditorModelImpl.Builder().addLines(lines1).build();
    m1.addNote(c3);
  }

  @Test
  public void testEdit1() {
    ANote c = new Note(MusicEditorModel.Pitch.C, 1, 0, 3, 1, 1);
    ANote d = new Note(MusicEditorModel.Pitch.D, 1, 1, 4, 1, 1);
    ANote e = new Note(MusicEditorModel.Pitch.E, 1, 3, 1, 1, 1);
    ANote f = new Note(MusicEditorModel.Pitch.F, 3, 0, 2, 1, 1);
    ANote c2 = new Note(MusicEditorModel.Pitch.C, 1, 1, 5, 1, 1);
    ANote c3 = new Note(MusicEditorModel.Pitch.C, 1, 1, 2, 1, 1);
    ArrayList<ANote> lines1 = new ArrayList<>();
    lines1.add(c);
    lines1.add(d);
    lines1.add(e);
    lines1.add(f);
    lines1.add(c2);
    MusicEditorModel m1 = new MusicEditorModelImpl.Builder().addLines(lines1).build();
    m1.edit(c, MusicEditorModel.Pitch.B, 3, 3, 5, 1, 1);
    //assertTrue(m1.getLines().contains(new Note(MusicEditorModel.Pitch.B, 3, 3, 5)));
    m1.edit(f, MusicEditorModel.Pitch.E, 10, 5, 6, 1, 1);
    //assertTrue(m1.getLines().contains(new Note(MusicEditorModel.Pitch.E, 10, 5, 6)));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEdit2() {
    ANote c = new Note(MusicEditorModel.Pitch.C, 1, 0, 3, 1, 1);
    ANote d = new Note(MusicEditorModel.Pitch.D, 1, 1, 4, 1, 1);
    ANote e = new Note(MusicEditorModel.Pitch.E, 1, 3, 1, 1, 1);
    ANote f = new Note(MusicEditorModel.Pitch.F, 3, 0, 2, 1, 1);
    ANote c2 = new Note(MusicEditorModel.Pitch.C, 1, 1, 5, 1, 1);
    ANote c3 = new Note(MusicEditorModel.Pitch.C, 1, 1, 2, 1, 1);
    ArrayList<ANote> lines1 = new ArrayList<>();
    lines1.add(c);
    lines1.add(d);
    lines1.add(e);
    lines1.add(f);
    lines1.add(c2);
    MusicEditorModel m1 = new MusicEditorModelImpl.Builder().addLines(lines1).build();
    m1.edit(c3, MusicEditorModel.Pitch.A, 3, 3, 3, 1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEdit3() {
    ANote c = new Note(MusicEditorModel.Pitch.C, 1, 0, 3, 1, 1);
    ANote d = new Note(MusicEditorModel.Pitch.D, 1, 1, 4, 1, 1);
    ANote e = new Note(MusicEditorModel.Pitch.E, 1, 3, 1, 1, 1);
    ANote f = new Note(MusicEditorModel.Pitch.F, 3, 0, 2, 1, 1);
    ANote c2 = new Note(MusicEditorModel.Pitch.C, 1, 1, 5, 1, 1);
    ANote c3 = new Note(MusicEditorModel.Pitch.C, 1, 1, 2, 1, 1);
    ArrayList<ANote> lines1 = new ArrayList<>();
    lines1.add(c);
    lines1.add(d);
    lines1.add(e);
    lines1.add(f);
    lines1.add(c2);
    MusicEditorModel m1 = new MusicEditorModelImpl.Builder().addLines(lines1).build();
    m1.edit(c, MusicEditorModel.Pitch.A, -3, -3, -3, 1, 1);
  }


  @Test
  public void testRemove() {
    ANote c = new Note(MusicEditorModel.Pitch.C, 1, 0, 3, 1, 1);
    ANote d = new Note(MusicEditorModel.Pitch.D, 1, 1, 4, 1, 1);
    ANote e = new Note(MusicEditorModel.Pitch.E, 1, 3, 1, 1, 1);
    ANote f = new Note(MusicEditorModel.Pitch.F, 3, 0, 2, 1, 1);
    ANote c2 = new Note(MusicEditorModel.Pitch.C, 1, 1, 5, 1, 1);
    ANote c3 = new Note(MusicEditorModel.Pitch.C, 1, 1, 2, 1, 1);
    ArrayList<ANote> lines1 = new ArrayList<>();
    lines1.add(c);
    lines1.add(d);
    lines1.add(e);
    lines1.add(f);
    lines1.add(c2);
    MusicEditorModel m1 = new MusicEditorModelImpl.Builder().addLines(lines1).build();
    //assertTrue(m1.getLines().contains(c2));
    m1.remove(c2);
    //assertFalse(m1.getLines().contains(c2));
    //assertTrue(m1.getLines().contains(f));
    m1.remove(f);
    //assertFalse(m1.getLines().contains(f));
  }

  //trying to remove when it is not in piece
  @Test(expected = IllegalArgumentException.class)
  public void testRemove2() {
    ANote c = new Note(MusicEditorModel.Pitch.C, 1, 0, 3, 1, 1);
    ANote d = new Note(MusicEditorModel.Pitch.D, 1, 1, 4, 1, 1);
    ANote e = new Note(MusicEditorModel.Pitch.E, 1, 3, 1, 1, 1);
    ANote f = new Note(MusicEditorModel.Pitch.F, 3, 0, 2, 1, 1);
    ANote c2 = new Note(MusicEditorModel.Pitch.C, 1, 1, 5, 1, 1);
    ANote c3 = new Note(MusicEditorModel.Pitch.C, 1, 1, 2, 1, 1);
    ArrayList<ANote> lines1 = new ArrayList<>();
    lines1.add(c);
    lines1.add(d);
    lines1.add(e);
    lines1.add(f);
    lines1.add(c2);
    MusicEditorModel m1 = new MusicEditorModelImpl.Builder().addLines(lines1).build();
    m1.remove(c3);
  }

//old toConsole methods
/*
  @Test
  public void testToConsole() throws IOException {
    ANote c = new Note(MusicEditorModel.Pitch.C, 1, 0, 3, 1, 1);
    ANote d = new Note(MusicEditorModel.Pitch.D, 1, 1, 4, 1, 1);
    ANote e = new Note(MusicEditorModel.Pitch.E, 1, 3, 1, 1, 1);
    ANote f = new Note(MusicEditorModel.Pitch.F, 3, 0, 2, 1, 1);
    ANote c2 = new Note(MusicEditorModel.Pitch.C, 1, 1, 5, 1, 1);
    ANote c3 = new Note(MusicEditorModel.Pitch.C, 1, 1, 2, 1, 1);
    ArrayList<ANote> lines1 = new ArrayList<>();
    lines1.add(c);
    lines1.add(d);
    lines1.add(e);
    lines1.add(f);
    lines1.add(c2);
    lines1.add(c3);
    MusicEditorModel m1 = new MusicEditorModelImpl.Builder().addLines(lines1).build();
    StringBuilder sb = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();
    m1.toConsole(sb);
    assertEquals(sb.toString(), "    C1C#1D1D#1E1F1F#1G1G#1A1A#1B1  C2C#2D2D#2E2F2F#2G2G#2A2A#2B2  "
            +
            "C3C#3D3D#3E3F3\n" +
            "0   X                                                                         X     " +
            "\n" +
            "1   X    X                                                                    |     " +
            "\n" +
            "2   |    |                                                                          " +
            "\n" +
            "3   |    |    X                                                                     " +
            "\n" +
            "4   X    |                                                                          " +
            "\n" +
            "5   |                                                                               " +
            "\n" +
            "\n");
    ArrayList<ANote> lines2 = new ArrayList<>();
    lines2.add(c);
    lines2.add(d);
    lines2.add(e);
    MusicEditorModel m2 = new MusicEditorModelImpl.Builder().addLines(lines2).build();
    m1.toConsole(sb2);
    assertEquals(sb2.toString(), "    C1C#1D1D#1E1\n" +
            "0   X               \n" +
            "1   |    X          \n" +
            "2   |    |          \n" +
            "3        |    X     \n" +
            "4        |          \n" +
            "5                   \n" +
            "\n");
  }
*/

}