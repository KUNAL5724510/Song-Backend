package music.song.com;

import org.springframework.web.multipart.MultipartFile;

public class Song {
    private String title;
    private String artist;
    private Song next;
    private String filepath;
    ; // Assuming you want to keep the file reference
    
    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public Song getNext() {
        return next;
    }

    public void setNext(Song next) {
        this.next = next;
    }

    public Song(String title, String artist, String filepath) {
        this.title = title;
        this.artist = artist;
        this.filepath = filepath;
        this.next = null;
    }

    
}
