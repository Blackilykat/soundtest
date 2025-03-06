package dev.blackilykat;

public class SineWaveSample implements Sample {
    double u = 0.0;
    double v = 0.0;
    double lastSeconds = 0.0;

    @Override
    public double at(double seconds) {
        double frequencyFrequency = seconds * 25;
        u += (seconds - lastSeconds) * frequencyFrequency;
        double frequency = Math.sin(u * 2 * Math.PI) * 100 + 500;
        v += (seconds - lastSeconds) * frequency;
        lastSeconds = seconds;

        return Math.sin(v * 2 * Math.PI);
    }
}
