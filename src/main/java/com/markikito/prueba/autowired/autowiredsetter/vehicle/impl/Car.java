package com.markikito.prueba.autowired.autowiredsetter.vehicle.impl;

import com.markikito.prueba.autowired.autowiredsetter.vehicle.Vehiculo;

public class Car extends Vehiculo {

		
	public Car() {
		super();
		System.out.println("Inside constructor Car");
	}


	@Override
	public void putTheKey() {
		System.out.println("Putting the keys in the car ignition");		
	}

	@Override
	public void start() {
		this.putTheKey();
		motor.start();
		System.out.println("Starting the car");
	}


	@Override
	public void stop() {
		System.out.println("The engine of the vehicle has to stop");
		motor.stopt();
		System.out.println("And finally turned off the car.");
		
	}


}
