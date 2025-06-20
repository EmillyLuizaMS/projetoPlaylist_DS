package com.cefet.playlist.dto;

import com.cefet.playlist.entities.MusicasPlaylist;

public class MusicasPlaylistDTO {

    private Long id;
    private Long id_playlist;
    private Long id_musica;

    public MusicasPlaylistDTO() {
    }

    public MusicasPlaylistDTO(MusicasPlaylist musicaPlaylist) {
        this.id = musicaPlaylist.getId();
        this.id_playlist = musicaPlaylist.getPlaylist().getId();
        this.id_musica = musicaPlaylist.getMusica().getId();
    }

    public Long getId() {
        return id;
    }

    public Long getId_playlist() {
        return id_playlist;
    }

    public Long getId_musica() {
        return id_musica;
    }
    
}
