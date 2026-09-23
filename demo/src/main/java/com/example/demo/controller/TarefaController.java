package com.example.demo.controller;

import com.example.demo.model.Tarefa;
import com.example.demo.service.TarefaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {
    private final TarefaService service;
    public TarefaController(TarefaService service) {
        this.service = service;
    }
    @PostMapping
    public ResponseEntity<Tarefa> criar(@RequestBody Map<String, String>
                                                corpo) {
        System.out.println("[CONTROLLER] Requisição recebida: POST/tarefas");
                Tarefa tarefa = service.criar(corpo.get("titulo"));
        return ResponseEntity.ok(tarefa);
    }
    @GetMapping
    public ResponseEntity<List<Tarefa>> listar() {
        System.out.println("[CONTROLLER] Requisição recebida: GET /tarefas");
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/concluidas")
    public ResponseEntity<List<Tarefa>> listarConcluidas() {
        System.out.println("[CONTROLLER] Requisição recebida: GET /tarefas/concluidas");
        return ResponseEntity.ok(service.listarConcluidas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> buscar(@PathVariable Long id) {
        System.out.println("[CONTROLLER] Requisição recebida: GET /tarefas/" + id);
        return ResponseEntity.ok(service.buscarPorId(id));
    }
}