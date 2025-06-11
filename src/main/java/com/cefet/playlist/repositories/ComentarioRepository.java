package com.cefet.playlist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cefet.playlist.entities.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    
}
