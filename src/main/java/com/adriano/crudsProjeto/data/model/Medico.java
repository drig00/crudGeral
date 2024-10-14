package com.adriano.crudsProjeto.data.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Medico {
    //SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150)
    private String name;
    @Column(nullable = false, length = 9)
    private String crm;
    @Enumerated(EnumType.STRING)
    Especialidade especialidade;
    @Column(length = 11)
    private String contact;

}
