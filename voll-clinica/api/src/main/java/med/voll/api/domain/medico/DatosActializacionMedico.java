package med.voll.api.domain.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.direccion.DatosActializacionDrieccion;

public record DatosActializacionMedico(
        @NotNull
        Long id,
        String nombre,
        String documento,
        @Valid
        DatosActializacionDrieccion direccion
) {
}
