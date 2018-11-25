package lauzhack2018.music.music_runner;

import android.media.MediaPlayer;

public interface PlayerAdapter {
    void loadSong(int index);
    void reset();
    boolean isPlaying();
    void play();
    void pause();
    //void setOnCompletionListener(MediaPlayer mediaPlayer);
    //int getCurrentPosition();
}
