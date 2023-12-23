package uss.edu.pe.PizarroDelgado.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import lombok.extern.slf4j.Slf4j;
import uss.edu.pe.PizarroDelgado.exceptions.NoDataServiceException;
import uss.edu.pe.PizarroDelgado.exceptions.ValidateServiceException;


@Slf4j
@ControllerAdvice
public class ErrorHandlerConfig extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> all(Exception e,WebRequest request){
		log.error(e.getMessage(),e);
		return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ValidateServiceException.class)
	public ResponseEntity<?> ValidateService(Exception e,WebRequest request){
		log.error(e.getMessage(),e);
		return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(NoDataServiceException.class)
	public ResponseEntity<?> noData(Exception e,WebRequest request){
		log.error(e.getMessage(),e);
		return new ResponseEntity<>(e.getMessage(),HttpStatus.NO_CONTENT);
	}
}
