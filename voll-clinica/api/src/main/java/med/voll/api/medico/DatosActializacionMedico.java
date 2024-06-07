package med.voll.api.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.api.direccion.DatosActializacionDrieccion;

public record DatosActializacionMedico(
        @NotNull
        Long id,
        String nombre,
        String documento,
        @Valid
        DatosActializacionDrieccion direccion
) {
}
