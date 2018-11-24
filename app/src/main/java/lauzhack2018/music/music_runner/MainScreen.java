package lauzhack2018.music.music_runner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import andorid controllers;
import java.util.ArrayList;

import android.widget.ListView;




public class MainScreen extends AppCompatActivity {
    //Declare
    class Song {
        string title;
        //image??
        int time; //in  seconds
        int value = 1;

    }
    private ArrayList<Song> songList;
    private ListView songView;

    public MainScreen(ArrayList<Song> songList, ListView songView) {
        this.songList = songList;
        this.songView = songView;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
    }


}



