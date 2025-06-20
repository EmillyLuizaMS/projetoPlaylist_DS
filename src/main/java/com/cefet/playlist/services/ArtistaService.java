package com.cefet.playlist.services;

import com.cefet.playlist.dto.ArtistaDTO;
import com.cefet.playlist.entities.Artista;
import com.cefet.playlist.repositories.ArtistaRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistaService {

    @Autowired
    private ArtistaRepository artistaRepository;

    // Buscar todos
	public List<ArtistaDTO> findAll() {
		List<Artista> artistas = artistaRepository.findAll();
		return artistas.stream().map(ArtistaDTO::new).toList();
	}

	// Buscar por ID
	public ArtistaDTO findById(Long id) {
		Artista artista = artistaRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Artista não encontrada com ID: " + id));
		return new ArtistaDTO(artista);
	}

    // Inserir Artista
    public ArtistaDTO insert(ArtistaDTO artistaDTO) {
        Artista artista = new Artista();
        artista.setNome(artistaDTO.getNome());
        artista.setDescricao(artistaDTO.getDescricao());

        Artista novoArtista = artistaRepository.save(artista);
        return new ArtistaDTO(novoArtista);
    }

    // Atualizar Artista
    public ArtistaDTO update(Long id, ArtistaDTO artistaDTO) {
    	Artista artista = artistaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Artista não encontrada com ID: " + id));
    	
    	artista.setNome(artistaDTO.getNome());
        artista.setDescricao(artistaDTO.getDescricao());
    	Artista atualizado = artistaRepository.save(artista);
        return new ArtistaDTO(atualizado);
    }
    
    // Remover por ID
    public void delete(Long id) {
        if (!artistaRepository.existsById(id)) {
            throw new EntityNotFoundException("Artista não encontrada com ID: " + id);
        }
        artistaRepository.deleteById(id);
    } 

}
