package com.cefet.playlist.controller;

import com.cefet.playlist.dto.UsuarioDTO;
import com.cefet.playlist.dto.UsuarioInsertDTO;
import com.cefet.playlist.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioDTO> insertUser(@RequestBody UsuarioInsertDTO usuario) {
        UsuarioDTO user =  usuarioService.insert(usuario);
        return  ResponseEntity.ok(user);
    }


    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> updateUser(
            @PathVariable Long id,
            @RequestBody UsuarioInsertDTO dto) {
        UsuarioDTO user = usuarioService.update(id, dto);
        return ResponseEntity.ok(user);
    }


    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> getAllUsers() {
        List<UsuarioDTO> allUsers = usuarioService.findAll();
        return  ResponseEntity.ok(allUsers);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
