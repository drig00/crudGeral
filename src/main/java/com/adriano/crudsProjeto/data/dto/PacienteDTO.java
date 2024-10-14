package com.adriano.crudsProjeto.data.dto;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
@Data
public class PacienteDTO {
    //SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private Long id;
    private String name;
    private String cpf;
    private LocalDate birthDate;
    private String adress;
    private String contact;
}
