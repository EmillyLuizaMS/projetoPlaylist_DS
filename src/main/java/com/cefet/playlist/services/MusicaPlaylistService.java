package com.cefet.playlist.services;

import com.cefet.playlist.dto.MusicaDTO;
import com.cefet.playlist.dto.MusicasPlaylistDTO;
import com.cefet.playlist.entities.Musica;
import com.cefet.playlist.entities.MusicasPlaylist;
import com.cefet.playlist.entities.Playlist;
import com.cefet.playlist.repositories.MusicaRepository;
import com.cefet.playlist.repositories.MusicasPlaylistRepository;
import com.cefet.playlist.repositories.PlaylistRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MusicaPlaylistService {

    @Autowired
    MusicasPlaylistRepository musicasPlaylistRepository;
    
    @Autowired
    MusicaRepository musicaRepository;

    @Autowired
    PlaylistRepository playlistRepository;

    // Inserir música na playlist
    public MusicasPlaylistDTO insert(MusicasPlaylistDTO musicasPlaylistDTO) {
        MusicasPlaylist musicasPlaylist = new MusicasPlaylist();

        Musica musica = musicaRepository.findById(musicasPlaylistDTO.getId_musica())
                .orElseThrow(() -> new EntityNotFoundException("Musica não encontrada com ID: " + musicasPlaylistDTO.getId_musica()));

        Playlist playlist = playlistRepository.findById(musicasPlaylistDTO.getId_playlist())
                .orElseThrow(() -> new EntityNotFoundException("Playlist não encontrada com ID: " + musicasPlaylistDTO.getId_playlist()));

        // Verifica se a música já está na playlist
        boolean exists = musicasPlaylistRepository.existsByMusicaIdAndPlaylistId(musicasPlaylistDTO.getId_musica(), musicasPlaylistDTO.getId_playlist());
        if (exists) {
            throw new DuplicateKeyException("A música já está na playlist.");
        }

        musicasPlaylist.setMusica(musica);
        musicasPlaylist.setPlaylist(playlist);

        MusicasPlaylist playlist1 = musicasPlaylistRepository.save(musicasPlaylist);
        return new MusicasPlaylistDTO(playlist1);

    }

    /*public List<MusicaDTO> getMusicasFromPlaylist(Long playlistId) {
          List<MusicasPlaylistDTO> musicasPlaylistDTOList = musicasPlaylistRepository.findAllById(playlistId).stream().toList();

          List<MusicaDTO> musicasDTO = musicasPlaylistDTOList.stream()
                    .map(musicaPlaylist -> {
                        Musica musica = musicaRepository.findById(musicaPlaylist.getId_musica()).orElseThrow(
                                () -> new EntityNotFoundException("Musica nao encontrada")
                        );
                        return new MusicaDTO(musica);
                    })
                    .collect(Collectors.toList());

          return musicasDTO;

    }*/

    // Obter músicas de uma playlist
    public List<MusicaDTO> getMusicasFromPlaylist(Long playlistId) {
        // Recupera todas as musicas associadas à playlist
        List<MusicasPlaylist> musicasPlaylist = musicasPlaylistRepository.findByPlaylistId(playlistId);
        
        // Extrair os IDs das músicas associadas à playlist
        List<Long> musicaIds = musicasPlaylist.stream()
            .map(musicaPlaylist -> musicaPlaylist.getMusica().getId()).collect(Collectors.toList());
        
        // Buscar todas as músicas de uma vez só
        List<Musica> musicas = musicaRepository.findAllById(musicaIds);
        
        return musicas.stream().map(MusicaDTO::new).toList();
    }

    // Remover por ID
    public void delete(Long id) {
        if (!musicasPlaylistRepository.existsById(id)) {
			throw new EntityNotFoundException("MusicasPlaylist não encontrado com ID: " + id);
		}
		musicasPlaylistRepository.deleteById(id);
    }

    // Remover pela associação musica e playlist
    public void delete(Long musicaId, Long playlistId) {
        MusicasPlaylist musicasPlaylist = musicasPlaylistRepository.findByMusicaIdAndPlaylistId(musicaId, playlistId)
                .orElseThrow(() -> new EntityNotFoundException("A música com ID " + musicaId + " não está na playlist com ID " + playlistId));

        musicasPlaylistRepository.delete(musicasPlaylist);
    }

}
