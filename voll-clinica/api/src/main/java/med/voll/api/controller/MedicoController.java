package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.direccion.DatosDireccion;
import med.voll.api.medico.*;
import med.voll.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    public ResponseEntity<DatosRespuestaMedico>  registra(@RequestBody @Valid DatosRegistroMedico datos,
                                    UriComponentsBuilder uriComponentsBuilder){

        var medico = repository.save(new Medico(datos));
        DatosRespuestaMedico datosRespuestaMedico = new DatosRespuestaMedico(medico.getId(),
                medico.getNombre(),
                medico.getEmail(),
                medico.getTelefono(),
                medico.getEspecialidad().toString(),
                new DatosDireccion(medico.getDireccion().getCalle(),
                        medico.getDireccion().getDistrito(),
                        medico.getDireccion().getNumero(),
                        medico.getDireccion().getCiudad(),
                        medico.getDireccion().getComplemento()));

        URI url = uriComponentsBuilder.path("/medico/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaMedico);
    }


    //muestra lista de medicos
    @GetMapping
    public ResponseEntity<Page<DatosListadoMedicos>> listaMedico(Pageable paginacion){
        //return repository.findAll(paginacion).map(DatosListadoMedicos::new);
        return ResponseEntity.ok(repository.findByActivoTrue(paginacion).map(DatosListadoMedicos::new));
    }

    //actualiza datos de medico especifico
    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaMedico> actualiza (@RequestBody @Valid DatosActializacionMedico datos){
        Medico medico = repository.getReferenceById(datos.id());
        medico.actualizarInformacion(datos);
        return ResponseEntity.ok(new DatosRespuestaMedico(medico.getId(),
                medico.getNombre(),
                medico.getEmail(),
                medico.getTelefono(),
                medico.getEspecialidad().toString(),
                new DatosDireccion(medico.getDireccion().getCalle(),
                        medico.getDireccion().getDistrito(),
                        medico.getDireccion().getNumero(),
                        medico.getDireccion().getCiudad(),
                        medico.getDireccion().getComplemento())));
    }

    //desactiva medico (delete logico)
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminar (@PathVariable Long id){
        Medico medico = repository.getReferenceById(id);
        medico.desactivarMedico();
        return ResponseEntity.noContent().build();
    }

    //muestra todos los datos de 1 solo medico
    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaMedico> retornaDatosMedico (@PathVariable Long id){
        Medico medico = repository.getReferenceById(id);
        var datosMedico = new DatosRespuestaMedico(medico.getId(),
                medico.getNombre(),
                medico.getEmail(),
                medico.getTelefono(),
                medico.getEspecialidad().toString(),
                new DatosDireccion(medico.getDireccion().getCalle(),
                        medico.getDireccion().getDistrito(),
                        medico.getDireccion().getNumero(),
                        medico.getDireccion().getCiudad(),
                        medico.getDireccion().getComplemento()));
        return ResponseEntity.ok(datosMedico);
    }








    //elimina de la base de datos
    /*
    @DeleteMapping("/{id}")
    @Transactional
    public void eliminarMedico (@PathVariable Long id){
        Medico medico = repository.getReferenceById(id);
        repository.delete(medico);
    }
    */


}
