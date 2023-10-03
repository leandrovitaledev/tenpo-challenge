package com.example.tenpochallenge.entity;

import com.example.tenpochallenge.DTO.CalculatorDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class RegistroHistorial {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	private int num1;
	private int num2;
	private double randomNumber;
	private double resultado;
	
	public RegistroHistorial(CalculatorDTO dto, double randomNumber, double resultado) {
		this.num1 = dto.getNum1();
		this.num2 = dto.getNum2();
		this.randomNumber = randomNumber;
		this.resultado = resultado;
	}
	
	public int getNum1() {
		return num1;
	}
	public void setNum1(int num1) {
		this.num1 = num1;
	}
	public int getNum2() {
		return num2;
	}
	public void setNum2(int num2) {
		this.num2 = num2;
	}
	public double getRandomNumber() {
		return randomNumber;
	}

	public void setRandomNumber(double randomNumber) {
		this.randomNumber = randomNumber;
	}

	public double getResultado() {
		return resultado;
	}
	public void setResultado(double resultado) {
		this.resultado = resultado;
	}
	
}
