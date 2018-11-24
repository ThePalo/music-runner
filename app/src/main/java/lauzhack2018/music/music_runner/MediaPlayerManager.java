package lauzhack2018.music.music_runner;

import android.content.Context;
import android.media.MediaPlayer;

public class MediaPlayerManager implements PlayerAdapter {

    private Context context;
    private MediaPlayer mediaPlayer;

    public MediaPlayerManager(Context context){
        this.context = context.getApplicationContext();
    }

    private void initialize() {
        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
        }
    }

    public void loadSong (int id) {
        initialize();

        //get song from list, once we receive it
    }

    public void release() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    public void play() {
        if (mediaPlayer != null && !mediaPlayer.isPlaying())  {
            mediaPlayer.start();
        }
    }

    @Override
    public void pause() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    @Override
    public boolean isPlaying() {
        if (mediaPlayer != null) {
            return mediaPlayer.isPlaying();
        }
        return false;
    }
}
