package med.voll.api.domain.medico;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.direccion.DatosDireccion;

public record DatosRegistroMedico(
        @NotBlank(message = "Nombre es obligatorio")
        String nombre,

        @NotBlank(message = "email es obligatorio")
        @Email(message = "Formato de email invalido")
        String email,

        @NotBlank(message = "Numero de documento es obligatorio")
        @Pattern(regexp = "\\d{9,10}",message = "Formato invalido. Debe contener de 9 a 10 digitos") //sin guion
       //@Pattern(regexp = "\\d{2}\\.?\\d{3}\\.?\\d{3}\\-?\\d{1}" con guion y puntos opcionales test
        String documento,

        @NotBlank (message = "Telefono es obligatorio")
        @Pattern(regexp = "\\d{9}",message = "Debe contener 9 digitos")//solo 9 digitos numericos
        String telefono,

        @NotNull (message = "Especilidad es obligatoria")
        Especialidad especialidad,

        @NotNull (message = "Datos de direccion son obligatorias")
        DatosDireccion direccion
) {
}
