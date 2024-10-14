package com.adriano.crudsProjeto.controller;

import com.adriano.crudsProjeto.data.dto.MedicoDTO;
import com.adriano.crudsProjeto.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medico/crud")
public class MedicoController {
    @Autowired
    MedicoService service;

    @PostMapping("/addMedico")
    public MedicoDTO addMedico(@RequestBody MedicoDTO medico){
        return service.saveMedico(medico);
    }

    @GetMapping("/getAllMedicos")
    public List<MedicoDTO> allMedicos(){
        return service.listAll();
    }

    @DeleteMapping("/deleteMedico/{id}")
    public void deleteMedico(@PathVariable long id){
        service.deleteMedico(id);
    }

    @PatchMapping("/updateMedico/{id}")
    public MedicoDTO updateMedico(@PathVariable long id, @RequestBody MedicoDTO newMedico){
        return service.updateMedico(id, newMedico);
    }

    @GetMapping("/findMedico/{id}")
    public MedicoDTO findPaciente(@PathVariable long id){
        return service.findMedico(id);
    }
}
