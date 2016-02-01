package cs3500.music.view;

import cs3500.music.model.MusicEditorModel;

/**
 * Created by alexgomez on 11/15/15.
 */
public class ViewFactory {
  public static View make(String view, MusicEditorModel model) {
    switch (view) {
      case "midi":
        return new MidiViewImpl(model);
      case "console":
        return new ConsoleView(model, System.out);
      case "gui":
        return new GuiViewFrame(model);
      default:
        throw new IllegalArgumentException("invalid argument");
    }
  }

}
