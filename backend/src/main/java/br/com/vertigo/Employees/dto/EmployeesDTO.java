package br.com.vertigo.Employees.dto;

import java.io.Serializable;
import java.sql.Date;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.vertigo.Employees.entity.Employees;

public class EmployeesDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long employee_id;

    @Pattern(regexp = "^[a-zA-Z-+\s]+$", message="favor inserir apenas caracteres")
    @Size(min = 1, max = 45, message = "first_name must be 1 to 45 characters long")
    private String first_name;

    @Pattern(regexp = "^[a-zA-Z-+\s]+$", message="favor inserir apenas caracteres")
    @Size(min = 1, max = 45, message = "last_name must be 1 to 45 characters long")
    private String last_name;

    @Pattern(regexp = "^[a-zA-Z-+\s]+$", message="favor inserir apenas caracteres")
    @Size(min = 1, max = 45, message = "department must be 1 to 45 characters long")
    private String department;

    @Pattern(regexp = "^[a-zA-Z-+\s]+$", message="favor inserir apenas caracteres")
    @Size(min = 1, max = 45, message = "job_title must be 1 to 45 characters long")
    private String job_title;

    @Pattern(regexp = "^[a-zA-Z-+\s]+$", message="favor inserir apenas caracteres")
    @Size(min = 1, max = 45, message = "employee_type must be 1 to 45 characters long")
    private String employee_type;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date start_date;

    @Pattern(regexp = "^[a-zA-Z-+\s]+$", message="favor inserir apenas caracteres")
    @Size(min = 1, max = 45, message = "status must be 1 to 45 characters long")
    private String status;

    @Size(min = 1, max = 45, message = "email must be 1 to 45 characters long")
    private String email;

    EmployeesDTO() {
    }

    public EmployeesDTO(Long employee_id, String first_name, String last_name, String department, String job_title,
            String employee_type, Date start_date, String status, String email) {
        this.employee_id = employee_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.department = department;
        this.job_title = job_title;
        this.employee_type = employee_type;
        this.start_date = start_date;
        this.status = status;
        this.email = email;
    }

    public EmployeesDTO(Employees entity) {
        this.employee_id = entity.getEmployee_id();
        this.first_name = entity.getFirst_name();
        this.last_name = entity.getLast_name();
        this.department = entity.getDepartment();
        this.job_title = entity.getJob_title();
        this.employee_type = entity.getEmployee_type();
        this.start_date = entity.getStart_date();
        this.status = entity.getStatus();
        this.email = entity.getEmail();
    }

    public Long getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Long employee_id) {
        this.employee_id = employee_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public String getEmployee_type() {
        return employee_type;
    }

    public void setEmployee_type(String employee_type) {
        this.employee_type = employee_type;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
