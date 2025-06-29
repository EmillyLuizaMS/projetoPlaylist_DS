package com.cefet.playlist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cefet.playlist.dto.ComentarioDTO;
import com.cefet.playlist.entities.Comentario;
import com.cefet.playlist.entities.Playlist;
import com.cefet.playlist.repositories.ComentarioRepository;
import com.cefet.playlist.repositories.PlaylistRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ComentarioService {

    @Autowired 
    private ComentarioRepository comentarioRepository;

    @Autowired 
    private PlaylistRepository playlistRepository;

    // Buscar todos
    public List<ComentarioDTO> findAll() {
        List<Comentario> lista = comentarioRepository.findAll();
        return lista.stream().map(ComentarioDTO::new).toList();
    }

    // Buscar por ID
	public ComentarioDTO findById(Long id) {
		Comentario comentario = comentarioRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Comentario não encontrada com ID: " + id));
		return new ComentarioDTO(comentario);
	}

    // Inserir 
    public ComentarioDTO insert(ComentarioDTO dto) {        
    	Comentario comentario = new Comentario();

        comentario.setNota(dto.getNota());
        comentario.setComentario(dto.getComentario());
        comentario.setData(dto.getData());

        Long playlistId = dto.getId_playlist();
        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new IllegalArgumentException("Playlist não encontrado com ID: " + playlistId));
        comentario.setPlaylist(playlist);

        Comentario salvo = comentarioRepository.save(comentario);
        return new ComentarioDTO(salvo);
    }

    // Atualizar
    public ComentarioDTO update(Long id, ComentarioDTO comentarioDTO) {
    	Comentario comentario = comentarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comentario não encontrada com ID: " + id));
    	
    	comentario.setNota(comentarioDTO.getNota());
        comentario.setComentario(comentarioDTO.getComentario());

    	Comentario atualizado = comentarioRepository.save(comentario);
        return new ComentarioDTO(atualizado);
    }

    // Remover por ID
    public void delete(Long id) {
        if (!comentarioRepository.existsById(id)) {
            throw new EntityNotFoundException("Comentario não encontrado com ID: " + id);
        }
        comentarioRepository.deleteById(id);
    }
    
}
