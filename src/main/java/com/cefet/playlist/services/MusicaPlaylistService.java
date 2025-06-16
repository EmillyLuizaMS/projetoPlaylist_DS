package com.cefet.playlist.services;

import com.cefet.playlist.dto.MusicaDTO;
import com.cefet.playlist.dto.MusicasPlaylistDTO;
import com.cefet.playlist.entities.Musica;
import com.cefet.playlist.entities.MusicasPlaylist;
import com.cefet.playlist.entities.Playlist;
import com.cefet.playlist.repositories.MusicaRepository;
import com.cefet.playlist.repositories.MusicasPlaylistRepository;
import com.cefet.playlist.repositories.PlaylistRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MusicaPlaylistService {

    @Autowired
    MusicasPlaylistRepository musicasPlaylistRepository;
    @Autowired
    MusicaRepository musicaRepository;

    @Autowired
    PlaylistRepository playlistRepository;


    public MusicasPlaylistDTO insert(MusicasPlaylistDTO musicasPlaylistDTO) {
        MusicasPlaylist musicasPlaylist = new MusicasPlaylist();

        Musica musica =   musicaRepository.findById(musicasPlaylistDTO.getId_musica()).orElseThrow(
                () -> new EntityNotFoundException("Musica nao encontrada")
        );
        musicasPlaylist.setMusica(musica);

        Playlist playlist = playlistRepository.findById(musicasPlaylistDTO.getMusicaplaylist()).orElseThrow(
                () -> new EntityNotFoundException("Playlist nao encontrada")
        );
        musicasPlaylist.setPlaylist(playlist);


        MusicasPlaylist playlist1 = musicasPlaylistRepository.save(musicasPlaylist);
        return new MusicasPlaylistDTO(playlist1);

    }

    public List<MusicaDTO> getMusicasFromPlaylist(Long playlistId) {
          List<MusicasPlaylistDTO> musicasPlaylistDTOList = musicasPlaylistRepository.findAllById(playlistId).stream().toList();

          List<MusicaDTO> musicasDTO = musicasPlaylistDTOList.stream()
                    .map(musicaPlaylist -> {
                        Musica musica = musicaRepository.findById(musicaPlaylist.getId_musica()).orElseThrow(
                                () -> new EntityNotFoundException("Musica nao encontrada")
                        );
                        return new MusicaDTO(musica);
                    })
                    .collect(Collectors.toList());

          return musicasDTO;


    }

}
