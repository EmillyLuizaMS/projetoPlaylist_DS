package com.cefet.playlist.services;


import com.cefet.playlist.dto.PlaylistDTO;
import com.cefet.playlist.entities.Artista;
import com.cefet.playlist.entities.Playlist;
import com.cefet.playlist.entities.Usuario;
import com.cefet.playlist.repositories.PlaylistRepository;
import com.cefet.playlist.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlaylistService {
    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public PlaylistDTO insert(PlaylistDTO playlistDTO) {
        Playlist playlist = new Playlist();
        playlist.setNome(playlistDTO.getNome());
        playlist.setVisibilidade(playlistDTO.getVisibilidade());

        // Buscar o artista pelo ID recebido no DTO
        Long userId = playlistDTO.getUserID();

        Usuario user = usuarioRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("usuario não encontrado com ID: " + userId));
        playlist.setUsuario(user);

        Playlist saved = playlistRepository.save(playlist);
        return new PlaylistDTO(saved);

    }

    public List<PlaylistDTO> findAll() {
        List<Playlist> playlists = playlistRepository.findAll();
        return playlists.stream().map(playlistDTO -> new PlaylistDTO(playlistDTO)).collect(Collectors.toList());
    }
}
