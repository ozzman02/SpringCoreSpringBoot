package guru.springframework.controllers;

import guru.springframework.services.HelloWorldService;


public class GreetingController {
	
	private HelloWorldService helloWorldService;
	
	private HelloWorldService helloWorldServiceGerman;
	
	private	HelloWorldService helloWorldServiceFrench;
	
	private HelloWorldService helloWorldServicePolish;
	
	public void setHelloWorldService(HelloWorldService helloWorldService) {
		this.helloWorldService = helloWorldService;
	}
	
	public void setHelloWorldServiceGerman(HelloWorldService helloWorldServiceGerman) {
		this.helloWorldServiceGerman = helloWorldServiceGerman;
	}

	public void setHelloWorldServiceFrench(HelloWorldService helloWorldServiceFrench) {
		this.helloWorldServiceFrench = helloWorldServiceFrench;
	}
	
	public void setHelloWorldServicePolish(HelloWorldService helloWorldServicePolish) {
		this.helloWorldServicePolish = helloWorldServicePolish;
	}
	
	public String sayHello() {
		String greeting = helloWorldService.getGreeting(); 
		System.out.println(greeting);
		System.out.println(helloWorldServiceGerman.getGreeting());
		System.out.println(helloWorldServiceFrench.getGreeting());
		System.out.println(helloWorldServicePolish.getGreeting());
		return greeting;
	}
	
}
