package com.markikito.prueba.autowired.autowiredsetter.vehicle;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class Vehiculo implements IVehicle {
	
	
	
	protected Motor motor;

	@Autowired
	public void setMotor(Motor motor) {
		System.out.println("Metodo setMotor()....");
		this.motor = motor;
	}

	public Motor getMotor() {
		return motor;
	}
	
	
}
