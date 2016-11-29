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
The Application context is:
```xml
<?xml version="1.0" encoding="UTF-8"?>

<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:context="http://www.springframework.org/schema/context"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
    http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
    http://www.springframework.org/schema/context
    http://www.springframework.org/schema/context/spring-context-3.0.xsd">
   <context:annotation-config/>

   <bean id="myCar" class="com.markikito.prueba.autowired.autowiredsetter.vehicle.impl.Car">
   </bean>

   <bean id="myMotor" class="com.markikito.prueba.autowired.autowiredsetter.vehicle.impl.MotorDiesel">
   </bean>
</beans>
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
[DEBUG]Returning cached instance of singleton bean 'org.springframework.context.annotation.internalConfigurationAnnotationProcessor'
[DEBUG]Returning cached instance of singleton bean 'org.springframework.context.annotation.internalAutowiredAnnotationProcessor'
[DEBUG]Returning cached instance of singleton bean 'org.springframework.context.annotation.internalRequiredAnnotationProcessor'
[DEBUG]Returning cached instance of singleton bean 'org.springframework.context.annotation.internalCommonAnnotationProcessor'
[DEBUG]Creating shared instance of singleton bean 'myCar'
[DEBUG]Creating instance of bean 'myCar'
Inside constructor Car
```
The first log messages are from the Spring context initialization, because the log´s config is set DEBUG level:
 - Run Spring preprocessors
 - Create instance Bean MyCar that is a singleton (There is only one instance of the class in virtual machine)
 - An finally the Constructor is executed and show the messages 'Inside constructor Car'
 
 The next lines of log:
 ```shell
[DEBUG]Registered injected element on class [Car]: AutowiredMethodElement for public void Vehiculo.setMotor(Motor)
[DEBUG]Eagerly caching bean 'myCar' to allow for resolving potential circular references
[DEBUG]Processing injected element of bean 'myCar': AutowiredMethodElement for public void Vehiculo.setMotor(Motor)
[DEBUG]Creating shared instance of singleton bean 'myMotor'
[DEBUG]Creating instance of bean 'myMotor'
Inside constructor MotorDiesel
```
Primero Spring detecta que hay una notación de autoriwire sobre el método 'setMotor(Motor)' 
Busca un bean en el contexto que implemente la interfaz
y crea un singleton sobre este bean haciendo que también se invoque el constructor de Motor.
```shell
[DEBUG]Eagerly caching bean 'myMotor' to allow for resolving potential circular references
[DEBUG]Finished creating instance of bean 'myMotor'
[DEBUG]Autowiring by type from bean name 'myCar' to bean named 'myMotor'
[DEBUG]Finished creating instance of bean 'myCar'
```
Se comprueba que no halla referencias circulares
Se termina la creación de la instancia 'myMotor' y se hace un autowiring por tipo para enganchar el coche con su motor.
Se da por terminada la creación de la instancia coche.

```shell
Putting the keys in the car ignition
Starting the diesel engine
Starting the car
```

And finally the messages of the methods about start the car is show.

