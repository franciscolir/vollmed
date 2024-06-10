package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.direccion.DatosDireccion;
import med.voll.api.paciente.*;
import med.voll.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    public ResponseEntity<DatosRespuestaPaciente>registraPaceinte(@RequestBody @Valid DatosRegistroPaciente datos, UriComponentsBuilder uriComponentsBuilder){

        var paciente = repository.save(new Paciente(datos));
        DatosRespuestaPaciente datosRespuestaPaciente = new DatosRespuestaPaciente(paciente.getId(),
                paciente.getNombre(),
                paciente.getEmail(),
                paciente.getTelefono(),
                paciente.getDocumento(),
                new DatosDireccion(paciente.getDireccion().getCalle(),
                        paciente.getDireccion().getDistrito(),
                        paciente.getDireccion().getNumero(),
                        paciente.getDireccion().getCiudad(),
                        paciente.getDireccion().getComplemento()));

        URI url = uriComponentsBuilder.path("/paciente/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaPaciente);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoPacientes>> listaPaceinte(Pageable paginacion){
        return ResponseEntity.ok(repository.findAll(paginacion).map(DatosListadoPacientes ::new));
    }
    //actualiza datos de paciente especifico
    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaPaciente> actualizarDatos (@RequestBody @Valid DatosActualizacionPacientes datos){
        Paciente paciente = repository.getReferenceById(datos.id());
        paciente.actualizarInformacion(datos);
        return ResponseEntity.ok(new DatosRespuestaPaciente(paciente.getId(),
                paciente.getNombre(),
                paciente.getEmail(),
                paciente.getTelefono(),
                paciente.getDocumento(),
                new DatosDireccion(paciente.getDireccion().getCalle(),
                        paciente.getDireccion().getDistrito(),
                        paciente.getDireccion().getNumero(),
                        paciente.getDireccion().getCiudad(),
                        paciente.getDireccion().getComplemento())));
    }
    //desactiva paciente (delete logico)
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarMedico (@PathVariable Long id){
        Paciente paciente = repository.getReferenceById(id);
        paciente.desactivarPaciente();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaPaciente> retornarDatosPaciente (@PathVariable Long id){
        Paciente paciente = repository.getReferenceById(id);
        var datosPaciente = new DatosRespuestaPaciente(paciente.getId(),
                paciente.getNombre(),
                paciente.getEmail(),
                paciente.getTelefono(),
                paciente.getDocumento(),
                new DatosDireccion(paciente.getDireccion().getCalle(),
                        paciente.getDireccion().getDistrito(),
                        paciente.getDireccion().getNumero(),
                        paciente.getDireccion().getCiudad(),
                        paciente.getDireccion().getComplemento()));
        return ResponseEntity.ok(datosPaciente);
    }
}