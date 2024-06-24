package med.voll.api.domain.consulta.validaciones;

import med.voll.api.domain.medico.Especialidad;

import java.time.LocalDateTime;

public interface IDatosConsulta {

    Long getIdPaciente();
    Long getIdMedico();
    LocalDateTime getFecha();
    Especialidad getEspecialidad();
}
