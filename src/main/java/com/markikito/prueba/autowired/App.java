package com.markikito.prueba.autowired;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        
    	ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        TextEditor te = (TextEditor) context.getBean("textEditor");

        te.spellCheck();    	
    	    	    	    	
    	
    	//SpringApplication.run(App.class, args);                
        
    }
}