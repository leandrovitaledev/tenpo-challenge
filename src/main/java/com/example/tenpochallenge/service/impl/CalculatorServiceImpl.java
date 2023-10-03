package com.example.tenpochallenge.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tenpochallenge.DTO.CalculatorDTO;
import com.example.tenpochallenge.dao.RandomNumberDao;
import com.example.tenpochallenge.dao.RegistroHistorialDao;
import com.example.tenpochallenge.entity.RegistroHistorial;
import com.example.tenpochallenge.exception.CustomException;
import com.example.tenpochallenge.service.CalculatorService;
import com.example.tenpochallenge.utils.HTTPCodesEnum;

/**
 * Interface para interactuar con CalculatorService
 * 
 *
 */
@Service
public class CalculatorServiceImpl implements CalculatorService {
	
	@Autowired
	private RandomNumberDao randomNumberDao;
	@Autowired
	private RegistroHistorialDao registroHistorialDao;
	
	/**
	 * Obtiene el resultado
	 * 
	 * @return
	 * @throws Exception 
	 */
	public double getResult(CalculatorDTO dto) throws Exception {
		double randomNumber;
		double resultado;
		
		try {
			randomNumber = Double.parseDouble(randomNumberDao.getRandomNumber());
		} catch (RuntimeException e) {
			throw new CustomException(HTTPCodesEnum.STATUS_400);
		}
		
		resultado = calcularResultado(dto.getNum1(), dto.getNum2(), randomNumber);
		RegistroHistorial registroHistorial = new RegistroHistorial(dto, randomNumber, resultado);
		
		try {
			registroHistorialDao.save(registroHistorial);
		} catch (RuntimeException e) {
			throw new CustomException(HTTPCodesEnum.STATUS_400);
		}
		
		return resultado;
	}
	
	private double calcularResultado(int num1, int num2, double randomNumber) {
		int suma = num1 + num2;
	    return suma + (suma * (randomNumber / 100.0));
	}

}
