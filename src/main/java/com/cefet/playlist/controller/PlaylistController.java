package com.cefet.playlist.controller;


import com.cefet.playlist.dto.PlaylistDTO;
import com.cefet.playlist.entities.Playlist;
import com.cefet.playlist.repositories.PlaylistRepository;
import com.cefet.playlist.services.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/playlist")
public class PlaylistController {
    @Autowired
    private PlaylistService playlistService;

    @PostMapping
    public ResponseEntity<PlaylistDTO> insert(@RequestBody  PlaylistDTO playlistDTO) {
        PlaylistDTO playlistDTO1 = playlistService.insert(playlistDTO);
        return ResponseEntity.ok(playlistDTO1);
    }

    @GetMapping
    public ResponseEntity<List<PlaylistDTO>> findAll() {
        List<PlaylistDTO> playlistDTOList = playlistService.findAll();
        return ResponseEntity.ok(playlistDTOList);
    }
}
