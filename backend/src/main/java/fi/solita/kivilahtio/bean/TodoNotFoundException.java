package fi.solita.kivilahtio.bean;

public class TodoNotFoundException extends RuntimeException {
	public TodoNotFoundException(String msg) {
		super(msg);
	}
}