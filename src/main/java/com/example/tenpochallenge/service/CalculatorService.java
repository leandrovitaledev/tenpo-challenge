package com.example.tenpochallenge.service;

import org.springframework.data.domain.Page;

import com.example.tenpochallenge.DTO.CalculatorDTO;
import com.example.tenpochallenge.entity.RegistroHistorial;

public interface CalculatorService {
	
    double getResult(CalculatorDTO dto) throws Exception;
    
	Page<RegistroHistorial> getRegistry(int page, int size);

}
