package com.cefet.playlist.controller;


import com.cefet.playlist.dto.ArtistaDTO;
import com.cefet.playlist.entities.Artista;
import com.cefet.playlist.repositories.ArtistaRepository;
import com.cefet.playlist.services.ArtistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artista")
public class ArtistaController {
    @Autowired
    private ArtistaService artistaService;

    @PostMapping
    public ResponseEntity<ArtistaDTO> cadastrarArtista(@RequestBody ArtistaDTO artistaDTO) {
        ArtistaDTO artistaDTO1 = artistaService.create(artistaDTO);
        return ResponseEntity.ok(artistaDTO1);

    }

    @GetMapping
    public ResponseEntity<List<ArtistaDTO>> findAll() {
        List<ArtistaDTO> artistas = artistaService.findAll();
        return ResponseEntity.ok(artistas);
    }

}
