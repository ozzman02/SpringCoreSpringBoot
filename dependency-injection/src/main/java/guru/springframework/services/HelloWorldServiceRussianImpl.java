package guru.springframework.services;

public class HelloWorldServiceRussianImpl implements HelloWorldService {

	@Override
	public String getGreeting() {
		return "Привет мир";
	}

}
