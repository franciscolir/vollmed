package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.agendar.*;
import med.voll.api.domain.medico.DatosListadoMedicos;
import med.voll.api.domain.medico.Medico;
import med.voll.api.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@ResponseBody
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultaService service;
    @Autowired
    private ConsultarAgendamiento consultar;
    @Autowired
    private ConsultaRepository repository;


    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DatosAgendarConsulta datos) {
        var response = service.validar(datos);
        return ResponseEntity.ok(response);

    }
    //muestra todos los datos de 1 consulta
    @GetMapping("/{id}")
    public ResponseEntity consultarAgenda (@PathVariable Long id){
        Consulta consulta  =  consultar.consultarAgenda(id);

        return ResponseEntity.ok(new DatosDetalleConsulta(id, consulta.getPaciente().getId(),consulta.getMedico().getId(),consulta.getFecha()));
    }


    //reagenda consulta (cambia horario)
    @PutMapping
    @Transactional
    public ResponseEntity reagenda (@RequestBody @Valid DatosReagendaConsulta datos){
        System.out.println("antes de validar");
        Consulta consulta = consultar.consultarAgenda(datos.id());
        var validar = service.validar(datos);
        consulta.actualizarInformacion(validar);

       return ResponseEntity.ok(
               new DatosDetalleConsulta(
                       consulta.getId(),
                       consulta.getMedico().getId(),
                       consulta.getPaciente().getId(),
                       consulta.getFecha()));
    }
/*
    //elimina de la base de datos
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarMedico (@PathVariable Long id){
        Consulta consulta  =  consultar.consultarAgenda(id);
        repository.delete(consulta);
        return ResponseEntity.ok("consulta id: "+id+" eliminada") ;
    }*/

    //desactiva medico (delete logico)
@DeleteMapping("/{id}")
@Transactional
public ResponseEntity cancelarr (@PathVariable Long id){
    Consulta consulta = repository.getReferenceById(id);
    consulta.cancelarHora();
    return ResponseEntity.noContent().build();
}
}