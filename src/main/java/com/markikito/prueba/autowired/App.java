package com.markikito.prueba.autowired;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.markikito.prueba.autowired.autowiredsetter.vehicle.IVehicle;



public class App {
	
    
	 public static void main(String[] args) {		 
		 ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		 IVehicle vehiculo;
		 vehiculo=context.getBean(IVehicle.class);
		 vehiculo.start();
		 //vehiculo.stop();
	 }

}
