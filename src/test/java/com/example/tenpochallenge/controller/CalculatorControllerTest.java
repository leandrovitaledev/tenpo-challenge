package com.example.tenpochallenge.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.tenpochallenge.DTO.CalculatorDTO;
import com.example.tenpochallenge.entity.RegistroHistorial;
import com.example.tenpochallenge.service.CalculatorService;

@SpringBootTest
@AutoConfigureMockMvc
public class CalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    private CalculatorController calculatorController;

    @Mock
    private CalculatorService calculatorService;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(calculatorController).build();
    }

    @Test
    public void testGetResult() throws Exception {
        CalculatorDTO dto = new CalculatorDTO(10, 90);
        double resultadoSimulado = 142.0;
        when(calculatorService.getResult(dto)).thenReturn(resultadoSimulado);

        mockMvc.perform(get("/api/calculator")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"num1\": 10,\"num2\": 90}"))
                .andExpect(status().isOk())
                .andExpect(content().string("142.0"));

        verify(calculatorService).getResult(dto);
    }
    

    @Test
    public void testGetRegistry() throws Exception {
        int page = 0;
        int size = 10;

        List<RegistroHistorial> registros = new ArrayList<>();
        registros.add(new RegistroHistorial(10, 90, 25, 125));
        registros.add(new RegistroHistorial(20, 80, 31, 131));
        Pageable pageable = PageRequest.of(page, size);
        Page<RegistroHistorial> registroPage = new PageImpl<>(registros, pageable, registros.size());

        when(calculatorService.getRegistry(page, size)).thenReturn(registroPage);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/registry")
            .param("page", String.valueOf(page))
            .param("size", String.valueOf(size))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
             .andExpect(MockMvcResultMatchers.jsonPath("$.content", hasSize(2)))
             .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].num1", is(10)))
             .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].num2", is(90)))
             .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].randomNumber", is(25.0)))
             .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].resultado", is(125.0)))
             .andExpect(MockMvcResultMatchers.jsonPath("$.content[1].num1", is(20)))
             .andExpect(MockMvcResultMatchers.jsonPath("$.content[1].num2", is(80)))
             .andExpect(MockMvcResultMatchers.jsonPath("$.content[1].randomNumber", is(31.0)))
             .andExpect(MockMvcResultMatchers.jsonPath("$.content[1].resultado", is(131.0)))
            .andReturn();
    }
}