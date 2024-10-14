package com.adriano.crudsProjeto.exceptions;

import com.adriano.crudsProjeto.data.dto.MessageDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Serial;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class CommonsExceptionsMedicos extends RuntimeException {
    @Serial
    private static final long serialVersionUID =  -44445544;

    protected HttpStatus status;
    protected String text;
    protected String key;

    public ResponseEntity<MessageDTO> getMessageError(){
        return ResponseEntity.status(status).body(new MessageDTO(text, key));
    }
}
