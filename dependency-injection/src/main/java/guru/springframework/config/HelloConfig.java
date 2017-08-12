package guru.springframework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import guru.springframework.services.HelloWorldFactory;
import guru.springframework.services.HelloWorldService;
//import guru.springframework.services.HelloWorldServiceEnglishImpl;
//import guru.springframework.services.HelloWorldServiceSpanishImpl;

@Configuration
public class HelloConfig {

	@Bean
	public HelloWorldFactory helloWorldFactory() {
		return new HelloWorldFactory();
	}
	
	@Bean
	@Profile("english")
	public HelloWorldService helloWorldServiceEnglish(HelloWorldFactory factory) {
		return factory.createHelloWorldService("en");
	}
	
	@Bean
	@Profile("spanish")
	public HelloWorldService helloWorldServiceSpanish(HelloWorldFactory factory) {
		return factory.createHelloWorldService("es");
	}
	
	@Bean
	@Profile("french")
	public HelloWorldService helloWorldServiceFrench(HelloWorldFactory factory) {
		return factory.createHelloWorldService("fr");
	}
	
	@Bean
	@Profile("german")
	public HelloWorldService helloWorldServiceGerman(HelloWorldFactory factory) {
		return factory.createHelloWorldService("de");
	}
	
	@Bean
	@Profile("polish")
	public HelloWorldService helloWorldServicePolish(HelloWorldFactory factory) {
		return factory.createHelloWorldService("pl");
	}
	
	@Bean
	@Profile("russian")
	public HelloWorldService helloWorldServiceRussian(HelloWorldFactory factory) {
		return factory.createHelloWorldService("ru");
	}
	
	/*@Bean
	@Profile("english")
	public HelloWorldService helloWorldServiceEnglish() {
		return new HelloWorldServiceEnglishImpl();
	}
	
	@Bean
	@Profile("spanish")
	public HelloWorldService helloWorldServiceSpanish() {
		return new HelloWorldServiceSpanishImpl();
	}*/
	
}
