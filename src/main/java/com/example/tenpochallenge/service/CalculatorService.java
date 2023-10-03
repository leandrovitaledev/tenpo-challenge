package com.example.tenpochallenge.service;

import com.example.tenpochallenge.DTO.CalculatorDTO;

public interface CalculatorService {
	
    double getResult(CalculatorDTO dto) throws Exception;

}
