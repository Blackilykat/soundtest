package dev.blackilykat;

public abstract class Note implements Sample{
    public double position = 0.0;

    public Note(double position) {
        this.position = position;
    }

    @Override
    public double at(double seconds) {
        return play(seconds - position);
    }

    public abstract double play(double secondsSinceStart);
}
