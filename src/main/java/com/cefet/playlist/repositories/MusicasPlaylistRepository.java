package com.cefet.playlist.repositories;

import com.cefet.playlist.dto.MusicasPlaylistDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cefet.playlist.entities.MusicasPlaylist;

import java.util.List;

public interface MusicasPlaylistRepository extends JpaRepository<MusicasPlaylist, Long> {

    boolean contains(MusicasPlaylist musicasPlaylist);

    List<MusicasPlaylistDTO> findAllById(Long playlistId);
}
