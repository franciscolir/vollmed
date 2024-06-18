package med.voll.api.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.DatosAgendarConsulta;
import med.voll.api.repository.PacienteRepository;

public class PacienteActivo {

    private PacienteRepository pacienteRepository;

    public void validar(DatosAgendarConsulta datos){

        if(datos.idPaciente()==null){
            return;
        }
        var pacienteActivo = pacienteRepository.findAllActivoById(datos.idPaciente());

    if (!pacienteActivo){
        throw new ValidationException("No se puede agendar citas a pacientes inactivos en sistema");
    }
    }
}
