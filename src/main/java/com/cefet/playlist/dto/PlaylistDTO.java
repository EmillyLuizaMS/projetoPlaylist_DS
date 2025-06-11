package com.cefet.playlist.dto;

import com.cefet.playlist.entities.Playlist;

public class PlaylistDTO {
    
    private Long id;
    private String nome;
    private int visibilidade;

     public PlaylistDTO() {
    }

    public PlaylistDTO(Playlist playlist) {
        this.id = playlist.getId();
        this.nome = playlist.getNome();
        this.visibilidade = playlist.getVisibilidade();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getVisibilidade() {
        return visibilidade;
    }

}
