package io.nortpoint.exception;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import io.nortpoint.exception.vo.Errors;

@ControllerAdvice
public class ControllerExceptionHandler {

	private static final Logger LOG = Logger.getLogger(ControllerExceptionHandler.class.getName());

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = { EntityNotFoundException.class })
	public void entityNotFoundException(Exception e) {
		LOG.log(Level.SEVERE, e.getMessage(), e);
	}

	@ExceptionHandler(value = { MethodArgumentNotValidException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody Errors processValidationError(MethodArgumentNotValidException e) {
		BindingResult bindingResult = e.getBindingResult();
		Errors errors = new Errors();
		bindingResult.getFieldErrors().stream().forEach(fieldError -> {
			errors.addFieldMessage(fieldError.getField(), fieldError.getDefaultMessage());
		});
		bindingResult.getGlobalErrors().stream().forEach(error -> {
			errors.addMessage(error.getDefaultMessage());
		});
		return errors;
	}

}