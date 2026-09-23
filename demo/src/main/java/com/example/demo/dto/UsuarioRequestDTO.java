package com.example.demo.dto;

public record UsuarioRequestDTO(
        String nome,
        String email,
        String cargo
) {
}