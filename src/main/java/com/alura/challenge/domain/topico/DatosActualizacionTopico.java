package com.alura.challenge.domain.topico;

import jakarta.validation.constraints.NotNull;

public record DatosActualizacionTopico(
        @NotNull
        Long id,
        String titulo,
        String mensaje,
        String status,
        String curso
) {
}
