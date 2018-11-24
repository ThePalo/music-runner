package lauzhack2018.music.music_runner;

import java.util.ArrayList;

public class PlaylistSingleton {
        private ArrayList<Song> playlist;
        public ArrayList<Song> getPlaylist() {return playlist;}
        public void setPlaylist(ArrayList<Song> data) {this.playlist = playlist;}

        private static final PlaylistSingleton holder = new PlaylistSingleton();
        public static PlaylistSingleton getInstance() {return holder;}
}

