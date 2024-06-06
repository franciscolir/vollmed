package med.voll.api.medico;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.direccion.DatosDireccion;

public record DatosRegistroMedico(
        @NotBlank
        String nombre,
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Pattern(regexp = "\\d{9,10}") //sin guion
       //@Pattern(regexp = "\\d{2}\\.?\\d{3}\\.?\\d{3}\\-?\\d{1}" con guion y puntos opcionales test
        String documento,
        @NotBlank
        @Pattern(regexp = "\\d{9}")//solo 9 digitos numericos
        String telefono,
        @NotNull
        Especialidad especialidad,
        @NotNull
        DatosDireccion direccion
) {
}
