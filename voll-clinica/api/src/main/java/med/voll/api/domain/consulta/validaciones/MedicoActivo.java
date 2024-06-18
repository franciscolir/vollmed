package med.voll.api.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.DatosAgendarConsulta;
import med.voll.api.repository.MedicoRepository;

public class MedicoActivo {

    private MedicoRepository medicoRepository;

    public void validar(DatosAgendarConsulta datos){

        if(datos.idMedico()==null){
            return;
        }
        var medicoActivo = medicoRepository.findAllActivoById(datos.idPaciente());

        if (!medicoActivo){
            throw new ValidationException("No se puede agendar citas con medico inactivos en sistema");
        }
    }
}


