package com.cefet.playlist.controller;


import com.cefet.playlist.dto.MusicaDTO;
import com.cefet.playlist.services.MusicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/musica")
public class MusicaController {
    @Autowired
    private MusicaService musicaService;

    @PostMapping()
    public ResponseEntity<MusicaDTO> create(@RequestBody MusicaDTO musicaDTO) {
        MusicaDTO  musica = musicaService.insert(musicaDTO);
        return new ResponseEntity<>(musica, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MusicaDTO> findById(@PathVariable Long id) {
        MusicaDTO musica = musicaService.findById(id);
        return new ResponseEntity<>(musica, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<MusicaDTO> >findAll() {
        List<MusicaDTO> musicas = musicaService.findAll();
        return new ResponseEntity<>(musicas, HttpStatus.OK);
    }
}
