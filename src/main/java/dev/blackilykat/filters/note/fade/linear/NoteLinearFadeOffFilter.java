package dev.blackilykat.filters.note.fade.linear;

import dev.blackilykat.Note;

public class NoteLinearFadeOffFilter extends Note {
    public double start;
    public double duration;
    private Note note;

    public NoteLinearFadeOffFilter(Note note, double start, double duration) {
        super(note.position);
        this.note = note;
        this.start = start;
        this.duration = duration;
    }

    @Override
    public double play(double seconds) {
        double v = note.play(seconds);
        double delta = seconds - start;
        if(delta < 0) return v;
        if(delta > duration) return 0;
        return v * (1 - (delta / duration));
    }
}
