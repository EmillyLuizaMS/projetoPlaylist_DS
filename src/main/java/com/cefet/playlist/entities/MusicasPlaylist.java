package com.cefet.playlist.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_musicas_playlist")
public class MusicasPlaylist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_playlist", nullable = false)
    private Playlist playlist;

    @ManyToOne
    @JoinColumn(name = "id_musica", nullable = false)
    private Musica musica;

    public MusicasPlaylist() {
    }

    public MusicasPlaylist(Long id, Playlist playlist, Musica musica) {
        this.id = id;
        this.playlist = playlist;
        this.musica = musica;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    public Musica getMusica() {
        return musica;
    }

    public void setMusica(Musica musica) {
        this.musica = musica;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((playlist == null) ? 0 : playlist.hashCode());
        result = prime * result + ((musica == null) ? 0 : musica.hashCode());
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
        MusicasPlaylist other = (MusicasPlaylist) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (playlist == null) {
            if (other.playlist != null)
                return false;
        } else if (!playlist.equals(other.playlist))
            return false;
        if (musica == null) {
            if (other.musica != null)
                return false;
        } else if (!musica.equals(other.musica))
            return false;
        return true;
    }
    
    
}
