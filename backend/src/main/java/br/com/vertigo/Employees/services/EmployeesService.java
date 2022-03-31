package br.com.vertigo.Employees.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.vertigo.Employees.dto.EmployeesDTO;
import br.com.vertigo.Employees.entity.Employees;
import br.com.vertigo.Employees.repository.EmployeesRepository;
import br.com.vertigo.Employees.services.exceptions.ResourceNotFoundException;

@Service
public class EmployeesService {

    @Autowired
    private EmployeesRepository repository;

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
        Employees entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new EmployeesDTO(entity);
    }

    // Inserir um Employee
    @Transactional
    public EmployeesDTO insert(EmployeesDTO dto) {
        Employees entity = new Employees();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new EmployeesDTO(entity);
    }

    // Deletar um Employee
    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not foud" + id);
        }
    }

    // Atualiza um Employee
    @Transactional
    public EmployeesDTO update(Long id, EmployeesDTO dto) {
        try {
            Employees entity = repository.getById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new EmployeesDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found" + id);
        }
    }

    // DTO -> Entity
    private void copyDtoToEntity(EmployeesDTO dto, Employees entity) {
        entity.setFirst_name(dto.getFirst_name());
        entity.setLast_name(dto.getLast_name());
        entity.setDepartment(dto.getDepartment());
        entity.setJob_title(dto.getJob_title());
        entity.setEmployee_type(dto.getEmployee_type());
        entity.setStart_date(dto.getStart_date());
        entity.setStatus(dto.getStatus());
        entity.setEmail(dto.getEmail());
    }
}
