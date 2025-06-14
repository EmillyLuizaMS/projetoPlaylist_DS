package com.cefet.playlist.dto;

import com.cefet.playlist.entities.Favorito;

public class FavoritoDTO {
    
    private Long id;
    private String nome_playlist;
    private Long id_usuario;

    public FavoritoDTO() {
    }

    public FavoritoDTO(Favorito favorito) {
        this.id = favorito.getId();
        this.nome_playlist = favorito.getPlaylist().getNome();
        this.id_usuario = favorito.getUsuario().getId();
    }

    public Long getId() {
        return id;
    }

    public String getNome_playlist() {
        return nome_playlist;
    }

    public Long getId_usuario() {
        return id_usuario;
    }

}
