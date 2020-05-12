package ceq.maven;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class MainTest {
	
	@BeforeAll
	public static void startServer() {
		String[] args = {};
		Main.main(args );
	}

	@Test
	public void testIndex() {
		String url = "http://localhost:8080/";
		ResponseEntity<String> entity = new TestRestTemplate().getForEntity(url, String.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
	}

	@Test
	public void testHola() {
		String url = "http://localhost:8080/hola";
		ResponseEntity<String> entity = new TestRestTemplate().getForEntity(url, String.class);
		assertEquals("Hola", entity.getBody());
	}

	@Test
	public void testThyme() {
		String url = "http://localhost:8080/thyme";
		ResponseEntity<String> entity = new TestRestTemplate().getForEntity(url, String.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
	}

	@Test
	public void testDbmem() {
		String url = "http://localhost:8080/dbmem";
		ResponseEntity<Boolean> entity = new TestRestTemplate().getForEntity(url, Boolean.class);
		assertEquals(true, entity.getBody());
	}
	
	@Test
	@SuppressWarnings({ "rawtypes"})
	public void testPersons() {
		String url = "http://localhost:8080/persons";
		ResponseEntity<Set> entity = new TestRestTemplate().getForEntity(url, Set.class);
		assertTrue(entity.getBody().size() > 0);
	}

	@Test
	public void testUsers() {
		String url;
		
		url = "http://localhost:8080/users/jaime";
		ResponseEntity<String> entity = new TestRestTemplate().getForEntity(url, String.class);
		assertEquals(">jaime", entity.getBody());

		url = "http://localhost:8080/users/jaime/customers";
		entity = new TestRestTemplate().getForEntity(url, String.class);
		assertEquals("[\"Hello jaime\",\"You jaime\"]", entity.getBody());
	}

}
