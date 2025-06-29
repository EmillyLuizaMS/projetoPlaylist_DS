package com.cefet.playlist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cefet.playlist.dto.FavoritoDTO;
import com.cefet.playlist.entities.Favorito;
import com.cefet.playlist.entities.Playlist;
import com.cefet.playlist.entities.Usuario;
import com.cefet.playlist.repositories.FavoritoRepository;
import com.cefet.playlist.repositories.PlaylistRepository;
import com.cefet.playlist.repositories.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class FavoritoService {

    @Autowired 
    private FavoritoRepository favoritoRepository;

    @Autowired 
    private UsuarioRepository usuarioRepository;

    @Autowired 
    private PlaylistRepository playlistRepository;

    // Buscar por ID
    public FavoritoDTO findById(Long id) {
        Favorito fovorito = favoritoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("musica não encontrada com ID: " + id));
        return new FavoritoDTO(fovorito);
    }

    // Buscar todos
    public List<FavoritoDTO> findAll() {
        List<Favorito> lista = favoritoRepository.findAll();
        return lista.stream().map(FavoritoDTO::new).toList();
    }

    // Inserir 
    public FavoritoDTO insert(FavoritoDTO dto) {        
    	Favorito favorito = new Favorito();

        Long usuarioId = dto.getId_usuario();
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario não encontrado com ID: " + usuarioId));
        favorito.setUsuario(usuario);

        Long playlistId = dto.getId_playlist();
        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new IllegalArgumentException("Playlist não encontrado com ID: " + playlistId));
        favorito.setPlaylist(playlist);

        Favorito salvo = favoritoRepository.save(favorito);
        return new FavoritoDTO(salvo);
    }
    
    // Remover por ID
    public void delete(Long id) {
    if (!favoritoRepository.existsById(id)) {
        throw new EntityNotFoundException("Favorito não encontrado com ID: " + id);
    }
    favoritoRepository.deleteById(id);
}
    
}
