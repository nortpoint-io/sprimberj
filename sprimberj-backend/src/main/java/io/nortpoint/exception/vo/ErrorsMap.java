package io.nortpoint.exception.vo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

class ErrorsMap {

	private Map<String, List<FieldMessage>> errors = new HashMap<>(16);

	private List<String> messages = new LinkedList<>();

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	@JsonAnySetter
	public void put(String key, List<FieldMessage> value) {
		errors.put(key, value);
	}

	@JsonAnyGetter
	public Map<String, List<FieldMessage>> getErrors() {
		return errors;
	}

	public void addFieldMessage(String field, String message) {
		FieldMessage fm = new FieldMessage(field, message);
		List<FieldMessage> fmList = errors.get(fm.getField());
		if (fmList == null) {
			fmList = new LinkedList<>();
		}
		fmList.add(fm);
		errors.put(field, fmList);
	}

	public void addMessage(String message) {
		messages.add(message);
	}

}