package com.cefet.playlist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cefet.playlist.dto.UsuarioDTO;
import com.cefet.playlist.entities.Usuario;
import com.cefet.playlist.repositories.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
        // Verifica login
        if (usuarioRepository.existsByLogin(dto.getLogin())) {
            throw new IllegalArgumentException("Login já está em uso.");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setLogin(dto.getLogin());
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        usuario.setNivelAcesso(dto.getNivelAcesso());

        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return new UsuarioDTO(usuarioSalvo);
    }

    //Atualizar Usuário
    public UsuarioDTO update(Long id, UsuarioDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID: " + id));

        // Não permite alterar login
        if (dto.getLogin() != null && !dto.getLogin().equals(usuario.getLogin())) {
            throw new IllegalArgumentException("Alteração de login não permitida");
        }
        
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());

        //if (dto.getNivelAcesso() == NivelAcesso.ADMIN) {
        //    usuario.setNivelAcesso(dto.getNivelAcesso());
        //}

        return new UsuarioDTO(usuarioRepository.save(usuario));
    }

    // Remover por ID
    public void delete(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new EntityNotFoundException("Usuário não encontrado com ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }

    // Verifica login   
    public boolean existsByLogin(String login) {
        return usuarioRepository.existsByLogin(login);
    }

}
