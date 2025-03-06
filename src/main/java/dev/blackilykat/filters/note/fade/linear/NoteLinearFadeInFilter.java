package dev.blackilykat.filters.note.fade.linear;

import dev.blackilykat.Note;

public class NoteLinearFadeInFilter extends Note {
    public double duration;
    private Note note;

    public NoteLinearFadeInFilter(Note note, double duration) {
        super(note.position);
        this.note = note;
        this.duration = duration;
    }

    @Override
    public double play(double seconds) {
        double v = note.play(seconds);
        if(seconds > duration) return v;
        return v * (seconds / duration);
    }
}
