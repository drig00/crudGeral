package com.adriano.crudsProjeto.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class MessageDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String message;
    private String key;

}

//parece que essa classe é um molde da mensagem que deve ser retornada quando houver algum erro. Ela tem dois atributos
//um para chave e outro para texto da mensagem. Não entendi ainda porque dessa chave mas a mensagem é claramente o texto
//que vai aparecer quando o erro ocorrer. Chuto que essa chave sirva para associar com o status do erro, por exemplo
//2XX, 3XX, 4XX, 5XX e a partir do status desse er