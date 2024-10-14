package com.adriano.crudsProjeto.data.dto;

import com.adriano.crudsProjeto.data.model.Especialidade;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MedicoDTO {
    //SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private Long id;
    private String name;
    private Especialidade especialidade;
    private String crm;
    private String contact;
}
