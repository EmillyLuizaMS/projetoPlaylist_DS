package com.cefet.playlist.dto;

import com.cefet.playlist.entities.Musica;

public class MusicaDTO {
    
    private Long id;
    private String nome;
    private Long id_artista;
    private String tags;
	private String url_musica;

	public MusicaDTO() {
	}

    public MusicaDTO(Musica musica) {
        this.id = musica.getId();
        this.id_artista = musica.getArtista().getId();
        this.tags = musica.getTags();
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

	public String getTags() {
		return tags;
	}

	public String getUrl_musica() {
		return url_musica;
	}

}
