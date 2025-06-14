package com.cefet.playlist.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cefet.playlist.dto.UsuarioDTO;
import com.cefet.playlist.entities.Usuario;
import com.cefet.playlist.repositories.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UsuarioService {
    
    private UsuarioRepository usuarioRepository;

    // Buscar todos
	public List<UsuarioDTO> findAll() {
		List<Usuario> usuarios = usuarioRepository.findAll();
		return usuarios.stream().map(UsuarioDTO::new).toList();
	}

	// Buscar por ID
	public UsuarioDTO findById(Long id) {
		Usuario usuario = usuarioRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Usuarios não encontrada com ID: " + id));
		return new UsuarioDTO(usuario);
	}

    // Inserir Usuario
     public UsuarioDTO insert(UsuarioDTO dto) {
        // Verifica se já existe login
        if (usuarioRepository.existsByLogin(dto.getLogin())){
            throw new IllegalArgumentException("Login já está em uso.");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setLogin(dto.getLogin());
        usuario.setSenha(dto.getSenha());
        usuario.setNivel_acesso(dto.nivel_acesso());        // ????????

        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return new UsuarioDTO(usuarioSalvo);
     }

     //Atualizar Usuário
    public UsuarioDTO update(Long id, UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID: " + id));

        // Garante que o login não seja alterado, caso seja informado
        //if (!usuario.getLogin().equals(usuarioDTO.getLogin()) && usuarioDTO.getLogin() != null) {
        //    throw new IllegalArgumentException("Não é permitido alterar o login.");
        //}

        usuario.setNome(usuarioDTO.getNome());
        Usuario usuarioAtualizado = usuarioRepository.save(usuario);

        return new UsuarioDTO(usuarioAtualizado);
    }

    // Remover por ID
    public void delete(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new EntityNotFoundException("Usuário não encontrado com ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }

    // Verificar se login existe
    public boolean existsByLogin(String login){
        return usuarioRepository.existsByLogin(login);
    }
    
}
