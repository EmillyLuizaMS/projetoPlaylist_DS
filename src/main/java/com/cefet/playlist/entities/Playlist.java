package com.cefet.playlist.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_playlist")
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "nome")
    private String nome;

	@Column(name = "visibilidade", nullable = false)
	private boolean visibilidade;	// true-publica, false-privada

	@Column(name = "nota_media")
	private Float notaMedia;

	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false)
	private Usuario usuario;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Playlist() {
	}

	public Playlist(Long id, String nome, boolean visibilidade,  Float notaMedia) {
		this.id = id;
		this.nome = nome;
		this.visibilidade = visibilidade;
		this.notaMedia = notaMedia;
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

	public Float getNotaMedia() {
		return notaMedia;
	}

	public void setNotaMedia(Float nota_media) {
		this.notaMedia = nota_media;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + (visibilidade ? 1231 : 1237);
		result = prime * result + ((notaMedia == null) ? 0 : notaMedia.hashCode());
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
		if (notaMedia == null) {
			if (other.notaMedia != null)
				return false;
		} else if (!notaMedia.equals(other.notaMedia))
			return false;
		return true;
	}

	
    
}
