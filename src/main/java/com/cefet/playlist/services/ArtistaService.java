package com.cefet.playlist.services;

import com.cefet.playlist.dto.ArtistaDTO;
import com.cefet.playlist.entities.Artista;
import com.cefet.playlist.repositories.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArtistaService {

    @Autowired
    private ArtistaRepository artistaRepository;

    public ArtistaDTO create(ArtistaDTO artistaDTO) {


        Artista artista = new Artista();
        artista.setNome(artistaDTO.getNome());
        artista.setDescricao(artistaDTO.getDescricao());

        Artista novoArtista = artistaRepository.save(artista);
        return new ArtistaDTO(novoArtista);
    }

    public List<ArtistaDTO> findAll() {
        List<Artista> artistas = artistaRepository.findAll();
        return artistas.stream().map(artista -> new ArtistaDTO(artista)).collect(Collectors.toList());
    }

}
