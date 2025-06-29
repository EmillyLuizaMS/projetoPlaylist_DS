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

import com.cefet.playlist.dto.FavoritoDTO;
import com.cefet.playlist.services.FavoritoService;

@RestController
@RequestMapping("/favorito")
public class FavoritoController {

    @Autowired
    private FavoritoService favoritoService;

    @GetMapping("/{id}")
    public ResponseEntity<FavoritoDTO> findById(@PathVariable Long id) {
        FavoritoDTO favorito = favoritoService.findById(id);
        return new ResponseEntity<>(favorito, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<FavoritoDTO>> getAll() {
        List<FavoritoDTO> favoritos = favoritoService.findAll();
        return  ResponseEntity.ok(favoritos);
    }

    @PostMapping
	public ResponseEntity<FavoritoDTO> create(@RequestBody FavoritoDTO favoritoDTO) {
		FavoritoDTO novoDTO = favoritoService.insert(favoritoDTO);
		return ResponseEntity.status(201).body(novoDTO); 
	}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        favoritoService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
}
