package com.cefet.playlist.dto;

import com.cefet.playlist.entities.Musica;

public class MusicaDTO {
    
    private Long id;
    private String nome;
    private Long id_artista;
    private String album;
    private String genero;
    private int tempo_total;
	private String url_musica;

	public MusicaDTO() {
	}

    public MusicaDTO(Musica musica) {
        this.id = musica.getId();
        this.id_artista = musica.getArtista().getId();
        this.album = musica.getAlbum();
        this.genero = musica.getAlbum();
        this.tempo_total = musica.getTempo_total();
		this.url_musica = musica.getUrl_musica();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Long getId_artista() {
		return id_artista;
	}

	public String getAlbum() {
		return album;
	}

	public String getGenero() {
		return genero;
	}

	public int getTempo_total() {
		return tempo_total;
	}

	public String getUrl_musica() {
		return url_musica;
	}

}
