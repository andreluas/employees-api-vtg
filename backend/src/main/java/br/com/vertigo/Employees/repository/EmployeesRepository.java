package br.com.vertigo.Employees.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.vertigo.Employees.entity.Employees;

@Repository
public interface EmployeesRepository extends JpaRepository<Employees, Long> {
    
}
