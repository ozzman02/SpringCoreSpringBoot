package guru.springframework;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import guru.springframework.services.HelloWorldService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/helloworld-config.xml", "classpath*:/spring/spanish-hello-world.xml"})
public class SpanishIntegrationTest {

	@Autowired
	HelloWorldService helloWorldService;
	
	@Test
	public void testHelloWorld() {
		String greeting = helloWorldService.getGreeting();
		assertEquals("Hola Mundo !!!", greeting);
	}

}
