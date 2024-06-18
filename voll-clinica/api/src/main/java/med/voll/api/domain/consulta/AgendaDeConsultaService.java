package med.voll.api.domain.consulta;

import med.voll.api.domain.ValidacionDeIntegridad;
import med.voll.api.domain.medico.Medico;
import med.voll.api.repository.ConsultaRepository;
import med.voll.api.repository.MedicoRepository;
import med.voll.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendaDeConsultaService {

    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private ConsultaRepository consultaRepository;




    public void agendar(DatosAgendarConsulta datos){
        if(pacienteRepository.findById(datos.idPaciente()).isPresent()){
            System.out.println("paciente si existe");
        }
        else {
            throw new ValidacionDeIntegridad("este id para el paciente no fue encontrado");
        }
        if( datos.idMedico()!=null && medicoRepository.existsById(datos.idMedico())){
            System.out.println("medico si existe");
        }
        else {
            throw new ValidacionDeIntegridad("este id para el medico no fue encontrado");
        }

        //validaciones
        var paciente = pacienteRepository.findById(datos.idPaciente()).get();
        var medico = seleccionarMedico(datos);
        var consulta = new Consulta(null,medico,paciente,datos.fecha());
        consultaRepository.save(consulta);
    }

    private Medico seleccionarMedico(DatosAgendarConsulta datos) {
        if(datos.idMedico()!=null){
            System.out.println("mensaje agregado");
            return medicoRepository.getReferenceById(datos.idMedico());

        }
        if(datos.especialidad()==null){
            throw new ValidacionDeIntegridad("debe seleccionarse una especialidad para el medico");
        }

        return medicoRepository.seleccionarMedicoConEspecialidadEnFecha(datos.especialidad(),datos.fecha());
    }
}