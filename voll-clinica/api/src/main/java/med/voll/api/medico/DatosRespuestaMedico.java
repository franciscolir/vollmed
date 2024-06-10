package med.voll.api.medico;

import jakarta.validation.Valid;
import med.voll.api.direccion.DatosActializacionDrieccion;
import med.voll.api.direccion.DatosDireccion;

public record DatosRespuestaMedico(Long id,
                                   String nombre,
                                   String email,
                                   String telefono,
                                   String documento,
                                   DatosDireccion direccion) {
}
