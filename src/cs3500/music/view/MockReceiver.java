package cs3500.music.view;

/**
 * Created by alexgomez on 11/15/15.
 */

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

public class MockReceiver implements Receiver {
  StringBuilder sb = new StringBuilder();

  /**
   * Sends a MIDI message and time-stamp to this receiver. If time-stamping is not supported by this
   * receiver, the time-stamp value should be -1.
   *
   * @param message   the MIDI message to send
   * @param timeStamp the time-stamp for the message, in microseconds.
   * @throws IllegalStateException if the receiver is closed
   */
  @Override
  public void send(MidiMessage message, long timeStamp) {
    ShortMessage message2 = (ShortMessage) message;
    if (message2.getCommand() == ShortMessage.NOTE_ON) {

      sb.append("Pitch: " + message2.getData1() + " Instrument: " + message2.getChannel() +
              " goes on at time: " + timeStamp + " at volume:" + message2.getData2() + "\n");
    } else {
      sb.append("Pitch: " + message2.getData1() + " Instrument: " + message2.getChannel() +
              " goes off at time: " + timeStamp + " at volume:" + message2.getData2() + "\n");
    }
  }


  /**
   * Indicates that the application has finished using the receiver, and that limited resources it
   * requires may be released or made available.
   *
   * <p>If the creation of this <code>Receiver</code> resulted in implicitly opening the underlying
   * device, the device is implicitly closed by this method. This is true unless the device is kept
   * open by other <code>Receiver</code> or <code>Transmitter</code> instances that opened the
   * device implicitly, and unless the device has been opened explicitly. If the device this
   * <code>Receiver</code> is retrieved from is closed explicitly by calling {@link MidiDevice#close
   * MidiDevice.close}, the <code>Receiver</code> is closed, too.  For a detailed description of
   * open/close behaviour see the class description of {@link MidiDevice MidiDevice}.
   *
   * @see MidiSystem#getReceiver
   */
  @Override
  public void close() {

  }
}
