package ceq.maven;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

public class MainTest {
	
	@BeforeAll
	public static void startServer() {
		String[] args = {};
		Main.main(args );
	}

	@Test
	public void testMain() {
		String url = "http://localhost:8080/";
		ResponseEntity<String> entity = new TestRestTemplate().getForEntity(url, String.class);
		assertEquals("Hola", entity.getBody());
	}

}
