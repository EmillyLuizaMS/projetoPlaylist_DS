package com.cefet.playlist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cefet.playlist.entities.MusicasPlaylist;

public interface MusicasPlaylistRepository extends JpaRepository<MusicasPlaylist, Long> {
    
}
