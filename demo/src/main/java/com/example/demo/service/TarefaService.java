package com.example.demo.service;

import com.example.demo.model.Tarefa;
import com.example.demo.repository.TarefaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarefaService {

    private final TarefaRepository repository;

    public TarefaService(TarefaRepository repository) {
        this.repository = repository;
    }

    public Tarefa criar(String titulo) {
        System.out.println("[SERVICE] Validando regra de negócio para: " +
                titulo);

        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException(
                    "O título da tarefa não pode ser vazio."
            );
        }

        return repository.salvar(titulo.trim());
    }

    public List<Tarefa> listar() {
        System.out.println("[SERVICE] Solicitando lista de tarefas ao repository");

        return repository.listarTodas();
    }

    public List<Tarefa> listarConcluidas() {
        System.out.println("[SERVICE] Solicitando tarefas concluídas ao repository");

        return repository.listarConcluidas();
    }

    public Tarefa buscarPorId(Long id) {
        System.out.println("[SERVICE] Processando busca por id: " + id);

        return repository.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Tarefa não encontrada: " + id
                ));
    }
}