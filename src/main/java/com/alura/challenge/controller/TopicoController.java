package com.alura.challenge.controller;

import com.alura.challenge.domain.topico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @PostMapping
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroTopico datos) {
        var duplicado = repository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje());
        if (duplicado) {
            return ResponseEntity.badRequest().body("Ya existe un tópico con el mismo título y mensaje.");
        }

        var topico = new Topico(datos);
        repository.save(topico);

        return ResponseEntity.ok("Tópico registrado exitosamente.");
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> listar(
            @RequestParam(required = false) String curso,
            @PageableDefault(size = 10, sort = "fechaCreacion", direction = Sort.Direction.ASC) Pageable paginacion) {

        Page<Topico> pagina;

        if (curso != null && !curso.isBlank()) {
            pagina = repository.findAllByCurso(curso, paginacion);
        } else {
            pagina = repository.findAll(paginacion);
        }

        return ResponseEntity.ok(pagina.map(DatosListadoTopico::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity detallar(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        var topico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DatosDetalleTopico(topico));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizar(@PathVariable Long id, @RequestBody @Valid DatosActualizacionTopico datos) {
        var optionalTopico = repository.findById(id);
        if (!optionalTopico.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        var topico = optionalTopico.get();

        if (datos.titulo() != null && datos.mensaje() != null) {
            var esDuplicado = repository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje());
            if (esDuplicado) {
                return ResponseEntity.badRequest().body("Ya existe otro tópico con el mismo título y mensaje.");
            }
        }
        topico.actualizarDatos(datos);

        return ResponseEntity.ok(new DatosDetalleTopico(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminar(@PathVariable Long id) {
        var optionalTopico = repository.findById(id);

        if (optionalTopico.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}