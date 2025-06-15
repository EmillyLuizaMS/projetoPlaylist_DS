package com.cefet.playlist.dto;

import com.cefet.playlist.entities.Playlist;

public class PlaylistDTO {
    
    private Long id;
    private String nome;
    private boolean visibilidade;
    private Float nota_media;
    private Long userID;



    public PlaylistDTO() {
    }

    public PlaylistDTO(Playlist playlist) {
        this.id = playlist.getId();
        this.nome = playlist.getNome();
        this.visibilidade = playlist.isVisibilidade();
        this.nota_media = playlist.getNotaMedia();
        this.userID = playlist.getUsuario().getId();

    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public boolean getVisibilidade() {
        return visibilidade;
    }

    public Float getNota_media(){
        return nota_media;
    }

    public Long getUserID() {
        return userID;
    }



}
