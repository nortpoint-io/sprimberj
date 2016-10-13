package io.nortpoint.exception.vo;

class FieldMessage {

	private final String field;

	private final String message;

	public FieldMessage(String field, String message) {
		this.field = field;
		this.message = message;
	}

	public String getField() {
		return this.field;
	}

	public String getMessage() {
		return this.message;
	}

	@Override
	public String toString() {
		return "FieldMessage [field=" + field + ", message=" + message + "]";
	}

}