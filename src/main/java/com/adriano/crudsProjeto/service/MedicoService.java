package com.adriano.crudsProjeto.service;

import com.adriano.crudsProjeto.data.dto.MedicoDTO;
import com.adriano.crudsProjeto.data.model.Medico;
import com.adriano.crudsProjeto.dozer.DozerConverter;
import com.adriano.crudsProjeto.exceptions.CommonsExceptionsMedicos;
import com.adriano.crudsProjeto.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoService {
    @Autowired
    MedicoRepository medicoRepository;
    //create paciente
    public MedicoDTO saveMedico(MedicoDTO medico){
        if(medico.getCrm().length() != 9){
            throw new CommonsExceptionsMedicos(
                    HttpStatus.BAD_REQUEST,
                    "você digitou um crm ou com mais de 9 caracteres ou com menos",
                    "crudMedico.service.badRequest"
            );
        }

        if(medico.getContact().length() != 11){
            throw new CommonsExceptionsMedicos(
                    HttpStatus.BAD_REQUEST,
                    "você digitou um telefone ou com mais de 11 caracteres ou com menos",
                    "crudMedico.service.badRequest"
            );
        }
        if(medico.getName().length()>150){
            throw new CommonsExceptionsMedicos(HttpStatus.BAD_REQUEST,
                    "você passou do limite de caracteres permitidos para nomes",
                    "crudPaciente.service.badRequest");
        }
        var entity = DozerConverter.parseObject(medico, Medico.class);
        var entityDTO = medicoRepository.save(entity);
        return DozerConverter.parseObject(entityDTO, MedicoDTO.class);
    }
    //read paciente
    public List<MedicoDTO> listAll(){
        return DozerConverter.parseListObjects(
                medicoRepository.findAll(), MedicoDTO.class
        );
    }
    //update paciente
    public MedicoDTO updateMedico(long idOldMedico, MedicoDTO medicoDTO) {
        var newMedico = DozerConverter.parseObject(medicoDTO, Medico.class);
        Medico oldMedico = medicoRepository.findById(idOldMedico).get();
        oldMedico.setName(newMedico.getName());
        oldMedico.setContact(newMedico.getContact());
        oldMedico.setEspecialidade(newMedico.getEspecialidade());
        oldMedico.setCrm(newMedico.getCrm());
        medicoRepository.save(oldMedico);
        return DozerConverter.parseObject(oldMedico, MedicoDTO.class);
    }

    //delete Paciente
    public void deleteMedico(long id){
        medicoRepository.deleteById(id);
    }

    //encontrar paciente por id
    public MedicoDTO findMedico(long id){
        var entity = medicoRepository.findById(id);
        if(entity.isEmpty()){
            throw new CommonsExceptionsMedicos(HttpStatus.NOT_FOUND,
                    "Usuário não encontrado no banco",
                    "crud.paciente.notfound");
        }
        return DozerConverter.parseObject(entity.get(), MedicoDTO.class);
    }

}
