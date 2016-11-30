package com.markikito.prueba.autowired.autowiredsetter.vehicle.impl;

import com.markikito.prueba.autowired.autowiredsetter.vehicle.Motor;

public class MotorDiesel implements Motor {
	
	public MotorDiesel() {
		super();
		System.out.println("Inside constructor MotorDiesel");
	}

	@Override
	public void start() {
		System.out.println("Starting the diesel engine");
	}

	@Override
	public void stopt() {
		System.out.println("Stoping the diesel engine");		
	}

}
