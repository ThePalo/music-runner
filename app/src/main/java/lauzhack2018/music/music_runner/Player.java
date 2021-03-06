package lauzhack2018.music.music_runner;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Player extends AppCompatActivity {

    private PlayerAdapter playerAdapter;
    ArrayList<Song> playlist;
    int currentSong;

    TextView songTitle;
    TextView songArtist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        currentSong = 0;

        PlaylistSingleton data = PlaylistSingleton.getInstance();
        playlist = data.getPlaylist();
        songTitle = (TextView) findViewById(R.id.song_title_text);
        songArtist = (TextView) findViewById(R.id.song_artist_text);

        MediaPlayerManager manager = new MediaPlayerManager(this);
        playerAdapter = manager;


        final Button play_and_pause = (Button) findViewById(R.id.play_pause_button);
        play_and_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!playerAdapter.isPlaying()) {
                    play_and_pause.setBackgroundResource(R.drawable.pause);
                    playerAdapter.play();
                } else {
                    play_and_pause.setBackgroundResource(R.drawable.play);
                    playerAdapter.pause();
                }
            }

        });

        Button nextButton = (Button) findViewById(R.id.next_button);
        nextButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (currentSong != playlist.size()-1) {
                            playerAdapter.reset();
                            playerAdapter.loadSong(currentSong+1);
                            songTitle.setText(playlist.get(currentSong+1).title);
                            songArtist.setText(playlist.get(currentSong+1).artist);
                            ++currentSong;
                            playerAdapter.play();
                        }
                        else {
                            //Toast
                        }
                    }
                }
        );

        Button prevButton = (Button) findViewById(R.id.previous_button);
        prevButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (currentSong != 0) {
                            playerAdapter.reset();
                            playerAdapter.loadSong(currentSong-1);
                            songTitle.setText(playlist.get(currentSong-1).title);
                            songArtist.setText(playlist.get(currentSong-1).artist);
                            --currentSong;
                            playerAdapter.play();
                        }
                        else {
                            playerAdapter.reset();
                            playerAdapter.loadSong(0);
                            songTitle.setText(playlist.get(0).title);
                            songArtist.setText(playlist.get(0).artist);
                            currentSong = 0;
                            playerAdapter.play();
                        }
                    }
                }
        );

    }

    @Override
    protected void onStart() {
        super.onStart();
        playerAdapter.loadSong(0);
        songTitle.setText(playlist.get(0).title);
        songArtist.setText(playlist.get(0).artist);
        currentSong = 0;
    }


}
