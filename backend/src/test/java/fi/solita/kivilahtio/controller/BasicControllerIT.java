package fi.solita.kivilahtio.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
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
import test.solita.kivilahtio.TestLogger;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
public class BasicControllerIT {

	@Rule
	public TestWatcher watchman = new TestLogger();

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