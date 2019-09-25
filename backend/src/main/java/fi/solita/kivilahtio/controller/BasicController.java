package fi.solita.kivilahtio.controller;

import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import fi.solita.kivilahtio.bean.WelcomeBean;

import javax.annotation.security.RolesAllowed;

@RestController
public class BasicController {
	private static Logger logger = LogManager.getLogger(BasicController.class);

	@Autowired
	private MessageSource messageSource;

	@GetMapping("/welcome")
	@RolesAllowed({})
	public String welcome() {
		logger.debug("This is a debug message");
		logger.info("This is an info message");
		logger.warn("This is a warn message");
		logger.error("This is an error message");
		logger.fatal("This is a fatal message");

		return "Hello World";
	}

	@GetMapping("/welcome-with-object")
	@RolesAllowed({"ALPHA"})
	public WelcomeBean welcomeWithObject() {
		return new WelcomeBean("Hello World");
	}

	private static final String helloWorldTemplate = "Hello World, %s!";

	@GetMapping("/welcome-with-parameter/name/{name}")
	@RolesAllowed({"BETA"})
	public WelcomeBean welcomeWithParameter(@PathVariable String name) {
		return new WelcomeBean(String.format(helloWorldTemplate, name));
	}

	@GetMapping("/welcome-internationalized")
	@RolesAllowed({"GAMMA"})
	public String msg(@RequestHeader(value = "Accept-Language", required = false) Locale locale) {
		return messageSource.getMessage("welcome.message", null, locale);
	}
}