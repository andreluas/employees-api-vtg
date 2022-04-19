package br.com.vertigo.Employees.services;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.vertigo.Employees.dto.EmployeesDTO;
import br.com.vertigo.Employees.entity.Employees;
import br.com.vertigo.Employees.factory.Factory;
import br.com.vertigo.Employees.repository.EmployeesRepository;
import br.com.vertigo.Employees.services.exceptions.ResourceNotFoundException;

@ExtendWith(SpringExtension.class)
public class EmployeesServiceTests {
    
    @InjectMocks
    private EmployeesService service;

    @Mock
    private EmployeesRepository repository;

    private Long existingId;
    private Long nonExistingId;
    private Employees employee;
    private EmployeesDTO employeeDTO;
    private List<Employees> listEmployee;

    @BeforeEach
    void setup() throws Exception {
        existingId = 1L;
        nonExistingId = 100L;
        employee = Factory.createEmployee();
        employeeDTO = Factory.createEmployeeDTO();
        listEmployee = Factory.createListEmployee();

        when(repository.findAll()).thenReturn(listEmployee);    
        when(repository.findById(existingId)).thenReturn(Optional.of(employee));
        when(repository.findById(nonExistingId)).thenReturn(Optional.empty());

        doNothing().when(repository).deleteById(existingId);
        doThrow(ResourceNotFoundException.class).when(repository).deleteById(nonExistingId);
        
        when(repository.save(ArgumentMatchers.any())).thenReturn(employee);
    }

    @Test
    public void findAllEmployees() {

        List<EmployeesDTO> result = service.findAll();

        Assertions.assertNotNull(result);
    }

    @Test
    public void findByIdShouldReturnEmployeeDTOWhenIdExists() {

        EmployeesDTO result = service.findById(existingId);
        Assertions.assertNotNull(result);
    }

    @Test
    public void findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            service.findById(nonExistingId);
        });
    }

    @Test
    public void deleteShouldDoNothingWhenIdExists() {

        Assertions.assertDoesNotThrow(() -> {
            service.delete(existingId);
        });

        verify(repository).deleteById(existingId);
    }

    @Test
    public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            service.delete(nonExistingId);
        });

        verify(repository).deleteById(nonExistingId);
    }

    @Test
    public void updateShouldReturnEmployeeDTOWhenIdExists() {

        EmployeesDTO result = service.update(existingId, employeeDTO);
        Assertions.assertNotNull(result);
    }

    @Test
    public void updateShouldReturnEntityNotFoundExceptionWhenIdDoesNotExists() {

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            service.update(nonExistingId, employeeDTO);
        });
    }
}