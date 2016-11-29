# TetsAutowired
This project is to test the implications of the Autowired tag in the various points of the code setter (over setter,over atribute, over constructor).

## Introduction
The tests are based on the domino vehicles.

Class Vehicle:
```java
public abstract class Vehiculo implements IVehicle {
	protected Motor motor;
  
	@Autowired
	public void setMotor(Motor motor) {
		this.motor = motor;
	}
  
	public Motor getMotor() {
		return motor;
	}	
}
```
Class Car:
```java
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

```
Class MotorDiesel:
```java
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
```
And the test is:
```java
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
}
```


## Over Setter
In this case the Autowired is positioned on the setter method:
```java
@Autowired
	public void setMotor(Motor motor) {
		this.motor = motor;
	}
```

The messages of execution are :

```shell
Returning cached instance of singleton bean 'org.springframework.context.annotation.internalConfigurationAnnotationProcessor'
Returning cached instance of singleton bean 'org.springframework.context.annotation.internalAutowiredAnnotationProcessor'
Returning cached instance of singleton bean 'org.springframework.context.annotation.internalRequiredAnnotationProcessor'
Returning cached instance of singleton bean 'org.springframework.context.annotation.internalCommonAnnotationProcessor'
Creating shared instance of singleton bean 'myCar'
Creating instance of bean 'myCar'
```
