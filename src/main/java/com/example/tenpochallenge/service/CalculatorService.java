package com.example.tenpochallenge.service;

import java.util.List;

import com.example.tenpochallenge.DTO.CalculatorDTO;
import com.example.tenpochallenge.entity.RegistroHistorial;

public interface CalculatorService {
	
    double getResult(CalculatorDTO dto) throws Exception;
    
    List<RegistroHistorial> getRegistry();

}
