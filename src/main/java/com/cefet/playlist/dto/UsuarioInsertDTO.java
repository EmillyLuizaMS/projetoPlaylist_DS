package com.cefet.playlist.dto;

import com.cefet.playlist.entities.NivelAcesso;

public class UsuarioInsertDTO {
    private String nome;

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public NivelAcesso getNivelAcesso() {
        return nivelAcesso;
    }

    private String email;
    private String login;
    private String senha;
    private NivelAcesso nivelAcesso;
}
