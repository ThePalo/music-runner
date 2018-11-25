package lauzhack2018.music.music_runner;

import java.util.ArrayList;

public class PlaylistSingleton {
        private ArrayList<Song> playlist;
        public ArrayList<Song> getPlaylist() {return playlist;}
        public void setPlaylist(ArrayList<Song> data) {this.playlist = playlist;}

        private static PlaylistSingleton holder = null;
        public static PlaylistSingleton getInstance() {
                if (holder == null) {
                        holder = new PlaylistSingleton();
                }
                return holder;
        }
}

