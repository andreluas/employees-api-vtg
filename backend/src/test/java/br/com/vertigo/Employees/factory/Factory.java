package br.com.vertigo.Employees.factory;

import java.sql.Date;

import br.com.vertigo.Employees.entity.Employees;

public class Factory {
    
    public static Employees createEmployee() {
        Date data = new Date(10-10-2022);
        Employees employees = new Employees();
        employees.setFirst_name("Teste");
        employees.setLast_name("Teste");
        employees.setDepartment("Delivery");
        employees.setJob_title("Estágio");
        employees.setEmployee_type("Backend");
        employees.setStart_date(data);
        employees.setStatus("Active");
        employees.setEmail("email@email.com.br");

        return employees;
    }
}
