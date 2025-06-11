package com.cefet.playlist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cefet.playlist.entities.Playlist;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    
}
