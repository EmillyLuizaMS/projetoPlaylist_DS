package com.cefet.playlist.dto;

import java.util.Date;

import com.cefet.playlist.entities.Comentario;

public class ComentarioDTO {
    
    private Long id;
    private int nota;
    private String comentario;
    private Date data;
    private Long id_playlist;

	public ComentarioDTO() {
	}

    public ComentarioDTO(Comentario comentario) {
        this.id = comentario.getId();
        this.nota = comentario.getNota();
        this.comentario = comentario.getComentario();
        this.data = comentario.getData();
        this.id_playlist = comentario.getPlaylist().getId();
	}

    public Long getId() {
        return id;
    }

    public int getNota() {
        return nota;
    }

    public String getComentario() {
        return comentario;
    }

    public Date getData() {
        return data;
    }

    public Long getId_playlist() {
        return id_playlist;
    }


}
