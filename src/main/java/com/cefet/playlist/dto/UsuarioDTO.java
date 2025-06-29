package com.cefet.playlist.dto;

import com.cefet.playlist.entities.NivelAcesso;
import com.cefet.playlist.entities.Usuario;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UsuarioDTO {

    private Long id;
    private String nome;
    private String email;
    private String login;
    //impede que a senha seja exposta
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;
    private NivelAcesso nivelAcesso;

    public UsuarioDTO() {}

    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.login = usuario.getLogin();
        this.senha = usuario.getSenha();
        this.nivelAcesso = usuario.getNivelAcesso();
    }

    public Long getId() {
        return id;
    }

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

}
