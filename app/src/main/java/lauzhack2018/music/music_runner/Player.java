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

    TextView songTitle = (TextView) findViewById(R.id.song_title_text);
    TextView songArtist = (TextView) findViewById(R.id.song_artist_text);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        data = PlaylistSingleton.getInstance();
        playlist = data.getPlaylist();

        MediaPlayerManager manager = new MediaPlayerManager(this);
        playerAdapter = manager;

        final Button playpauseButton = (Button) findViewById(R.id.play_pause_button);
        playpauseButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (playerAdapter.isPlaying()) {
                            playerAdapter.pause();
                            //playpauseButton.setText("Play"); //En vez de texto, hay que poner imagen
                        }
                        else {
                            playerAdapter.play();
                            //playpauseButton.setText("Pause");
                        }
                    }
                }
        );
    }

    @Override
    protected void onStart() {
        super.onStart();
        playerAdapter.loadSong(playlist.get(0).id);
        songTitle.setText(playlist.get(0).title);
        songArtist.setText(playlist.get(0).artist);
    }

}
