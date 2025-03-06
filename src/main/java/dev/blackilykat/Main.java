package dev.blackilykat;

import dev.blackilykat.filters.note.fade.linear.NoteLinearFadeInFilter;
import dev.blackilykat.filters.note.fade.linear.NoteLinearFadeOffFilter;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.exit;

public class Main {
    public static void main(String[] args) throws LineUnavailableException {
        int sampleRate = 44100;
        AudioFormat audioFormat = new AudioFormat(
            AudioFormat.Encoding.PCM_SIGNED,
            sampleRate,
            16,
            2,
            4,
            sampleRate,
            true);

        DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
        if(!AudioSystem.isLineSupported(info)) {
            System.err.println("fuck");
            exit(1);
        }

        SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
        line.open(audioFormat);
        line.start();

        List<Sample> samples = new ArrayList<>();

        /*samples.add(
                new NoteLinearFadeInFilter(
                    new NoteLinearFadeOffFilter(
                            new SineNote(2, 1, 320), 0.3, 0.4
                    ), 0.05
                )
        );

         */
        //samples.add(new SineNote(3.5, 0.5, 500));

        samples.add(
                new NoteLinearFadeInFilter(
                        new NoteLinearFadeOffFilter(
                                new SineNote(2.2, 1, 440), 0.5, 0.4
                        ), 0.02
                )
        );

        for(long i = 0;; i++) {
            double seconds = (double) i / sampleRate;
            double v = 0.0;
            for(Sample sample : samples) {
                v += sample.at(seconds);
            }
            // change this appropriately in order to not have demonic sounds due to clipping in the next line
            // v /= 10;
            short s = (short) (v * Short.MAX_VALUE);
            byte[] sample = new byte[4];
            sample[0] = (byte) ((s & 0xFF00) >> 8);
            sample[1] = (byte) (s & 0xFF);
            sample[2] = sample[0];
            sample[3] = sample[1];
            line.write(sample, 0, 4);
        }
    }
}