package io.nortpoint.exception.vo;

public class Errors {

	private ErrorsMap errors = new ErrorsMap();

	public ErrorsMap getErrors() {
		return errors;
	}

	public void setErrors(ErrorsMap errors) {
		this.errors = errors;
	}

	public void addFieldMessage(String field, String message) {
		errors.addFieldMessage(field, message);
	}

	public void addMessage(String message) {
		errors.addMessage(message);
	}

}