package br.com.vertigo.Employees.factory;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import br.com.vertigo.Employees.dto.EmployeesDTO;
import br.com.vertigo.Employees.dto.EmployeesRequiredDTO;
import br.com.vertigo.Employees.entity.Employees;

public class Factory {
    
    public static Employees createEmployee() {

        Date data = new Date(10/10/2022);
        Employees employees = new Employees();
        employees.setFirst_name("Teste");
        employees.setLast_name("Teste");
        employees.setDepartment("Delivery");
        employees.setJob_title("Estagio");
        employees.setEmployee_type("Backend");
        employees.setStart_date(data);
        employees.setStatus("Active");
        employees.setEmail("email@email.com.br");

        return employees;
    }

    public static EmployeesDTO createEmployeeDTO() {

        Employees employees = createEmployee();
        return new EmployeesDTO(employees);
    }

    public static EmployeesRequiredDTO createEmployeeRequiredDTO() {

        Employees employees = createEmployee();
        return new EmployeesRequiredDTO(employees);
    }

    public static List<EmployeesDTO> createListEmployeeDTO() {
        
        List<EmployeesDTO> listEmployee = new ArrayList<>(); 
        EmployeesDTO employeesDTO = createEmployeeDTO();
        listEmployee.add(employeesDTO);
        
        return listEmployee;
    }
}
