package cs3500.music;

import java.io.FileReader;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;

import cs3500.music.model.MusicEditorModel;
import cs3500.music.model.MusicEditorModelImpl;
import cs3500.music.util.MusicReader;
//import cs3500.music.view.ViewFactory;
import cs3500.music.controller.ControllerImpl;
import cs3500.music.view.CompoundView;

public class MusicEditor {
  public static void main(String[] args) throws IOException, InvalidMidiDataException,
          InterruptedException {
    MusicEditorModel m =
            MusicReader.parseFile(new FileReader("mary-little-lamb.txt"),
                    new MusicEditorModelImpl.Builder());

    CompoundView view = new CompoundView(m);
    ControllerImpl c = new ControllerImpl(view, m);
    c.go();


  }


}
