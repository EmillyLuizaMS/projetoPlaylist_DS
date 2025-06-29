package com.cefet.playlist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cefet.playlist.dto.MusicaDTO;
import com.cefet.playlist.dto.MusicasPlaylistDTO;
import com.cefet.playlist.services.MusicaPlaylistService;

@RestController
@RequestMapping("/musicas-playlist")
public class MusicaPlaylistController {
    
    @Autowired
    private MusicaPlaylistService musicaPlaylistService;

    // Inserir música na playlist
    @PostMapping("/add")
    public ResponseEntity<MusicasPlaylistDTO> addMusicaToPlaylist(@RequestBody MusicasPlaylistDTO musicasPlaylistDTO) {
        MusicasPlaylistDTO createdPlaylist = musicaPlaylistService.insert(musicasPlaylistDTO);
        return new ResponseEntity<>(createdPlaylist, HttpStatus.CREATED);
    }

    // Obter todas as músicas de uma playlist
    @GetMapping("/playlist/{playlistId}/musicas")
    public ResponseEntity<List<MusicaDTO>> getMusicasFromPlaylist(@PathVariable Long playlistId) {
        List<MusicaDTO> musicas = musicaPlaylistService.getMusicasFromPlaylist(playlistId);
        return ResponseEntity.ok(musicas);
    }

     // Remover música de uma playlist (por ID)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        musicaPlaylistService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Remover música de uma playlist pela associação música e playlist
    @DeleteMapping("/remove/{musicaId}/{playlistId}")
    public ResponseEntity<Void> delete(@PathVariable Long musicaId, @PathVariable Long playlistId) {
        musicaPlaylistService.delete(musicaId, playlistId);
        return ResponseEntity.noContent().build();
    }

}
