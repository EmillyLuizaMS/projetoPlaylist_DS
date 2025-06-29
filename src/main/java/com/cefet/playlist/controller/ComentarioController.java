package com.cefet.playlist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cefet.playlist.dto.ComentarioDTO;
import com.cefet.playlist.services.ComentarioService;

@RestController
@RequestMapping("/comentario")
public class ComentarioController {
    
    @Autowired
    private ComentarioService comentarioService;

    @GetMapping("/{id}")
	public ResponseEntity<ComentarioDTO> findById(@PathVariable Long id) {
		ComentarioDTO dto = comentarioService.findById(id);
		return ResponseEntity.ok(dto);
	}	
	
	@GetMapping
	public ResponseEntity<List<ComentarioDTO>> findAll() {
		List<ComentarioDTO> dtos = comentarioService.findAll();
		return ResponseEntity.ok(dtos);
	}
	
	@PostMapping
	public ResponseEntity<ComentarioDTO> create(@RequestBody ComentarioDTO dto) {
		ComentarioDTO novoDTO = comentarioService.insert(dto);
		return ResponseEntity.status(201).body(novoDTO); 
	}	
	
	@PutMapping("/{id}")
	public ResponseEntity<ComentarioDTO> update(@PathVariable Long id, @RequestBody ComentarioDTO dto) {
		ComentarioDTO salvoDTO = comentarioService.update(id, dto);
		return ResponseEntity.ok(salvoDTO); 
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		comentarioService.delete(id);
		return ResponseEntity.noContent().build(); 
	}

}
