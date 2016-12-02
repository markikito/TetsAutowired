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

In the following sections the traces and small explication will be shown.


## Over Setter
In this case the Autowired is positioned on the setter method:
```java
	@Autowired
	public void setMotor(Motor motor) {
		System.out.println("Metodo setMotor()....");
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
[DEBUG]Registered injected element on class [Car]: AutowiredMethodElement for public void Vehiculo.setMotor(Motor)
[DEBUG]Eagerly caching bean 'myCar' to allow for resolving potential circular references
[DEBUG]Processing injected element of bean 'myCar': AutowiredMethodElement for public void Vehiculo.setMotor(Motor)
[DEBUG]Creating shared instance of singleton bean 'myMotor'
[DEBUG]Creating instance of bean 'myMotor'
Inside constructor MotorDiesel
[DEBUG]Eagerly caching bean 'myMotor' to allow for resolving potential circular references
[DEBUG]Finished creating instance of bean 'myMotor'
[DEBUG]Autowiring by type from bean name 'myCar' to bean named 'myMotor'
Metodo setMotor()....
[DEBUG]Finished creating instance of bean 'myCar'
[DEBUG]Returning cached instance of singleton bean 'myMotor'
[DEBUG]Returning cached instance of singleton bean 'lifecycleProcessor'
[DEBUG]Searching for key 'spring.liveBeansView.mbeanDomain' in [systemProperties]
[DEBUG]Searching for key 'spring.liveBeansView.mbeanDomain' in [systemEnvironment]
[DEBUG]Could not find key 'spring.liveBeansView.mbeanDomain' in any property source. Returning [null]
[DEBUG]Returning cached instance of singleton bean 'myCar'
```
All lines of logs are simply initializing the Context of Spring.

Important things:
- Declared beans are initialized using the singleton pattern
- Car class constructor is executed : "Inside constructor Car"
- Spring write down that is a method with Autowired: "AutowiredMethodElement for public void Vehiculo.setMotor(Motor)"
- Continue reading the context file and  initialize the MotorDiesel Bean : "Inside constructor MotorDiesel"
- The autowired is launched by type "Autowiring by type from bean name 'myCar' to bean named 'myMotor'"
- The Setter method is executed setMotor(): "Metodo setMotor()...."
- The creation of the beans is completed and the instances of the beans are cached.
 
The next lines of log:
```shell
Putting the keys in the car ignition
Starting the diesel engine
Starting the car
```

And finally the messages of the methods about start the car is show.

## Over Atribbute
In this test the Autowired is placed on the attribute of the class:

```java
public abstract class Vehiculo implements IVehicle {		
	@Autowired
	protected Motor motor;
	
	public void setMotor(Motor motor) {
		System.out.println("Metodo setMotor()....");
		this.motor = motor;
	}
	
	public Motor getMotor() {
		return motor;
	}	
}
```
In the following lines samples the parts of the log that show the significant differences:

```shell
[DEBUG]Creating instance of bean 'myCar'
Inside constructor Car
Registered injected element on class [Car]: AutowiredFieldElement for protected Motor Vehiculo.motor
Eagerly caching bean 'myCar' to allow for resolving potential circular references
Processing injected element of bean 'myCar': AutowiredFieldElement for protected Motor Vehiculo.motor
Creating shared instance of singleton bean 'myMotor'
Creating instance of bean 'myMotor'
Inside constructor MotorDiesel
```
The main difference is that the System.out of the setMotor method is not displayed (...). So when the autowired is over the attribute the seter method is not executed.

The Spring logs appear as follows:
```shell
Processing injected element of bean 'myCar': AutowiredFieldElement for protected Motor Vehiculo.motor
```
In the previous test case the logs were as follows:
```shell
AutowiredMethodElement for public void Vehiculo.setMotor(Motor)
```
When the Autowired is placed above the attribute the engine is injected without using the setter method.


## Over Constructor
In this case the Autowired notation is on the constructor of the class Car. In the previous examples the Autowired was on the Base Vehicle class, it is necessary to do so because the daughter class is the one invoked from the context of Spring and is the one that needs to have The Autowired.

So the class would look like this:

```java
public class Car extends Vehiculo {	
	@Autowired
	public Car(Motor motor) {
		super(motor);		
		System.out.println("Inside constructor Car");
		this.motor=motor;
	}
```

The execution log is:

```shell
[DEBUG]Creating shared instance of singleton bean 'myCar'
[DEBUG]Creating instance of bean 'myCar'
[DEBUG]Creating shared instance of singleton bean 'myMotor'
[DEBUG]Creating instance of bean 'myMotor'
Inside constructor MotorDiesel
[DEBUG]Eagerly caching bean 'myMotor' to allow for resolving potential circular references
[DEBUG]Finished creating instance of bean 'myMotor'
[DEBUG]Autowiring by type from bean name 'myCar' via constructor to bean named 'myMotor'
Inside Vehiculo constructor.
Inside constructor Car
[DEBUG]Eagerly caching bean 'myCar' to allow for resolving potential circular references
[DEBUG]Finished creating instance of bean 'myCar'
[DEBUG]Returning cached instance of singleton bean 'myMotor'
[DEBUG]Returning cached instance of singleton bean 'lifecycleProcessor'
[DEBUG]Searching for key 'spring.liveBeansView.mbeanDomain' in [systemProperties]
[DEBUG]Searching for key 'spring.liveBeansView.mbeanDomain' in [systemEnvironment]
[DEBUG]Could not find key 'spring.liveBeansView.mbeanDomain' in any property source. Returning [null]
[DEBUG]Returning cached instance of singleton bean 'myCar'
Putting the keys in the car ignition
Starting the diesel engine
Starting the car
```

Instances of the two references are created in the context file myCar and myMotor.
First the MotorDiesel constructor is executed and the creation of the instance is finished.

Autowiring is then produced by the builder and the MyMotor bean is injected into MyCar:
```shell
Autowiring by type from bean name 'myCar' via constructor to bean named 'myMotor'
```

Curious things in these traces is that this time the engine builder of the MotorDiesel class is first run.

And for now that's all.



