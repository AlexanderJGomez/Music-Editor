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
public class MidiViewImplTest {
  /*
  This class would not allow for use of anything in JUNIT4, we did test everything manually
  in our main method so the assert equals call is commented out
   */

  @Test
  public void testMidi() throws Exception {
    MusicEditorModel model = MusicReader.parseFile(new FileReader("mary-little-lamb.txt"),
            new MusicEditorModelImpl.Builder());
    StringBuilder sb = new StringBuilder();
    MidiViewImpl view = new MidiViewImpl(model, sb);
    view.mockOutput();

    assertEquals(sb.toString(), "Pitch: 64 Instrument: 0 goes on at time: 0 at volume:72\n" +
            "Pitch: 64 Instrument: 0 goes off at time: 400000 at volume:72\n" +
            "Pitch: 55 Instrument: 0 goes on at time: 0 at volume:70\n" +
            "Pitch: 55 Instrument: 0 goes off at time: 1400000 at volume:70\n" +
            "Pitch: 64 Instrument: 0 goes on at time: 0 at volume:72\n" +
            "Pitch: 64 Instrument: 0 goes off at time: 400000 at volume:72\n" +
            "Pitch: 55 Instrument: 0 goes on at time: 0 at volume:70\n" +
            "Pitch: 55 Instrument: 0 goes off at time: 1400000 at volume:70\n" +
            "Pitch: 55 Instrument: 0 goes on at time: 0 at volume:70\n" +
            "Pitch: 55 Instrument: 0 goes off at time: 1400000 at volume:70\n" +
            "Pitch: 62 Instrument: 0 goes on at time: 400000 at volume:72\n" +
            "Pitch: 62 Instrument: 0 goes off at time: 800000 at volume:72\n" +
            "Pitch: 55 Instrument: 0 goes on at time: 0 at volume:70\n" +
            "Pitch: 55 Instrument: 0 goes off at time: 1400000 at volume:70\n" +
            "Pitch: 62 Instrument: 0 goes on at time: 400000 at volume:72\n" +
            "Pitch: 62 Instrument: 0 goes off at time: 800000 at volume:72\n" +
            "Pitch: 55 Instrument: 0 goes on at time: 0 at volume:70\n" +
            "Pitch: 55 Instrument: 0 goes off at time: 1400000 at volume:70\n" +
            "Pitch: 60 Instrument: 0 goes on at time: 800000 at volume:71\n" +
            "Pitch: 60 Instrument: 0 goes off at time: 1200000 at volume:71\n" +
            "Pitch: 55 Instrument: 0 goes on at time: 0 at volume:70\n" +
            "Pitch: 55 Instrument: 0 goes off at time: 1400000 at volume:70\n" +
            "Pitch: 60 Instrument: 0 goes on at time: 800000 at volume:71\n" +
            "Pitch: 60 Instrument: 0 goes off at time: 1200000 at volume:71\n" +
            "Pitch: 55 Instrument: 0 goes on at time: 0 at volume:70\n" +
            "Pitch: 55 Instrument: 0 goes off at time: 1400000 at volume:70\n" +
            "Pitch: 62 Instrument: 0 goes on at time: 1200000 at volume:79\n" +
            "Pitch: 62 Instrument: 0 goes off at time: 1600000 at volume:79\n" +
            "Pitch: 62 Instrument: 0 goes on at time: 1200000 at volume:79\n" +
            "Pitch: 62 Instrument: 0 goes off at time: 1600000 at volume:79\n" +
            "Pitch: 55 Instrument: 0 goes on at time: 1600000 at volume:79\n" +
            "Pitch: 55 Instrument: 0 goes off at time: 3000000 at volume:79\n" +
            "Pitch: 64 Instrument: 0 goes on at time: 1600000 at volume:85\n" +
            "Pitch: 64 Instrument: 0 goes off at time: 2000000 at volume:85\n" +
            "Pitch: 55 Instrument: 0 goes on at time: 1600000 at volume:79\n" +
            "Pitch: 55 Instrument: 0 goes off at time: 3000000 at volume:79\n" +
            "Pitch: 64 Instrument: 0 goes on at time: 1600000 at volume:85\n" +
            "Pitch: 64 Instrument: 0 goes off at time: 2000000 at volume:85\n" +
            "Pitch: 55 Instrument: 0 goes on at time: 1600000 at volume:79\n" +
            "Pitch: 55 Instrument: 0 goes off at time: 3000000 at volume:79\n" +
            "Pitch: 64 Instrument: 0 goes on at time: 2000000 at volume:78\n" +
            "Pitch: 64 Instrument: 0 goes off at time: 2400000 at volume:78\n" +
            "Pitch: 55 Instrument: 0 goes on at time: 1600000 at volume:79\n" +
            "Pitch: 55 Instrument: 0 goes off at time: 3000000 at volume:79\n" +
            "Pitch: 64 Instrument: 0 goes on at time: 2000000 at volume:78\n" +
            "Pitch: 64 Instrument: 0 goes off at time: 2400000 at volume:78\n" +
            "Pitch: 55 Instrument: 0 goes on at time: 1600000 at volume:79\n" +
            "Pitch: 55 Instrument: 0 goes off at time: 3000000 at volume:79\n" +
            "Pitch: 64 Instrument: 0 goes on at time: 2400000 at volume:74\n" +
            "Pitch: 64 Instrument: 0 goes off at time: 3000000 at volume:74\n" +
            "Pitch: 55 Instrument: 0 goes on at time: 1600000 at volume:79\n" +
            "Pitch: 55 Instrument: 0 goes off at time: 3000000 at volume:79\n" +
            "Pitch: 64 Instrument: 0 goes on at time: 2400000 at volume:74\n" +
            "Pitch: 64 Instrument: 0 goes off at time: 3000000 at volume:74\n" +
            "Pitch: 55 Instrument: 0 goes on at time: 1600000 at volume:79\n" +
            "Pitch: 55 Instrument: 0 goes off at time: 3000000 at volume:79\n" +
            "Pitch: 64 Instrument: 0 goes on at time: 2400000 at volume:74\n" +
            "Pitch: 64 Instrument: 0 goes off at time: 3000000 at volume:74\n" +
            "Pitch: 55 Instrument: 0 goes on at time: 3200000 at volume:77\n" +
            "Pitch: 55 Instrument: 0 goes off at time: 4800000 at volume:77\n" +
            "Pitch: 62 Instrument: 0 goes on at time: 3200000 at volume:75\n" +
            "Pitch: 62 Instrument: 0 goes off at time: 3600000 at volume:75\n" +
            "Pitch: 55 Instrument: 0 goes on at time: 3200000 at volume:77\n" +
            "Pitch: 55 Instrument: 0 goes off at time: 4800000 at volume:77\n" +
            "Pitch: 62 Instrument: 0 goes on at time: 3200000 at volume:75\n" +
            "Pitch: 62 Instrument: 0 goes off at time: 3600000 at volume:75\n" +
            "Pitch: 55 Instrument: 0 goes on at time: 3200000 at volume:77\n" +
            "Pitch: 55 Instrument: 0 goes off at time: 4800000 at volume:77\n" +
            "Pitch: 62 Instrument: 0 goes on at time: 3600000 at volume:77\n" +
            "Pitch: 62 Instrument: 0 goes off at time: 4000000 at volume:77\n" +
            "Pitch: 55 Instrument: 0 goes on at time: 3200000 at volume:77\n" +
            "Pitch: 55 Instrument: 0 goes off at time: 4800000 at volume:77\n" +
            "Pitch: 62 Instrument: 0 goes on at time: 3600000 at volume:77\n" +
            "Pitch: 62 Instrument: 0 goes off at time: 4000000 at volume:77\n" +
            "Pitch: 55 Instrument: 0 goes on at time: 3200000 at volume:77\n" +
            "Pitch: 55 Instrument: 0 goes off at time: 4800000 at volume:77\n" +
            "Pitch: 62 Instrument: 0 goes on at time: 4000000 at volume:75\n" +
            "Pitch: 62 Instrument: 0 goes off at time: 4800000 at volume:75\n" +
            "Pitch: 55 Instrument: 0 goes on at time: 3200000 at volume:77\n" +
            "Pitch: 55 Instrument: 0 goes off at time: 4800000 at volume:77\n" +
            "Pitch: 62 Instrument: 0 goes on at time: 4000000 at volume:75\n" +
            "Pitch: 62 Instrument: 0 goes off at time: 4800000 at volume:75\n" +
            "Pitch: 55 Instrument: 0 goes on at time: 3200000 at volume:77\n" +
            "Pitch: 55 Instrument: 0 goes off at time: 4800000 at volume:77\n" +
            "Pitch: 62 Instrument: 0 goes on at time: 4000000 at volume:75\n" +
            "Pitch: 62 Instrument: 0 goes off at time: 4800000 at volume:75\n" +
            "Pitch: 55 Instrument: 0 goes on at time: 3200000 at volume:77\n" +
            "Pitch: 55 Instrument: 0 goes off at time: 4800000 at volume:77\n" +
            "Pitch: 62 Instrument: 0 goes on at time: 4000000 at volume:75\n" +
            "Pitch: 62 Instrument: 0 goes off at time: 4800000 at volume:75\n" +
            "Pitch: 55 Instrument: 0 goes on at time: 4800000 at volume:79\n" +
            "Pitch: 55 Instrument: 0 goes off at time: 5200000 at volume:79\n" +
            "Pitch: 64 Instrument: 0 goes on at time: 4800000 at volume:82\n" +
            "Pitch: 64 Instrument: 0 goes off at time: 5200000 at volume:82\n" +
            "Pitch: 55 Instrument: 0 goes on at time: 4800000 at volume:79\n" +
            "Pitch: 55 Instrument: 0 goes off at time: 5200000 at volume:79\n" +
            "Pitch: 64 Instrument: 0 goes on at time: 4800000 at volume:82\n" +
            "Pitch: 64 Instrument: 0 goes off at time: 5200000 at volume:82\n" +
            "Pitch: 67 Instrument: 0 goes on at time: 5200000 at volume:84\n" +
            "Pitch: 67 Instrument: 0 goes off at time: 5600000 at volume:84\n" +
            "Pitch: 67 Instrument: 0 goes on at time: 5200000 at volume:84\n" +
            "Pitch: 67 Instrument: 0 goes off at time: 5600000 at volume:84\n" +
            "Pitch: 67 Instrument: 0 goes on at time: 5600000 at volume:75\n" +
            "Pitch: 67 Instrument: 0 goes off at time: 6400000 at volume:75\n" +
            "Pitch: 67 Instrument: 0 goes on at time: 5600000 at volume:75\n" +
            "Pitch: 67 Instrument: 0 goes off at time: 6400000 at volume:75\n" +
            "Pitch: 67 Instrument: 0 goes on at time: 5600000 at volume:75\n" +
            "Pitch: 67 Instrument: 0 goes off at time: 6400000 at volume:75\n" +
            "Pitch: 67 Instrument: 0 goes on at time: 5600000 at volume:75\n" +
            "Pitch: 67 Instrument: 0 goes off at time: 6400000 at volume:75\n" +
            "Pitch: 55 Instrument: 0 goes on at time: 6400000 at volume:78\n" +
            "Pitch: 55 Instrument: 0 goes off at time: 8000000 at volume:78\n" +
            "Pitch: 64 Instrument: 0 goes on at time: 6400000 at volume:73\n" +
            "Pitch: 64 Instrument: 0 goes off at time: 6800000 at volume:73\n" +
            "Pitch: 55 Instrument: 0 goes on at time: 6400000 at volume:78\n" +
            "Pitch: 55 Instrument: 0 goes off at time: 8000000 at volume:78\n" +
            "Pitch: 64 Instrument: 0 goes on at time: 6400000 at volume:73\n" +
            "Pitch: 64 Instrument: 0 goes off at time: 6800000 at volume:73\n" +
            "Pitch: 55 Instrument: 0 goes on at time: 6400000 at volume:78\n" +
            "Pitch: 55 Instrument: 0 goes off at time: 8000000 at volume:78\n" +
            "Pitch: 62 Instrument: 0 goes on at time: 6800000 at volume:69\n" +
            "Pitch: 62 Instrument: 0 goes off at time: 7200000 at volume:69\n" +
            "Pitch: 55 Instrument: 0 goes on at time: 6400000 at volume:78\n" +
            "Pitch: 55 Instrument: 0 goes off at time: 8000000 at volume:78\n" +
            "Pitch: 62 Instrument: 0 goes on at time: 6800000 at volume:69\n" +
            "Pitch: 62 Instrument: 0 goes off at time: 7200000 at volume:69\n" +
            "Pitch: 55 Instrument: 0 goes on at time: 6400000 at volume:78\n" +
            "Pitch: 55 Instrument: 0 goes off at time: 8000000 at volume:78\n" +
            "Pitch: 60 Instrument: 0 goes on at time: 7200000 at volume:71\n" +
            "Pitch: 60 Instrument: 0 goes off at time: 7600000 at volume:71\n" +
            "Pitch: 55 Instrument: 0 goes on at time: 6400000 at volume:78\n" +
            "Pitch: 55 Instrument: 0 goes off at time: 8000000 at volume:78\n" +
            "Pitch: 60 Instrument: 0 goes on at time: 7200000 at volume:71\n" +
            "Pitch: 60 Instrument: 0 goes off at time: 7600000 at volume:71\n" +
            "Pitch: 55 Instrument: 0 goes on at time: 6400000 at volume:78\n" +
            "Pitch: 55 Instrument: 0 goes off at time: 8000000 at volume:78\n" +
            "Pitch: 62 Instrument: 0 goes on at time: 7600000 at volume:80\n" +
            "Pitch: 62 Instrument: 0 goes off at time: 8000000 at volume:80\n" +
            "Pitch: 55 Instrument: 0 goes on at time: 6400000 at volume:78\n" +
            "Pitch: 55 Instrument: 0 goes off at time: 8000000 at volume:78\n" +
            "Pitch: 62 Instrument: 0 goes on at time: 7600000 at volume:80\n" +
            "Pitch: 62 Instrument: 0 goes off at time: 8000000 at volume:80\n" +
            "Pitch: 55 Instrument: 0 goes on at time: 8000000 at volume:79\n" +
            "Pitch: 55 Instrument: 0 goes off at time: 9600000 at volume:79\n" +
            "Pitch: 64 Instrument: 0 goes on at time: 8000000 at volume:84\n" +
            "Pitch: 64 Instrument: 0 goes off at time: 8400000 at volume:84\n" +
            "Pitch: 55 Instrument: 0 goes on at time: 8000000 at volume:79\n" +
            "Pitch: 55 Instrument: 0 goes off at time: 9600000 at volume:79\n" +
            "Pitch: 64 Instrument: 0 goes on at time: 8000000 at volume:84\n" +
            "Pitch: 64 Instrument: 0 goes off at time: 8400000 at volume:84\n" +
            "Pitch: 55 Instrument: 0 goes on at time: 8000000 at volume:79\n" +
            "Pitch: 55 Instrument: 0 goes off at time: 9600000 at volume:79\n" +
            "Pitch: 64 Instrument: 0 goes on at time: 8400000 at volume:76\n" +
            "Pitch: 64 Instrument: 0 goes off at time: 8800000 at volume:76\n" +
            "Pitch: 55 Instrument: 0 goes on at time: 8000000 at volume:79\n" +
            "Pitch: 55 Instrument: 0 goes off at time: 9600000 at volume:79\n" +
            "Pitch: 64 Instrument: 0 goes on at time: 8400000 at volume:76\n" +
            "Pitch: 64 Instrument: 0 goes off at time: 8800000 at volume:76\n" +
            "Pitch: 55 Instrument: 0 goes on at time: 8000000 at volume:79\n" +
            "Pitch: 55 Instrument: 0 goes off at time: 9600000 at volume:79\n" +
            "Pitch: 64 Instrument: 0 goes on at time: 8800000 at volume:74\n" +
            "Pitch: 64 Instrument: 0 goes off at time: 9200000 at volume:74\n" +
            "Pitch: 55 Instrument: 0 goes on at time: 8000000 at volume:79\n" +
            "Pitch: 55 Instrument: 0 goes off at time: 9600000 at volume:79\n" +
            "Pitch: 64 Instrument: 0 goes on at time: 8800000 at volume:74\n" +
            "Pitch: 64 Instrument: 0 goes off at time: 9200000 at volume:74\n" +
            "Pitch: 55 Instrument: 0 goes on at time: 8000000 at volume:79\n" +
            "Pitch: 55 Instrument: 0 goes off at time: 9600000 at volume:79\n" +
            "Pitch: 64 Instrument: 0 goes on at time: 9200000 at volume:77\n" +
            "Pitch: 64 Instrument: 0 goes off at time: 9600000 at volume:77\n" +
            "Pitch: 55 Instrument: 0 goes on at time: 8000000 at volume:79\n" +
            "Pitch: 55 Instrument: 0 goes off at time: 9600000 at volume:79\n" +
            "Pitch: 64 Instrument: 0 goes on at time: 9200000 at volume:77\n" +
            "Pitch: 64 Instrument: 0 goes off at time: 9600000 at volume:77\n" +
            "Pitch: 55 Instrument: 0 goes on at time: 9600000 at volume:78\n" +
            "Pitch: 55 Instrument: 0 goes off at time: 11200000 at volume:78\n" +
            "Pitch: 62 Instrument: 0 goes on at time: 9600000 at volume:75\n" +
            "Pitch: 62 Instrument: 0 goes off at time: 10000000 at volume:75\n" +
            "Pitch: 55 Instrument: 0 goes on at time: 9600000 at volume:78\n" +
            "Pitch: 55 Instrument: 0 goes off at time: 11200000 at volume:78\n" +
            "Pitch: 62 Instrument: 0 goes on at time: 9600000 at volume:75\n" +
            "Pitch: 62 Instrument: 0 goes off at time: 10000000 at volume:75\n" +
            "Pitch: 55 Instrument: 0 goes on at time: 9600000 at volume:78\n" +
            "Pitch: 55 Instrument: 0 goes off at time: 11200000 at volume:78\n" +
            "Pitch: 62 Instrument: 0 goes on at time: 10000000 at volume:74\n" +
            "Pitch: 62 Instrument: 0 goes off at time: 10400000 at volume:74\n" +
            "Pitch: 55 Instrument: 0 goes on at time: 9600000 at volume:78\n" +
            "Pitch: 55 Instrument: 0 goes off at time: 11200000 at volume:78\n" +
            "Pitch: 62 Instrument: 0 goes on at time: 10000000 at volume:74\n" +
            "Pitch: 62 Instrument: 0 goes off at time: 10400000 at volume:74\n" +
            "Pitch: 55 Instrument: 0 goes on at time: 9600000 at volume:78\n" +
            "Pitch: 55 Instrument: 0 goes off at time: 11200000 at volume:78\n" +
            "Pitch: 64 Instrument: 0 goes on at time: 10400000 at volume:81\n" +
            "Pitch: 64 Instrument: 0 goes off at time: 10800000 at volume:81\n" +
            "Pitch: 55 Instrument: 0 goes on at time: 9600000 at volume:78\n" +
            "Pitch: 55 Instrument: 0 goes off at time: 11200000 at volume:78\n" +
            "Pitch: 64 Instrument: 0 goes on at time: 10400000 at volume:81\n" +
            "Pitch: 64 Instrument: 0 goes off at time: 10800000 at volume:81\n" +
            "Pitch: 55 Instrument: 0 goes on at time: 9600000 at volume:78\n" +
            "Pitch: 55 Instrument: 0 goes off at time: 11200000 at volume:78\n" +
            "Pitch: 62 Instrument: 0 goes on at time: 10800000 at volume:70\n" +
            "Pitch: 62 Instrument: 0 goes off at time: 11200000 at volume:70\n" +
            "Pitch: 55 Instrument: 0 goes on at time: 9600000 at volume:78\n" +
            "Pitch: 55 Instrument: 0 goes off at time: 11200000 at volume:78\n" +
            "Pitch: 62 Instrument: 0 goes on at time: 10800000 at volume:70\n" +
            "Pitch: 62 Instrument: 0 goes off at time: 11200000 at volume:70\n" +
            "Pitch: 52 Instrument: 0 goes on at time: 11200000 at volume:72\n" +
            "Pitch: 52 Instrument: 0 goes off at time: 12800000 at volume:72\n" +
            "Pitch: 60 Instrument: 0 goes on at time: 11200000 at volume:73\n" +
            "Pitch: 60 Instrument: 0 goes off at time: 12800000 at volume:73\n" +
            "Pitch: 52 Instrument: 0 goes on at time: 11200000 at volume:72\n" +
            "Pitch: 52 Instrument: 0 goes off at time: 12800000 at volume:72\n" +
            "Pitch: 60 Instrument: 0 goes on at time: 11200000 at volume:73\n" +
            "Pitch: 60 Instrument: 0 goes off at time: 12800000 at volume:73\n" +
            "Pitch: 52 Instrument: 0 goes on at time: 11200000 at volume:72\n" +
            "Pitch: 52 Instrument: 0 goes off at time: 12800000 at volume:72\n" +
            "Pitch: 60 Instrument: 0 goes on at time: 11200000 at volume:73\n" +
            "Pitch: 60 Instrument: 0 goes off at time: 12800000 at volume:73\n" +
            "Pitch: 52 Instrument: 0 goes on at time: 11200000 at volume:72\n" +
            "Pitch: 52 Instrument: 0 goes off at time: 12800000 at volume:72\n" +
            "Pitch: 60 Instrument: 0 goes on at time: 11200000 at volume:73\n" +
            "Pitch: 60 Instrument: 0 goes off at time: 12800000 at volume:73\n" +
            "Pitch: 52 Instrument: 0 goes on at time: 11200000 at volume:72\n" +
            "Pitch: 52 Instrument: 0 goes off at time: 12800000 at volume:72\n" +
            "Pitch: 60 Instrument: 0 goes on at time: 11200000 at volume:73\n" +
            "Pitch: 60 Instrument: 0 goes off at time: 12800000 at volume:73\n" +
            "Pitch: 52 Instrument: 0 goes on at time: 11200000 at volume:72\n" +
            "Pitch: 52 Instrument: 0 goes off at time: 12800000 at volume:72\n" +
            "Pitch: 60 Instrument: 0 goes on at time: 11200000 at volume:73\n" +
            "Pitch: 60 Instrument: 0 goes off at time: 12800000 at volume:73\n" +
            "Pitch: 52 Instrument: 0 goes on at time: 11200000 at volume:72\n" +
            "Pitch: 52 Instrument: 0 goes off at time: 12800000 at volume:72\n" +
            "Pitch: 60 Instrument: 0 goes on at time: 11200000 at volume:73\n" +
            "Pitch: 60 Instrument: 0 goes off at time: 12800000 at volume:73\n");

  }


}