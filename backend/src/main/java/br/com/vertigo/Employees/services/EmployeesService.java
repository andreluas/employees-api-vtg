package br.com.vertigo.Employees.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.vertigo.Employees.dto.EmployeesDTO;
import br.com.vertigo.Employees.dto.EmployeesRequiredDTO;
import br.com.vertigo.Employees.dto.shared.Translate;
import br.com.vertigo.Employees.entity.Employees;
import br.com.vertigo.Employees.repository.EmployeesRepository;
import br.com.vertigo.Employees.services.exceptions.BooleanDtoException;
import br.com.vertigo.Employees.services.exceptions.ResourceNotFoundException;

@Service
public class EmployeesService {

    @Autowired
    private EmployeesRepository repository;

    @Autowired
    private Translate translate;

    // Buscar todos os employees
    @Transactional(readOnly = true)
    public List<EmployeesDTO> findAll() {
        List<Employees> list = repository.findAll();
        return list.stream().map(x -> new EmployeesDTO(x)).collect(Collectors.toList());
    }

    // Busca por id
    @Transactional(readOnly = true)
    public EmployeesDTO findById(Long id) {
        Optional<Employees> obj = repository.findById(id);
        Employees entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found " + id));
        return new EmployeesDTO(entity);
    }

    // Inserir um Employee
    @Transactional
    public EmployeesRequiredDTO insert(EmployeesRequiredDTO dto) throws BooleanDtoException {
        Employees entity = new Employees();
        translate.copyDtoRequiredToEntity(dto, entity);
        entity = repository.save(entity);
        return new EmployeesRequiredDTO(entity);
    }

    // Deletar um Employee
    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not foud " + id);
        }
    }

    // Atualiza um Employee
    @Transactional
    public EmployeesDTO update(Long id, EmployeesDTO dto) {
        Optional<Employees> obj = repository.findById(id);
        Employees entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        translate.copyPatch(dto, entity);
        entity = repository.save(entity);
        return new EmployeesDTO(entity);
    }

}
