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

    /*
    //solucion de cancelamiento intructor
    public void cancelar(DatosCancelamientoConsulta datos)
    if(!consultaRepository.existById(datos.idConsulta())
    throw new ValidacionDeIntegridad("id de la consulta informada no existe");
      validadoresCancelamiento.forEach(v-> v.validar(datos));
      var consulta = consultaRepository.getReferenceById(datos.IdConsulta())
      consulta.cancelar(datos.motivo())

      se debe validar que se puede candelar con 24hrs minimo de antelacion
    * */

    //OBS:   relizar metodo par aingresar motivo de cancelamineto de una lista de motivos.es una propiedad de Call Consulta. lista de ENUM
    //      en cosntructpoers de otros metodo doncde se llame a consulta de debe declsarara las propiedaddes como nula si no corresponde al proceso espécifico. si se esta agendando, el motivo de cancelamineto sera null
    //      endpiont para obtener todas las consultas. se obytiene y por medio de paginacion se filtra por cantidad. metodo GEt findAll


}