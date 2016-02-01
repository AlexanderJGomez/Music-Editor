package cs3500.music.view;

import org.junit.Test;

import java.io.FileReader;

import cs3500.music.model.MusicEditorModel;
import cs3500.music.model.MusicEditorModelImpl;
import cs3500.music.util.MusicReader;
import static org.junit.Assert.assertEquals;

/**
 * Created by alexgomez on 11/15/15.
 */
public class ConsoleViewTest {
  /*
  This class would not allow for use of anything in JUNIT4, we did test everything manually
  in our main method so the assert equals call is commented out
   */
  @Test
  public void testConsole() throws Exception {
    MusicEditorModel model = MusicReader.parseFile(new FileReader("mary-little-lamb.txt"),
            new MusicEditorModelImpl.Builder());
    StringBuilder sb = new StringBuilder();
    ConsoleView view = new ConsoleView(model, sb);
    view.create();

    assertEquals(view.output.toString(),
            "    E4  F4  F#4 G4  G#4 A4  A#4 B4    C5  C#5 D5  D#5 E5  F5  F#5 G5  \n" +
                    "0               X                                     X                   \n" +
                    "1               |                                     |                   \n" +
                    "2               |                             X                           \n" +
                    "3               |                             |                           \n" +
                    "4               |                     X                                   \n" +
                    "5               |                     |                                   \n" +
                    "6               |                             X                           \n" +
                    "7                                             |                           \n" +
                    "8               X                                     X                   \n" +
                    "9               |                                     |                   \n" +
                    "10              |                                     X                   \n" +
                    "11              |                                     |                   \n" +
                    "12              |                                     X                   \n" +
                    "13              |                                     |                   \n" +
                    "14              |                                     |                   \n" +
                    "15                                                                        \n" +
                    "16              X                             X                           \n" +
                    "17              |                             |                           \n" +
                    "18              |                             X                           \n" +
                    "19              |                             |                           \n" +
                    "20              |                             X                           \n" +
                    "21              |                             |                           \n" +
                    "22              |                             |                           \n" +
                    "23              |                             |                           \n" +
                    "24              X                                     X                   \n" +
                    "25              |                                     |                   \n" +
                    "26                                                                X       \n" +
                    "27                                                                |       \n" +
                    "28                                                                X       \n" +
                    "29                                                                |       \n" +
                    "30                                                                |       \n" +
                    "31                                                                |       \n" +
                    "32              X                                     X                   \n" +
                    "33              |                                     |                   \n" +
                    "34              |                             X                           \n" +
                    "35              |                             |                           \n" +
                    "36              |                     X                                   \n" +
                    "37              |                     |                                   \n" +
                    "38              |                             X                           \n" +
                    "39              |                             |                           \n" +
                    "40              X                                     X                   \n" +
                    "41              |                                     |                   \n" +
                    "42              |                                     X                   \n" +
                    "43              |                                     |                   \n" +
                    "44              |                                     X                   \n" +
                    "45              |                                     |                   \n" +
                    "46              |                                     X                   \n" +
                    "47              |                                     |                   \n" +
                    "48              X                             X                           \n" +
                    "49              |                             |                           \n" +
                    "50              |                             X                           \n" +
                    "51              |                             |                           \n" +
                    "52              |                                     X                   \n" +
                    "53              |                                     |                   \n" +
                    "54              |                             X                           \n" +
                    "55              |                             |                           \n" +
                    "56  X                                 X                                   \n" +
                    "57  |                                 |                                   \n" +
                    "58  |                                 |                                   \n" +
                    "59  |                                 |                                   \n" +
                    "60  |                                 |                                   \n" +
                    "61  |                                 |                                   \n" +
                    "62  |                                 |                                   \n" +
                    "63  |                                 |                                   \n");

  }
}