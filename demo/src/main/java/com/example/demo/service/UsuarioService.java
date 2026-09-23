package com.example.demo.service;

import com.example.demo.dto.UsuarioRequestDTO;
import com.example.demo.dto.UsuarioResponseDTO;
import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public UsuarioResponseDTO criar(UsuarioRequestDTO dto) {
        Usuario usuario = new Usuario(
                null,
                dto.nome(),
                dto.email(),
                dto.cargo()
        );

        Usuario salvo = repository.salvar(usuario);

        return converterParaResponse(salvo);
    }

    public List<UsuarioResponseDTO> listarTodos() {
        return repository.listarTodos()
                .stream()
                .map(this::converterParaResponse)
                .toList();
    }

    public UsuarioResponseDTO buscarPorId(Long id) {
        Usuario usuario = repository.buscarPorId(id)
                .orElseThrow(() ->
                        new RuntimeException("Usuário não encontrado")
                );

        return converterParaResponse(usuario);
    }

    public UsuarioResponseDTO atualizar(Long id, UsuarioRequestDTO dto) {
        Usuario usuario = repository.buscarPorId(id)
                .orElseThrow(() ->
                        new RuntimeException("Usuário não encontrado")
                );

        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setCargo(dto.cargo());

        Usuario atualizado = repository.atualizar(usuario);

        return converterParaResponse(atualizado);
    }

    public void remover(Long id) {
        boolean removido = repository.remover(id);

        if (!removido) {
            throw new RuntimeException("Usuário não encontrado");
        }
    }

    private UsuarioResponseDTO converterParaResponse(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getCargo()
        );
    }
}