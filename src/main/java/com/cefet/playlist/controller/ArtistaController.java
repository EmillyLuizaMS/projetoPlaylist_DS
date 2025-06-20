package com.cefet.playlist.controller;


import com.cefet.playlist.dto.ArtistaDTO;
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
    
    @GetMapping("/{id}")
	public ResponseEntity<ArtistaDTO> findById(@PathVariable Long id) {
		ArtistaDTO dto = artistaService.findById(id);
		return ResponseEntity.ok(dto);
	}	

    @GetMapping
    public ResponseEntity<List<ArtistaDTO>> findAll() {
        List<ArtistaDTO> artistas = artistaService.findAll();
        return ResponseEntity.ok(artistas);
    }

    @PostMapping
    public ResponseEntity<ArtistaDTO> create(@RequestBody ArtistaDTO artistaDTO) {
        ArtistaDTO artistaDTO1 = artistaService.insert(artistaDTO);
        return ResponseEntity.ok(artistaDTO1);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ArtistaDTO> update(@PathVariable Long id, @RequestBody ArtistaDTO dto) {
		ArtistaDTO salvoDTO = artistaService.update(id, dto);
		return ResponseEntity.ok(salvoDTO);
	}
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
		artistaService.delete(id);
		return ResponseEntity.noContent().build();
	}	

}
