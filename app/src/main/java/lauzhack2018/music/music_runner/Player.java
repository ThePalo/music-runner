package lauzhack2018.music.music_runner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Player extends AppCompatActivity {

    private PlayerAdapter playerAdapter;
    PlaylistSingleton data;
    ArrayList<Song> playlist;
    Button play_and_pause;
    Button next_button;
    TextView songTitle;
    TextView songArtist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        data = PlaylistSingleton.getInstance();
        playlist = data.getPlaylist();
        songTitle = (TextView) findViewById(R.id.song_title_text);
        songArtist = (TextView) findViewById(R.id.song_artist_text);

        MediaPlayerManager manager = new MediaPlayerManager(this);
        playerAdapter = manager;

        play_and_pause = findViewById(R.id.play_pause_button);
        play_and_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (playerAdapter.isPlaying()) {
                    play_and_pause.setBackgroundResource(R.drawable.pause);
                } else {
                    play_and_pause.setBackgroundResource(R.drawable.play);
                }
            }

        });
    }



    @Override
    protected void onStart() {
        super.onStart();
        playerAdapter.loadSong(playlist.get(0).id);
        songTitle.setText(playlist.get(0).title);
        songArtist.setText(playlist.get(0).artist);
    }

    public void continue_song(){
             int i = 1;
            while(i<playlist.size())

    {
        playerAdapter.loadSong(playlist.get(i).id);
        songTitle.setText(playlist.get(i).title);
        songArtist.setText(playlist.get(i).artist);
        ++i;
    }
}
    }

