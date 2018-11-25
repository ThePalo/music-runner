package lauzhack2018.music.music_runner;

public interface PlayerAdapter {
    void loadSong(long id);
    void release();
    void reset();
    boolean isPlaying();
    void play();
    void pause();
}
