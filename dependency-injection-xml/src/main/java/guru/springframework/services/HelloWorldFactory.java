package guru.springframework.services;

public class HelloWorldFactory {
	
	public HelloWorldService createHelloWorldService(String language) {
		
		HelloWorldService helloWorldService = null;
		
		switch (language) {
			case "en":
				helloWorldService = new HelloWorldServiceEnglishImpl();
				break;
			case "es":
				helloWorldService = new HelloWorldServiceSpanishImpl();
				break;
			case "fr":
				helloWorldService = new HelloWorldServiceFrenchImpl();
				break;
			case "de":
				helloWorldService = new HelloWorldServiceGermanImpl();
				break;
			case "pl":
				helloWorldService = new HelloWorldServicePolishImpl();
				break;
			case "ru":
				helloWorldService = new HelloWorldServiceRussianImpl();
				break;
			default: new HelloWorldServiceEnglishImpl();
		}
		
		return helloWorldService;
	}
}
