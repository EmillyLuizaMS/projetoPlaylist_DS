package com.cefet.playlist.services;


import com.cefet.playlist.dto.MusicaDTO;
import com.cefet.playlist.entities.Artista;
import com.cefet.playlist.entities.Musica;
import com.cefet.playlist.repositories.ArtistaRepository;
import com.cefet.playlist.repositories.MusicaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicaService {
    @Autowired
    private MusicaRepository musicaRepository;

    @Autowired
    private ArtistaRepository artistaRepository;

    public MusicaDTO findById(Long id) {
        Musica musica = musicaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("musica não encontrada com ID: " + id));
        return new MusicaDTO(musica);
    }

    public List<MusicaDTO> findAll() {
        List<Musica> musicas = musicaRepository.findAll();
        return musicas.stream().map(MusicaDTO::new).toList();
    }

    public MusicaDTO insert(MusicaDTO musicaDTO) {


        Musica musica = new Musica();
        musica.setNome(musicaDTO.getNome());
        musica.setTags(musicaDTO.getTags());
        musica.setUrlMusica(musicaDTO.getUrl_musica());

        // Buscar o artista pelo ID recebido no DTO
        Long artistaId = musicaDTO.getId_artista();

        Artista artista = artistaRepository.findById(artistaId)
                .orElseThrow(() -> new IllegalArgumentException("Artista não encontrado com ID: " + artistaId));
        musica.setArtista(artista);

        Musica novaMusica = musicaRepository.save(musica);
        return new MusicaDTO(novaMusica);
    }


}
