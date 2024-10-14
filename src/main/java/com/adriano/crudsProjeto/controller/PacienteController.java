package com.adriano.crudsProjeto.controller;

import com.adriano.crudsProjeto.data.dto.ConsultaDTO;
import com.adriano.crudsProjeto.data.dto.PacienteDTO;
import com.adriano.crudsProjeto.data.model.Paciente;
import com.adriano.crudsProjeto.service.ConsultaService;
import com.adriano.crudsProjeto.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paciente/crud")
public class PacienteController {
    @Autowired
    PacienteService pacienteService;



    @PostMapping("/addPaciente")
    public PacienteDTO addPaciente(@RequestBody PacienteDTO paciente){
        return pacienteService.savePaciente(paciente);
    }

    @GetMapping("/getAllPacientes")
    public List<PacienteDTO> allPacientes(){
        return pacienteService.listAll();
    }

    @DeleteMapping("/deletePaciente/{id}")
    public void deletePaciente(@PathVariable long id){
        pacienteService.deletePaciente(id);
    }

    @PatchMapping("/updatePaciente/{id}")
    public PacienteDTO updatePaciente(@PathVariable long id, @RequestBody PacienteDTO newPaciente){
        return pacienteService.updatePaciente(id, newPaciente);
    }

    @GetMapping("/findPaciente/{id}")
    public PacienteDTO findPaciente(@PathVariable long id){
        return pacienteService.findPaciente(id);
    }

    @GetMapping("/consultasPaciente/{idPaciente}")
    public List<ConsultaDTO> consultasPaciente(@PathVariable long idPaciente){
        return pacienteService.getConsultasPaciente(idPaciente);
    }
}
