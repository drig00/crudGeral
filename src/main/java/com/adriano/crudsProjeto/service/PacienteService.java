package com.adriano.crudsProjeto.service;

import com.adriano.crudsProjeto.data.dto.ConsultaDTO;
import com.adriano.crudsProjeto.data.dto.PacienteDTO;
import com.adriano.crudsProjeto.data.model.Consulta;
import com.adriano.crudsProjeto.data.model.Paciente;
import com.adriano.crudsProjeto.dozer.DozerConverter;
import com.adriano.crudsProjeto.exceptions.CommonsExceptionsPacientes;
import com.adriano.crudsProjeto.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {
    @Autowired
    PacienteRepository pacienteRepository;
    @Autowired
    ConsultaService consultaService;
    //create paciente
    public PacienteDTO savePaciente(PacienteDTO paciente){
        if(paciente.getCpf().length() != 11){
            throw new CommonsExceptionsPacientes(
                    HttpStatus.BAD_REQUEST,
                    "você digitou um cpf ou com mais de 11 caracteres ou com menos",
                    "crudPaciente.service.badRequest"
            );
        }

        if(paciente.getContact().length() != 11){
            throw new CommonsExceptionsPacientes(
                    HttpStatus.BAD_REQUEST,
                    "você digitou um telefone ou com mais de 11 caracteres ou com menos",
                    "crudPaciente.service.badRequest"
            );
        }
        if(paciente.getName().length()>150){
            throw new CommonsExceptionsPacientes(HttpStatus.BAD_REQUEST,
                    "você passou do limite de caracteres permitidos para nomes",
                    "crudPaciente.service.badRequest");
        }
        var entity = DozerConverter.parseObject(paciente, Paciente.class);
        var entityDTO = pacienteRepository.save(entity);
        return DozerConverter.parseObject(entityDTO, PacienteDTO.class);
    }
    //read paciente
    public List<PacienteDTO> listAll(){
        return DozerConverter.parseListObjects(
                pacienteRepository.findAll(), PacienteDTO.class
        );
    }
    //update paciente
    public PacienteDTO updatePaciente(long idOldPaciente, PacienteDTO paciente) {
        var newPaciente = DozerConverter.parseObject(paciente, Paciente.class);
        Paciente oldPaciente = pacienteRepository.findById(idOldPaciente).get();
        oldPaciente.setName(newPaciente.getName());
        oldPaciente.setAdress(newPaciente.getAdress());
        oldPaciente.setContact(newPaciente.getContact());
        oldPaciente.setCpf(newPaciente.getCpf());
        pacienteRepository.save(oldPaciente);
        return DozerConverter.parseObject(oldPaciente, PacienteDTO.class);
    }

    //delete Paciente
    public void deletePaciente(long id){
        pacienteRepository.deleteById(id);
    }

    //encontrar paciente por id
    public PacienteDTO findPaciente(long id){
        var entity = pacienteRepository.findById(id);
        if(entity.isEmpty()){
            throw new CommonsExceptionsPacientes(HttpStatus.NOT_FOUND,
                    "Usuário não encontrado no banco",
                    "crud.paciente.notfound");
        }
        return DozerConverter.parseObject(entity.get(), PacienteDTO.class);
    }

    public List<ConsultaDTO> getConsultasPaciente(long id){
        var paciente = pacienteRepository.findById(id); //precisa ser var porque ela pode ser null e assim é possível capturar o erro
        if(paciente.isEmpty()){
            throw new CommonsExceptionsPacientes(HttpStatus.BAD_REQUEST,
                    "Paciente não existe no banco",
                    "Paciente.service.badRequest");
        }
        return DozerConverter.parseListObjects(paciente.get().getConsultas(), ConsultaDTO.class);
    }

}
