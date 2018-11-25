package lauzhack2018.music.music_runner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class Player extends AppCompatActivity {

    private PlayerAdapter playerAdapter;
    PlaylistSingleton data = PlaylistSingleton.getInstance();
    ArrayList<Song> playlist = data.getPlaylist();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

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
    }

}
