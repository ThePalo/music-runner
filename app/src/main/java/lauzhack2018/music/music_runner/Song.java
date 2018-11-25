package lauzhack2018.music.music_runner;

public class Song {
    long id;
    String title;
    int time; //in  seconds
    int value;
    String artist;
    String genre;
    //image??

    public Song (long id, String title, int time, int value, String artist, String genre) {
        this.id = id;
        this.title = title;
        this.time = time;
        this.value = value;
        this.artist = artist;
        this.genre = genre;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getTime() {
        return time;
    }

    public int getValue() {
        return value;
    }

    public String getArtist() {
        return artist;
    }

    public String getGenre() {
        return genre;
    }

    public void setValue (int value) {
        this.value = value;
    }


}
