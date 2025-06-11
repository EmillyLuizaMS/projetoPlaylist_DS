package com.cefet.playlist.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_playlist")
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nome;

	@Column(nullable = false)
	private boolean visibilidade;	// true-publica, false-privada

	@Column(nullable = false)
	private Float nota_media;

    public Playlist() {
	}

	public Playlist(Long id, String nome, boolean visibilidade,  Float nota_media) {
		this.id = id;
		this.nome = nome;
		this.visibilidade = visibilidade;
		this.nota_media = nota_media;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isVisibilidade() {
		return visibilidade;
	}

	public void setVisibilidade(boolean visibilidade) {
		this.visibilidade = visibilidade;
	}

	public Float getNota_media() {
		return nota_media;
	}

	public void setNota_media(Float nota_media) {
		this.nota_media = nota_media;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + (visibilidade ? 1231 : 1237);
		result = prime * result + ((nota_media == null) ? 0 : nota_media.hashCode());
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
		Playlist other = (Playlist) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (visibilidade != other.visibilidade)
			return false;
		if (nota_media == null) {
			if (other.nota_media != null)
				return false;
		} else if (!nota_media.equals(other.nota_media))
			return false;
		return true;
	}

	
    
}
