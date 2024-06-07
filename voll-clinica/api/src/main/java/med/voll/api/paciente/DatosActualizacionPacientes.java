package med.voll.api.paciente;

import jakarta.validation.Valid;
import med.voll.api.direccion.DatosActializacionDrieccion;

public record DatosActualizacionPacientes(
        Long id,
        String nombre,
        String telefono,
        @Valid
        DatosActializacionDrieccion direccion
) {
}
