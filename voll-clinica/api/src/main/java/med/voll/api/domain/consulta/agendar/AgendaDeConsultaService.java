package med.voll.api.domain.consulta.agendar;

import med.voll.api.domain.consulta.validaciones.IDatosConsulta;
import med.voll.api.infra.errores.ValidacionDeIntegridad;
import med.voll.api.domain.consulta.validaciones.IValidadorDeConsultas;
import med.voll.api.domain.medico.Medico;
import med.voll.api.repository.ConsultaRepository;
import med.voll.api.repository.MedicoRepository;
import med.voll.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsultaService implements IValidadorDeConsultas{

    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    List<IValidadorDeConsultas> validadores;



    public DatosDetalleConsulta validar(IDatosConsulta datos){

        if(!pacienteRepository.findById(datos.getIdPaciente()).isPresent()){
            throw new ValidacionDeIntegridad("este id para el paciente no fue encontrado");
        }
        if(datos.getIdMedico()!=null && !medicoRepository.existsById(datos.getIdMedico())){
            throw new ValidacionDeIntegridad("este id para el medico no fue encontrado");
        }

        validadores.forEach(v-> v.validar(datos));

        var paciente = pacienteRepository.findById(datos.getIdPaciente()).get();
        var medico = seleccionarMedico(datos);

        if(medico==null){
            throw new ValidacionDeIntegridad("no existen medicos disponibles para este horario y especialidad");
        }
       // var consulta = new Consulta(null,medico.getId(),paciente.getId(),datos.getFecha());
        var consulta = new Consulta(null,medico,paciente,datos.getFecha(),true);
        consultaRepository.save(consulta);

        return new DatosDetalleConsulta(consulta);
    }

    private Medico seleccionarMedico(IDatosConsulta datos) {
        if(datos.getIdMedico()!=null){
            return medicoRepository.getReferenceById(datos.getIdMedico());

        }
        if(datos.getEspecialidad()==null){
            throw new ValidacionDeIntegridad("debe seleccionarse una especialidad para el medico");
        }

        return medicoRepository.seleccionarMedicoConEspecialidadEnFecha(datos.getEspecialidad(),datos.getFecha());
    }


}