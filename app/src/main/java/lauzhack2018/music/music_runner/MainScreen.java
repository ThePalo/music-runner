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
        int max_time = Integer.parseInt(in_time.getText().toString());
        int total_songs = songList.size();
        ArrayList sum_values = new ArrayList<Integer>(total_songs + 1);
        int best = 0;
        ArrayList<Song> partial_songList;

        public void knapsack_order() {
            sum_values.set(total_songs, 0);

            for (int i = total_songs - 1; i >= 0; --i) {
                sum_values.set(i, ((Integer) sum_values.get(i + 1) + (Integer) songList.get(i).value));
            }
            i_knapsack_order(0, 0, 0);
        }

        private void i_knapsack_order(int i, int value, int time) {
            if (i == total_songs) {
                if (value > best) {
                    best = value;
                    final_songList = partial_songList;
                }
            } else {
                if (time + songList.get(i).time <= max_time && value + (int) sum_values.get(i) > best) {
                    partial_songList.add(songList.get(i));
                    i_knapsack_order(i + 1, value + songList.get(i).value, time + songList.get(i).time);
                }
                if (value + (int) sum_values.get(i + 1) > best) {
                    partial_songList.remove(songList.get(i));
                    i_knapsack_order(i + 1, value, time);
                }
            }
        }
    }

    private ArrayList<Song> songList;
    private ListView songView;
    private ArrayList<Song> final_songList;
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
                    (android.provider.MediaStore.Audio.Media._ID);
            int titleColumn = musicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media.TITLE_KEY);
            int timeColumn = musicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media.DURATION);
            int artistColumn = musicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media.ARTIST);
            //Get Data of songs
            while (musicCursor.moveToNext()) {
                long thisId = musicCursor.getLong(idColumn);
                String thisTitle = musicCursor.getString(titleColumn);
                int time = Integer.parseInt(musicCursor.getString(timeColumn));
                String thisArtist = musicCursor.getString(artistColumn);
                songList.add(new Song(thisId, thisTitle, time, 1, thisArtist));
            }
        }
    }
}
