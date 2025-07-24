package com.cefet.playlist.dto;

public class JwtAuthenticationDTO {

	private String accessToken;
    private String tokenType = "Bearer";
    private Long userId; // <-- CAMPO ADICIONADO


    public JwtAuthenticationDTO() {
    }

    public JwtAuthenticationDTO(String accessToken,  Long userId) {
        this.accessToken = accessToken;
        this.userId = userId;
    }
    
    public String getAccessToken() {
        return accessToken;
    }
    
    public String getTokenType() {
        return tokenType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
