package lauzhack2018.music.music_runner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Player extends AppCompatActivity {

    private PlayerAdapter playerAdapter;
    private boolean is_playing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        final Button playpauseButton = (Button) findViewById(R.id.play_pause_button);
        playpauseButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (is_playing) {
                            playerAdapter.pause();
                            playpauseButton.setText("Play"); //En vez de texto, hay que poner imagen
                        }
                        else {
                            playerAdapter.play();
                            playpauseButton.setText("Pause");
                        }
                    }
                }
        );

        MediaPlayerManager manager = new MediaPlayerManager(this);
        playerAdapter = manager;
    }

}
