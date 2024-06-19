package med.voll.api.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.DatosAgendarConsulta;
import med.voll.api.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicoConConsulta  implements ValidadorDeConsultas{
    @Autowired
    private ConsultaRepository repository;

    public void validar (DatosAgendarConsulta datos){

        if (datos.idMedico()==null)
            return;

        var medicoConConsulta = repository.existsByMedicoIdAndFecha(datos.idMedico(),datos.fecha());

        if(medicoConConsulta){
            throw new ValidationException("Medico ya tiene consulta agendada en ese horario");
        }
    }
}
