package kz.bitlab.middle02.keycloakservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KeycloakserviceApplicationTests {

	@Test
	void contextLoads() {
		System.out.println("I am testing");
		String text = "2";
		Assertions.assertEquals(text, "2");
	}

}
