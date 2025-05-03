package music.song.com;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/soongs")
public class PlaylistController {
    
    @Autowired
    private PlaylistService playlistService;
    
    @PostMapping("/add")
    public void addSong(
        @RequestParam String title,
        @RequestParam String artist,
        @RequestParam MultipartFile file 
        ) {
        playlistService.addSong(title, artist, file);
    }   
    
    @GetMapping("/all")
    public List<String> getAllSongs() {
        return playlistService.getAllSongs();
    }

    
    @GetMapping("/plays")
    public void playCurrentSong() {
        playlistService.playCurrentSong();
    }

    @GetMapping("/next")
    public void playNextSong() {
        playlistService.playNextSong();
    }

    @GetMapping("/play")
    public ResponseEntity<Resource> playSong(@RequestParam String title) throws IOException {
        
        // 🧹 Clean input to take first value only (remove duplicates)
        String cleanTitle = title.split(",")[0].trim();
    
        System.out.println("✅ Hit received for: " + cleanTitle);
        String filePath = "songs/" + cleanTitle + ".mp3";
    
        System.out.println("🔍 Looking for: " + filePath);
        File songsDir = new File("songs");
        for (File f : songsDir.listFiles()) {
            System.out.println("📂 Found file: " + f.getName());
        }
    
        File file = new File(filePath);
    
        if (!file.exists()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Song not found");
        }
    
        FileSystemResource resource = new FileSystemResource(file);
    
        return ResponseEntity.ok()
            .contentType(MediaType.valueOf("audio/mpeg"))
            .body(resource);
    }
    



}
