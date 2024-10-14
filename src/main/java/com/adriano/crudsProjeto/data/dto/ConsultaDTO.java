package com.adriano.crudsProjeto.data.dto;

import com.adriano.crudsProjeto.data.model.Medico;
import com.adriano.crudsProjeto.data.model.Paciente;
import lombok.Data;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
@Data
public class ConsultaDTO {
    private long id;
    private PacienteDTO paciente;
    private MedicoDTO medico;
    private LocalDate dataConsulta;
    private String horario;
    private String motivo;
}
