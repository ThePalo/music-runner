package lauzhack2018.music.music_runner;

import android.content.ContentUris;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

import java.io.IOException;
import java.util.ArrayList;

public class MediaPlayerManager implements PlayerAdapter {

    private Context context;
    private MediaPlayer mediaPlayer;

    ArrayList<Song> playlist;
    int currentSong;

    public MediaPlayerManager(Context context){
        this.context = context.getApplicationContext();
        PlaylistSingleton data = PlaylistSingleton.getInstance();
        playlist = data.getPlaylist();
    }

    private void initialize() {
        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
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
    public void reset() {
        mediaPlayer.reset();
    }


    @Override
    public boolean isPlaying() {
        if (mediaPlayer != null) {
            return mediaPlayer.isPlaying();
        }
        return false;
    }

    @Override
    public void loadSong(final int index) {
        initialize();
        Uri songUri = ContentUris.withAppendedId(
                android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, playlist.get(index).id);
        try {
            mediaPlayer.setDataSource(context.getApplicationContext(), songUri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.prepareAsync();
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
                currentSong = index;
            }
        });
    }

    public void setOnCompletionListener(MediaPlayer mediaPlayer) {
        if (mediaPlayer.getCurrentPosition() > 0) {
            mediaPlayer.reset();
            loadSong(currentSong+1);
        }
    }


}
