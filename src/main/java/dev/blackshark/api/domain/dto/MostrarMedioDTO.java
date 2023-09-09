package dev.blackshark.api.domain.dto;

import dev.blackshark.api.persistance.entity.Especialidad;

public record MostrarMedioDTO(
        String nombre,
        Especialidad especialidad,
        String dni,
        String email) {
}
