package com.adriano.crudsProjeto.exceptions;

import com.adriano.crudsProjeto.data.dto.MessageDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalHandlerExceptions {
    private static final Logger logger = LoggerFactory.getLogger(GlobalHandlerExceptions.class);

    @ExceptionHandler(CommonsExceptionsPacientes.class)
    protected ResponseEntity<MessageDTO> handlerCommonExceptions(CommonsExceptionsPacientes exception){
        logger.error("[error]: "+exception);
        return exception.getMessageError();
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<MessageDTO> handlerException(Exception ex){
        logger.error("[error]: "+ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new MessageDTO("Exeção não tratada"+ ex.toString(), "gestão médica")
        );
    }

    @ExceptionHandler(CommonsExceptionsConsulta.class)
    protected ResponseEntity<MessageDTO> handlerCommonsExceptionsConsulta(CommonsExceptionsConsulta exception){
        logger.error("[error]: "+exception);
        return exception.getMessageError();
    }

    @ExceptionHandler(CommonsExceptionsMedicos.class)
    protected ResponseEntity<MessageDTO> handlerCommonsExceptionsMedico(CommonsExceptionsMedicos exception){
        logger.error("[error]: "+exception);
        return exception.getMessageError();
    }
}
