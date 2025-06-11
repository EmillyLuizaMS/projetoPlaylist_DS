package com.cefet.playlist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cefet.playlist.entities.Favorito;

public interface FavoritoRepository extends JpaRepository<Favorito, Long> {
    
}
