package com.markikito.prueba.autowired.autowiredsetter.vehicle;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:ApplicationContext.xml"})
public class AutoWiredSetterTest extends TestCase{
	
	@Autowired
    IVehicle vehiculo;
	
    public AutoWiredSetterTest() {
		super();
		System.out.println("Inside the constructor Test.");
	}

	@Before
    public void setUp() throws Exception {
		super.setUp();
    	System.out.println("Before the test has been run.");
    }
	
	@After
	public void tearDown() throws Exception{
		super.tearDown();
		System.out.println("After the test has been run.");
	}

    @Test
    @Order(1)
    public void testStartCar(){
        vehiculo.start();
    }

//    @Test
//    @Order(2)
//    public void testStoptCar() {
//        vehiculo.stop();
//    }

    

}
