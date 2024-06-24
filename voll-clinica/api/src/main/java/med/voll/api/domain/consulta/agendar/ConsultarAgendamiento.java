package med.voll.api.domain.consulta.agendar;

import med.voll.api.infra.errores.ValidacionDeIntegridad;
import med.voll.api.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultarAgendamiento {

        @Autowired
        private ConsultaRepository consultaRepository;

        public Consulta consultarAgenda(Long id) {

            if (!consultaRepository.findAllActivoById(id)) {
                throw new ValidacionDeIntegridad("este id para consulta no fue encontrado");
            } else {
                var consutlaId = consultaRepository.getReferenceById(id);
                return consutlaId;
            }
        }

    public Consulta consultarAgendaHistorica(Long id) {

        if (!consultaRepository.findAllActivoById(id)) {
            throw new ValidacionDeIntegridad("este id para consulta no fue encontrado");
        } else {
            var consutlaId = consultaRepository.getReferenceByPacienteId(id);
            return consutlaId;
        }
    }


}
