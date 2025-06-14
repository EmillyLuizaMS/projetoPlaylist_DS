package com.cefet.playlist.dto;

import com.cefet.playlist.entities.NivelAcesso;
import com.cefet.playlist.entities.Usuario;

public class UsuarioDTO {
    
    private Long id;
    private String nome;
    private String email;
    private String login;
    private String senha;
    private NivelAcesso nivel_acesso;
    
    public UsuarioDTO() {
    }

    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.login = usuario.getLogin();
        this.senha = usuario.getSenha();
        this.nivel_acesso = usuario.getNivel_acesso();
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

    public NivelAcesso nivel_acesso() {
        return nivel_acesso;
    }
}
