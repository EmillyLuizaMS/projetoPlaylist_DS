package com.cefet.playlist.repositories;

import com.cefet.playlist.dto.MusicasPlaylistDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cefet.playlist.entities.Musica;
import com.cefet.playlist.entities.MusicasPlaylist;

import java.util.List;
import java.util.Optional;

public interface MusicasPlaylistRepository extends JpaRepository<MusicasPlaylist, Long> {

    boolean contains(MusicasPlaylist musicasPlaylist);

    //List<MusicasPlaylistDTO> findAllById(Long playlistId);
    List<MusicasPlaylist> findByPlaylistId(Long playlistId);

    //verificar se a música existe na playlist
    boolean existsByMusicaIdAndPlaylistId(Long musicatId, Long playlistId);

    Optional<MusicasPlaylist> findByMusicaIdAndPlaylistId(Long musicaId, Long playlistId);

    void deleteByPlaylistId(Long playlistId);

    // Buscar todas as associações da música
    List<MusicasPlaylist> findByMusica(Musica musica);
    
}
