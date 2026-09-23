package com.example.demo.repository;

import com.example.demo.model.Usuario;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class InMemoryUsuarioRepository implements UsuarioRepository {

    private final Map<Long, Usuario> usuarios = new HashMap<>();
    private Long proximoId = 1L;

    @Override
    public Usuario salvar(Usuario usuario) {
        Usuario usuarioComId = new Usuario(
                proximoId,
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getCargo()
        );

        usuarios.put(proximoId, usuarioComId);
        proximoId++;

        return usuarioComId;
    }

    @Override
    public List<Usuario> listarTodos() {
        return new ArrayList<>(usuarios.values());
    }

    @Override
    public Optional<Usuario> buscarPorId(Long id) {
        return Optional.ofNullable(usuarios.get(id));
    }

    @Override
    public Usuario atualizar(Usuario usuario) {
        usuarios.put(usuario.getId(), usuario);
        return usuario;
    }

    @Override
    public boolean remover(Long id) {
        return usuarios.remove(id) != null;
    }
}