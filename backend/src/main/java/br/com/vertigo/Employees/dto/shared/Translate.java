package br.com.vertigo.Employees.dto.shared;

import org.springframework.stereotype.Service;

import br.com.vertigo.Employees.dto.EmployeesDTO;
import br.com.vertigo.Employees.dto.EmployeesRequiredDTO;
import br.com.vertigo.Employees.entity.Employees;

@Service
public class Translate {

    public void copyDtoToEntity(EmployeesDTO dto, Employees entity) {
        entity.setFirst_name(dto.getFirst_name());
        entity.setLast_name(dto.getLast_name());
        entity.setDepartment(dto.getDepartment());
        entity.setJob_title(dto.getJob_title());
        entity.setEmployee_type(dto.getEmployee_type());
        entity.setStart_date(dto.getStart_date());
        entity.setStatus(dto.getStatus());
        entity.setEmail(dto.getEmail());
    }

    public void copyDtoRequiredToEntity(EmployeesRequiredDTO dto, Employees entity) {
        entity.setFirst_name(dto.getFirst_name());
        entity.setLast_name(dto.getLast_name());
        entity.setDepartment(dto.getDepartment());
        entity.setJob_title(dto.getJob_title());
        entity.setEmployee_type(dto.getEmployee_type());
        entity.setStart_date(dto.getStart_date());
        entity.setStatus(dto.getStatus());
        entity.setEmail(dto.getEmail());
    }

    public void copyPatch(EmployeesDTO dto, Employees entity) {

        // first_name
        if (dto.getFirst_name() == null) {
            entity.setFirst_name(entity.getFirst_name());
        } else {
            entity.setFirst_name(dto.getFirst_name());
        }

        // last_name
        if (dto.getLast_name() == null) {
            entity.setLast_name(entity.getLast_name());
        } else {
            entity.setLast_name(dto.getLast_name());
        }

        // department
        if (dto.getDepartment() == null) {
            entity.setDepartment(entity.getDepartment());
        } else {
            entity.setDepartment(dto.getDepartment());
        }

        // job_title
        if (dto.getJob_title() == null) {
            entity.setEmployee_type(entity.getJob_title());
        } else {
            entity.setEmployee_type(dto.getJob_title());
        }

        // employee_type
        if (dto.getJob_title() == null) {
            entity.setEmployee_type(entity.getJob_title());
        } else {
            entity.setEmployee_type(dto.getJob_title());
        }

        // start_date
        if (dto.getStart_date() == null) {
            entity.setStart_date(entity.getStart_date());
        } else {
            entity.setStart_date(dto.getStart_date());
        }

        // status
        if (dto.getStatus() == null) {
            entity.setStatus(entity.getStatus());
        } else {
            entity.setStatus(dto.getStatus());
        }

        // email
        if (dto.getEmail() == null) {
            entity.setEmail(entity.getEmail());
        } else {
            entity.setEmail(dto.getEmail());
        }
    }
}
