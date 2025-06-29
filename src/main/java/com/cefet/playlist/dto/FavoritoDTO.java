package com.cefet.playlist.dto;

import com.cefet.playlist.entities.Favorito;

public class FavoritoDTO {
    
    private Long id;
    private Long id_playlist;
    private Long id_usuario;

    public FavoritoDTO() {
    }

    public FavoritoDTO(Favorito favorito) {
        this.id = favorito.getId();
        this.id_playlist = favorito.getPlaylist().getId();
        this.id_usuario = favorito.getUsuario().getId();
    }

    public Long getId() {
        return id;
    }

    public Long getId_playlist() {
        return id_playlist;
    }

    public Long getId_usuario() {
        return id_usuario;
    }

}
