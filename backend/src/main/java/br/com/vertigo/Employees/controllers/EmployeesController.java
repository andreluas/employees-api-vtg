package br.com.vertigo.Employees.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.vertigo.Employees.dto.EmployeesDTO;
import br.com.vertigo.Employees.dto.EmployeesRequiredDTO;
import br.com.vertigo.Employees.services.EmployeesService;

@RestController
@RequestMapping(value = "/employee")
public class EmployeesController {

    @Autowired
    private EmployeesService service;

    @GetMapping
    public ResponseEntity<List<EmployeesDTO>> findAll() {
        List<EmployeesDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EmployeesDTO> findById(@PathVariable Long id) {
        EmployeesDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<EmployeesRequiredDTO> insert(@Valid @RequestBody EmployeesRequiredDTO dto) {
        EmployeesRequiredDTO newDto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getEmployee_id())
                .toUri();
        return ResponseEntity.created(uri).body(newDto);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<EmployeesDTO> update(@PathVariable Long id, @Valid @RequestBody EmployeesDTO dto) {
        EmployeesDTO newDto = service.update(id, dto);
        return ResponseEntity.ok().body(newDto);
    }
}
