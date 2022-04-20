package br.com.vertigo.Employees.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import br.com.vertigo.Employees.dto.EmployeesDTO;
import br.com.vertigo.Employees.dto.EmployeesRequiredDTO;
import br.com.vertigo.Employees.factory.Factory;
import br.com.vertigo.Employees.services.EmployeesService;
import br.com.vertigo.Employees.services.exceptions.ResourceNotFoundException;

@WebMvcTest
public class EmployeesControlerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeesService service;

    @Autowired
    private ObjectMapper objectMapper;

    private Long existingId;
    private Long nonExistingId;
    private EmployeesDTO employeeDTO;
    private EmployeesRequiredDTO employeesRequiredDTO;
    private List<EmployeesDTO> listEmployeeDTO;

    @BeforeEach
    void setUp() throws Exception {

        existingId = 1L;
        nonExistingId = 100L;
        employeeDTO = Factory.createEmployeeDTO();
        employeesRequiredDTO = Factory.createEmployeeRequiredDTO();
        listEmployeeDTO = Factory.createListEmployeeDTO();

        when(service.findAll()).thenReturn(listEmployeeDTO);
        when(service.findById(existingId)).thenReturn(employeeDTO);
        when(service.findById(nonExistingId)).thenThrow(ResourceNotFoundException.class);

        doNothing().when(service).delete(existingId);
        doThrow(ResourceNotFoundException.class).when(service).delete(nonExistingId);

        when(service.update(eq(existingId), any())).thenReturn(employeeDTO);
        when(service.update(eq(nonExistingId), any())).thenThrow(ResourceNotFoundException.class);

        when(service.insert(any())).thenReturn(employeesRequiredDTO);
    }

    @Test
    public void findAllShouldReturnEmployeeList() throws Exception {
        ResultActions result = mockMvc.perform(get("/employee").accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
    }

    @Test
    public void findByIdShouldReturnEmployeeWhenIdExists() throws Exception {

        ResultActions result = mockMvc.perform(get("/employee/{id}", existingId).accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.first_name").exists());
        result.andExpect(jsonPath("$.last_name").exists());
    }

    @Test
    public void findByIdShouldReturnNotFoundWhenIdNotExists() throws Exception {

        ResultActions result = mockMvc.perform(get("/employee/{id}", nonExistingId).accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isNotFound());
    }

    @Test
    public void deleteShouldReturnNoContentWhenIdExists() throws Exception {

        ResultActions result = mockMvc.perform(delete("/employee/{id}", existingId).accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isNoContent());
    }

    @Test
    public void deleteShouldReturnNotFoundWhenIdNotExists() throws Exception {

        ResultActions result = mockMvc
                .perform(delete("/employee/{id}", nonExistingId).accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isNotFound());
    }

    @Test
    public void insertShouldReturnEmployeeDTOCreated() throws Exception {

        String jsonBody = objectMapper.writeValueAsString(employeesRequiredDTO);

        ResultActions result = mockMvc.perform(post("/employee").content(jsonBody).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isCreated());
    }

    @Test
    public void updateShouldReturnEmployeeDTOWhenIdExists() throws Exception {

        String jsonBody = objectMapper.writeValueAsString(employeeDTO);

        ResultActions result = mockMvc.perform(patch("/employee/{id}", existingId).content(jsonBody).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.first_name").exists());
        result.andExpect(jsonPath("$.last_name").exists());
    }

    @Test
    public void updateShouldReturnExceptionWhenIdDoNotExists() throws Exception {

        String jsonBody = objectMapper.writeValueAsString(employeeDTO);

        ResultActions result = mockMvc.perform(patch("/employee/{id}", nonExistingId).content(jsonBody).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isNotFound());
    }

}
