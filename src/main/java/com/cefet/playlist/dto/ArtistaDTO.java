package com.cefet.playlist.dto;

import com.cefet.playlist.entities.Artista;

public class ArtistaDTO {
    
    private Long id;
    private String nome;
    private String descricao;

     public ArtistaDTO() {
    }

    public ArtistaDTO(Artista artista) {
        this.id = artista.getId();
        this.nome = artista.getNome();
        this.descricao = artista.getDescricao();
    }

    public Long getId() {
        return id;
    }

     public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

}
