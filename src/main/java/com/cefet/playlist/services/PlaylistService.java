package com.cefet.playlist.services;

import com.cefet.playlist.dto.PlaylistDTO;
import com.cefet.playlist.entities.Playlist;
import com.cefet.playlist.entities.Usuario;
import com.cefet.playlist.repositories.MusicasPlaylistRepository;
import com.cefet.playlist.repositories.PlaylistRepository;
import com.cefet.playlist.repositories.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistService {

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MusicasPlaylistRepository musicasPlaylistRepository;
    
    // Buscar todos
    public List<PlaylistDTO> findAll() {
        List<Playlist> playlists = playlistRepository.findAll();
        return playlists.stream().map(PlaylistDTO::new).toList();
    }

    // Buscar por ID
	public PlaylistDTO findById(Long id) {
		Playlist playlist = playlistRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Playlist não encontrada com ID: " + id));
		return new PlaylistDTO(playlist);
	}

    // Inserir Playlist
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

    // Atualizar Playlist
    public PlaylistDTO update(Long id, PlaylistDTO playlistDTO) {
    	Playlist playlist = playlistRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Playlist não encontrada com ID: " + id));
    	
    	playlist.setNome(playlistDTO.getNome());
        playlist.setVisibilidade(playlistDTO.getVisibilidade());

    	Playlist atualizado = playlistRepository.save(playlist);
        return new PlaylistDTO(atualizado);
    }
    
    // Remover por ID
    public void delete(Long id) {
        if (!playlistRepository.existsById(id)) {
            throw new EntityNotFoundException("Playlist não encontrada com ID: " + id);
        }
        // Deleta todas as associações da playlist (MusicasPlaylist)
        musicasPlaylistRepository.deleteByPlaylistId(id);

        playlistRepository.deleteById(id);
    } 

}
