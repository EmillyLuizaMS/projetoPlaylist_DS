package com.cefet.playlist.controller;

import com.cefet.playlist.dto.PlaylistDTO;
import com.cefet.playlist.services.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/playlist")
public class PlaylistController {
    @Autowired
    private PlaylistService playlistService;

    @PostMapping
    public ResponseEntity<PlaylistDTO> create(@RequestBody  PlaylistDTO playlistDTO) {
        PlaylistDTO playlistDTO1 = playlistService.insert(playlistDTO);
        return ResponseEntity.ok(playlistDTO1);
    }

    @GetMapping
    public ResponseEntity<List<PlaylistDTO>> findAll() {
        List<PlaylistDTO> playlistDTOList = playlistService.findAll();
        return ResponseEntity.ok(playlistDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlaylistDTO> findById(@PathVariable Long id) {
        PlaylistDTO playlist = playlistService.findById(id);
        return new ResponseEntity<>(playlist, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlaylistDTO> update(@PathVariable Long id, @RequestBody PlaylistDTO dto) {
		PlaylistDTO salvoDTO = playlistService.update(id, dto);
		return ResponseEntity.ok(salvoDTO);
	}
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
		playlistService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
