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
	}

}
