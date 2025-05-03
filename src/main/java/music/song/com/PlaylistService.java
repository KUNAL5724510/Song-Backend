package music.song.com;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PlaylistService {
   private Song head;
   private Song tail;
   private Song current;

   public void addSong(String title, String artist, MultipartFile file) {
      try {
          // Step 1: Create folder path based on project directory
          String folderPath = System.getProperty("user.dir") + "/songs/";
          File folder = new File(folderPath);
          if (!folder.exists()) {
              folder.mkdirs(); // Make folder if not exist
          }
  
          // Step 2: Save file to disk
          File destinationFile = new File(folder, file.getOriginalFilename());
          file.transferTo(destinationFile);
  
          // Step 3: Store relative path for frontend
          String path = "songs/" + file.getOriginalFilename();
          Song newSong = new Song(title, artist, path);
  
          // Step 4: Linked list logic
          if (head == null) {
              head = newSong;
              tail = newSong;
              current = newSong;
          } else {
              tail.setNext(newSong);
              tail = newSong;
          }
  
      } catch (Exception e) {
          e.printStackTrace();
      }
  }

    public List<String> getAllSongs() {
        List<String> songs = new ArrayList<>();
         
        if (head == null) {
         System.out.println("No songs in the playlist.");
            return songs; // Return empty list if no songs are present
         }else{
            Song songPointer = head;
            while (songPointer != null) {
                songs.add(songPointer.getTitle() + " by " + songPointer.getArtist());
                songPointer = songPointer.getNext();
            
         }
         return songs;
      }
    }
    
    public Song getSongByTitle(String title) {
      Song pointer = head;
  
      while (pointer != null) {
          if (pointer.getTitle().equalsIgnoreCase(title)) {
              return pointer;
          }
          pointer = pointer.getNext();
      }
  
      return null; // Not found
     }
  

    public void playCurrentSong() {
    if (current == null) {
        System.out.println("No song is currently playing.");
    } else {
        System.out.println("Playing: " + current.getTitle() + " by " + current.getArtist());
      }
   }

      public void playNextSong() {
         if (current == null) {
               current = head;
         } else {
               current = current.getNext();
         }
         playCurrentSong();
      }
   
      

}