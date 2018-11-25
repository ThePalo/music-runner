package lauzhack2018.music.music_runner;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;




public class MainScreen extends AppCompatActivity {

    class Knapsack {
        EditText in_time = findViewById(R.id.time);
        int max_time = Integer.parseInt(in_time.getText().toString()) * 1000 * 60;
        int total_songs = songList.size();
        ArrayList sum_values = new ArrayList<Integer>();
        int best = 0;
        ArrayList<Boolean> partial_sol = new ArrayList<>(total_songs);
        ArrayList<Boolean> sol;


        public void knapsack_order() {
            for (int i = 0; i < total_songs + 1; ++i) {
                sum_values.add(0);
                partial_sol.add(false);

            }
            for (int i = total_songs - 1; i >= 0; --i) {
                sum_values.set(i, ((Integer) sum_values.get(i + 1) + (Integer) songList.get(i).value));
            }
            i_knapsack_order(0, 0, 0);
            for (int i = 0; i < total_songs + 1; ++i) {
                if ((boolean) sol.get(i)) {
                    final_songList.add(songList.get(i));
                }
            }
        }

        private void i_knapsack_order(int i, int value, int time) {
            if (i == total_songs) {
                if (value > best) {
                    best = value;
                    sol = partial_sol;
                }
            } else {
                if (time + songList.get(i).time <= max_time && value + (int) sum_values.get(i) > best) {
                    partial_sol.set(i, true);
                    i_knapsack_order(i + 1, value + songList.get(i).value, time + songList.get(i).time);
                }
                if (value + (int) sum_values.get(i + 1) > best) {
                    partial_sol.set(i, false);
                    i_knapsack_order(i + 1, value, time);
                }
            }
        }
    }


    private ArrayList<Song> songList = new ArrayList<>();
    private ArrayList<Song> final_songList = new ArrayList<>();
    private Button playlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        getSongs();

        playlist = findViewById(R.id.playlist);
        playlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainScreen.Knapsack knapsack = new Knapsack();
                knapsack.knapsack_order();
                PlaylistSingleton playlist = new PlaylistSingleton();
                playlist.setPlaylist(final_songList);
                openPlay();
            }
        });
    }

    public void openPlay() {
        Intent intent = new Intent(this, Player.class);
        startActivity(intent);
    }

    public void getSongs() {

        ContentResolver musicResolver = getContentResolver();
        Uri musicUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor musicCursor = musicResolver.query(musicUri, null, null, null, null);

        //Caused by: java.lang.SecurityException: Permission Denial: reading com.android.providers.media.MediaProvider uri content://media/external/audio/media from pid=19069, uid=10101 requires android.permission.READ_EXTERNAL_STORAGE, or grantUriPermission()

        if (musicCursor != null && musicCursor.moveToFirst()) {
            //Get columns of interest
            int idColumn = musicCursor.getColumnIndex
                    (MediaStore.Audio.Media._ID);
            int titleColumn = musicCursor.getColumnIndex
                    (MediaStore.Audio.Media.TITLE);
            int timeColumn = musicCursor.getColumnIndex
                    (MediaStore.Audio.Media.DURATION);
            int artistColumn = musicCursor.getColumnIndex
                    (MediaStore.Audio.Media.ARTIST);
            int musicColumn = musicCursor.getColumnIndex
                    (MediaStore.Audio.Media.IS_MUSIC);
            while (musicCursor.moveToNext()) {
                if (musicCursor.getLong(musicColumn) != '0') {
                    long thisId = musicCursor.getLong(idColumn);
                    String thisTitle = musicCursor.getString(titleColumn);
                    int time = Integer.parseInt(musicCursor.getString(timeColumn));
                    String thisArtist = musicCursor.getString(artistColumn);
                    // CHANGE --> VALUE = 1   !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                    Song toAdd = new Song(thisId, thisTitle, time, 1, thisArtist);
                    songList.add(toAdd);
                }
            }
        }
    }
}


    //Let's put a value to all songs! (From 0 to 20)
   /* public void classify () {
        int n = songList.size();
        for (int i = 0; i < n; ++i) {
            if (songList.get(i).genre != null && songList.get(i).genre == myGenres) {
                songList.get(i).setValue(20);
            }
            else {
                process_data
            }
        }


    }*/

