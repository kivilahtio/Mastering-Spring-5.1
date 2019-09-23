package fi.solita.kivilahtio.controller

import org.junit.Rule
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import fi.solita.kivilahtio.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import spock.lang.Specification;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LoadContextTest extends Specification {

	@Rule
	public TestWatcher watchman = new TestWatcher() {
		@Override
		protected void failed(Throwable e, Description description) {
			System.out.println(description + e);
		}

		@Override
		protected void succeeded(Description description) {
			System.out.println(description);
		}
	};

	@Autowired
	private MockMvc mvc

	def "when context is loaded then all expected beans are created"() {
		expect: "the WebController is created"
		mvc
	}
}

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BasicControllerIT {

	@Autowired
	private TestRestTemplate template;

	@Test
	public void welcome() throws Exception {
		ResponseEntity<String> response = template.getForEntity("/welcome", String.class);
		assertThat(response.getBody(), equalTo("Hello World"));
	}

	@Test
	public void welcomeWithObject() throws Exception {
		ResponseEntity<String> response = template.getForEntity("/welcome-with-object", String.class);
		assertThat(response.getBody(), containsString("Hello World"));
	}

	@Test
	public void welcomeWithParameter() throws Exception {
		ResponseEntity<String> response = template.getForEntity("/welcome-with-parameter/name/Buddy", String.class);
		assertThat(response.getBody(), containsString("Hello World, Buddy"));
	}
}