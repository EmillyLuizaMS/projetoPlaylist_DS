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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + nota;
        result = prime * result + ((comentario == null) ? 0 : comentario.hashCode());
        result = prime * result + ((data == null) ? 0 : data.hashCode());
        result = prime * result + ((id_playlist == null) ? 0 : id_playlist.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ComentarioDTO other = (ComentarioDTO) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (nota != other.nota)
            return false;
        if (comentario == null) {
            if (other.comentario != null)
                return false;
        } else if (!comentario.equals(other.comentario))
            return false;
        if (data == null) {
            if (other.data != null)
                return false;
        } else if (!data.equals(other.data))
            return false;
        if (id_playlist == null) {
            if (other.id_playlist != null)
                return false;
        } else if (!id_playlist.equals(other.id_playlist))
            return false;
        return true;
    }

    

}
