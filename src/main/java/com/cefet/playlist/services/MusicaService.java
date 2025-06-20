package com.cefet.playlist.services;

import com.cefet.playlist.dto.MusicaDTO;
import com.cefet.playlist.entities.Artista;
import com.cefet.playlist.entities.Musica;
import com.cefet.playlist.entities.MusicasPlaylist;
import com.cefet.playlist.repositories.ArtistaRepository;
import com.cefet.playlist.repositories.MusicaRepository;
import com.cefet.playlist.repositories.MusicasPlaylistRepository;

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

    @Autowired
    private MusicasPlaylistRepository musicasPlaylistRepository;

    // Buscar por ID
    public MusicaDTO findById(Long id) {
        Musica musica = musicaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("musica não encontrada com ID: " + id));
        return new MusicaDTO(musica);
    }

    // Buscar todos
    public List<MusicaDTO> findAll() {
        List<Musica> musicas = musicaRepository.findAll();
        return musicas.stream().map(MusicaDTO::new).toList();
    }

    // Inserir Musica
    public MusicaDTO insert(MusicaDTO musicaDTO) {
        Artista artista = artistaRepository.findById(musicaDTO.getId_artista())
				.orElseThrow(() -> new EntityNotFoundException("Musica não encontrado com ID: " + musicaDTO.getId_artista()));
        
        Musica musica = new Musica();
        musica.setNome(musicaDTO.getNome());
        musica.setTags(musicaDTO.getTags());
        musica.setUrlMusica(musicaDTO.getUrl_musica());
        musica.setArtista(artista);

        Musica novaMusica = musicaRepository.save(musica);
        return new MusicaDTO(novaMusica);
    }

    //Atualizar Musica
    public MusicaDTO update(Long id, MusicaDTO musicaDTO) {
    	Musica musica = musicaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Musica não encontrada com ID: " + id));

        Artista artista = artistaRepository.findById(musicaDTO.getId_artista())
				.orElseThrow(() -> new EntityNotFoundException("Musica não encontrado com ID: " + musicaDTO.getId_artista()));
    	
    	musica.setNome(musicaDTO.getNome());
        musica.setTags(musicaDTO.getTags());
        musica.setUrlMusica(musicaDTO.getUrl_musica());
         musica.setArtista(artista);

    	Musica atualizado = musicaRepository.save(musica);
        return new MusicaDTO(atualizado);
    }

    // Remover por ID
	public void delete(Long id) {
		Musica musica = musicaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Música não encontrada com ID: " + id));

        // Remover a  música das playlists
        List<MusicasPlaylist> musicasPlaylists = musicasPlaylistRepository.findByMusica(musica);
        if (!musicasPlaylists.isEmpty()) {
            musicasPlaylistRepository.deleteAll(musicasPlaylists);
        }

		musicaRepository.deleteById(id);
	}

}
