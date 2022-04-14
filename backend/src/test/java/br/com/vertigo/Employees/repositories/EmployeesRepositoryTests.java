package br.com.vertigo.Employees.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.vertigo.Employees.entity.Employees;
import br.com.vertigo.Employees.factory.Factory;
import br.com.vertigo.Employees.repository.EmployeesRepository;

@DataJpaTest
public class EmployeesRepositoryTests {

    @Autowired
    private EmployeesRepository repository;

    private long existingId;
    private long nonExistingId;
    private long totalEmployees;

    @BeforeEach
    void setUp() throws Exception {
        existingId = 1L;
        nonExistingId = 100L;
        totalEmployees = 10L;
    }

    @Test
    public void findByIdShouldReturnNonEmptyOptionalEmployeeWhenIdExists() {

        Optional<Employees> result = repository.findById(existingId);
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    public void findByIdShouldReturnEmptyOptionalEmployeeWhenIdDoesNotExists() {

        Optional<Employees> result = repository.findById(nonExistingId);
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void saveShouldPersistWithAutoincrementWhenIdIsNull() {
        
        Employees employees = Factory.createEmployee();
        employees.setEmployee_id(null);
        employees = repository.save(employees);

        Assertions.assertNotNull(employees.getEmployee_id());
        Assertions.assertEquals(totalEmployees + 1, employees.getEmployee_id());
    }

    @Test
    public void deleteShouldDeleteObjectWhenIdExists() {

        repository.deleteById(existingId);
        Optional<Employees> result = repository.findById(existingId);

        Assertions.assertFalse(result.isPresent());
    }
    
}
