package com.cefet.playlist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cefet.playlist.entities.Musica;

public interface MusicaRepository extends JpaRepository<Musica, Long> {
    
}
