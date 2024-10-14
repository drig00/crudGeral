package com.adriano.crudsProjeto.service;

import com.adriano.crudsProjeto.data.dto.ConsultaDTO;
import com.adriano.crudsProjeto.data.dto.MedicoDTO;
import com.adriano.crudsProjeto.data.dto.PacienteDTO;
import com.adriano.crudsProjeto.data.model.Consulta;
import com.adriano.crudsProjeto.data.model.Medico;
import com.adriano.crudsProjeto.data.model.Paciente;
import com.adriano.crudsProjeto.dozer.DozerConverter;
import com.adriano.crudsProjeto.exceptions.CommonsExceptionsConsulta;
import com.adriano.crudsProjeto.exceptions.CommonsExceptionsPacientes;
import com.adriano.crudsProjeto.repository.ConsultaRepository;
import com.adriano.crudsProjeto.repository.MedicoRepository;
import com.adriano.crudsProjeto.repository.PacienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {
    @Autowired
    ConsultaRepository consultaRepository;
    @Autowired
    MedicoRepository medicoRepository;
    @Autowired
    PacienteRepository pacienteRepository;
    /*
    public ConsultaDTO saveConsulta(ConsultaDTO consultaDTO){
        var entity = DozerConverter.parseObject(consultaDTO, Consulta.class);
        consultaRepository.save(entity);
        return DozerConverter.parseObject(entity, ConsultaDTO.class);
    }
    */
    public ConsultaDTO saveConsulta(ConsultaDTO consultaDTO) {
        // Converter DTO para entidade Consulta
        var entity = DozerConverter.parseObject(consultaDTO, Consulta.class);
        // Verificar se o Médico já existe no banco de dados
        var medico = medicoRepository.findById(entity.getMedico().getId());
        if (medico.isEmpty()) {
            throw new CommonsExceptionsConsulta(HttpStatus.BAD_REQUEST,
                    "Médico inexistente no banco",
                    "Consulta.service.saveConsulta");
        }

        entity.setMedico(medico.get()); // Atribuir o médico gerenciado
        // Verificar se o Paciente já existe no banco de dados
        var paciente = pacienteRepository.findById(entity.getPaciente().getId());
        if (paciente.isEmpty()) {
            throw new CommonsExceptionsConsulta(HttpStatus.BAD_REQUEST,
                    "Paciente inexistente no banco",
                    "Consulta.service.saveConsulta");
        }
        entity.setPaciente(paciente.get());
        // Salvar a entidade Consulta
        consultaRepository.save(entity);

        // Converter de volta para DTO
        return DozerConverter.parseObject(entity, ConsultaDTO.class);
    }

    public void deleteConsulta(long id){
        consultaRepository.deleteById(id);
    }

    public List<ConsultaDTO> listAll(){
        return DozerConverter.parseListObjects(consultaRepository.findAll(), ConsultaDTO.class);
    }

    public ConsultaDTO findConsulta(long id){
        var entity = consultaRepository.findById(id);
        return DozerConverter.parseObject(entity.get(), ConsultaDTO.class);
    }

    public ConsultaDTO updateConsulta(long idOldConsulta, ConsultaDTO consulta){
        Consulta newConsulta= DozerConverter.parseObject(consulta, Consulta.class);
        Consulta oldConsulta = consultaRepository.findById(idOldConsulta).get();
        oldConsulta.setDataConsulta(newConsulta.getDataConsulta());
        oldConsulta.setMedico(newConsulta.getMedico());
        oldConsulta.setPaciente(newConsulta.getPaciente());
        oldConsulta.setMotivo(newConsulta.getMotivo());
        oldConsulta.setHorario(newConsulta.getHorario());
        consultaRepository.save(oldConsulta);
        return DozerConverter.parseObject(oldConsulta, ConsultaDTO.class);
    }


}
