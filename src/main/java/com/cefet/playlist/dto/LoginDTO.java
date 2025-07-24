package com.cefet.playlist.dto;

public class LoginDTO {

	private String login;
    private String senha;
    private Long idUsuario;
    
    public LoginDTO() {
    }

    public LoginDTO(String login, String senha, Long idUsuario) {
        this.login = login;
        this.senha = senha;
        this.idUsuario = idUsuario;
    }
    
    public String getLogin() {
        return login;
    }
    
    public String getSenha() {
        return senha;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }
}
