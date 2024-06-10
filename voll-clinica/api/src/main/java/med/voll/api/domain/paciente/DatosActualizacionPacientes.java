package med.voll.api.domain.paciente;

import jakarta.validation.Valid;
import med.voll.api.domain.direccion.DatosActializacionDrieccion;

public record DatosActualizacionPacientes(
        Long id,
        String nombre,
        String telefono,
        @Valid
        DatosActializacionDrieccion direccion
) {
}
