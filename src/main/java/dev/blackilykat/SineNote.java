package dev.blackilykat;

public class SineNote extends Note {
    public double duration;
    public int frequency;

    public SineNote(double position, double duration, int frequency) {
        super(position);
        this.duration = duration;
        this.frequency = frequency;
    }

    @Override
    public double play(double seconds) {
        if(seconds < 0 || seconds > duration) return 0;
        return Math.sin(seconds * frequency * 2 * Math.PI);
    }
}
