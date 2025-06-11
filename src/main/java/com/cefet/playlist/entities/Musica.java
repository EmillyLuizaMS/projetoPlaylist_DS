package com.cefet.playlist.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_musica")
public class Musica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_artista", nullable = false)
    private Artista artista;

    @Column(nullable = false)
    private String album;

    @Column(nullable = false)
    private String genero;

    @Column(nullable = false)
    private int tempo_total;

	@Column(nullable = false)
	private String url_musica;

	public Musica() {
	}

	public Musica(Long id, String nome, Artista artista, String album, String genero, int tempo_total, String url_musica) {
		this.id = id;
		this.nome = nome;
		this.artista = artista;
		this.album = album;
		this.genero = genero;
		this.tempo_total = tempo_total;
		this.url_musica = url_musica;
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

	public Artista getArtista() {
		return artista;
	}

	public void setArtista(Artista artista) {
		this.artista = artista;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public int getTempo_total() {
		return tempo_total;
	}

	public void setTempo_total(int tempo_total) {
		this.tempo_total = tempo_total;
	}

	public String getUrl_musica() {
		return url_musica;
	}

	public void setUrl_musica(String url_musica) {
		this.url_musica = url_musica;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((artista == null) ? 0 : artista.hashCode());
		result = prime * result + ((album == null) ? 0 : album.hashCode());
		result = prime * result + ((genero == null) ? 0 : genero.hashCode());
		result = prime * result + tempo_total;
		result = prime * result + ((url_musica == null) ? 0 : url_musica.hashCode());
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
		Musica other = (Musica) obj;
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
		if (artista == null) {
			if (other.artista != null)
				return false;
		} else if (!artista.equals(other.artista))
			return false;
		if (album == null) {
			if (other.album != null)
				return false;
		} else if (!album.equals(other.album))
			return false;
		if (genero == null) {
			if (other.genero != null)
				return false;
		} else if (!genero.equals(other.genero))
			return false;
		if (tempo_total != other.tempo_total)
			return false;
		if (url_musica == null) {
			if (other.url_musica != null)
				return false;
		} else if (!url_musica.equals(other.url_musica))
			return false;
		return true;
	}
	
    
}
