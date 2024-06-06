package med.voll.api.medico;

import med.voll.api.paciente.Paciente;

public record DatosListadoPacientes(
        String nombre,
        String email,
        String documento) {

    public DatosListadoPacientes(Paciente paciente) {
        this (paciente.getNombre(),paciente.getEmail(), paciente.getDocumento());
    }
}
