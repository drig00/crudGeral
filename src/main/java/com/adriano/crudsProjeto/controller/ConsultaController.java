package com.adriano.crudsProjeto.controller;

import com.adriano.crudsProjeto.data.dto.ConsultaDTO;
import com.adriano.crudsProjeto.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consulta/crud")
public class ConsultaController {
    @Autowired
    ConsultaService service;

    @GetMapping("/getAllConsultas")
    public List<ConsultaDTO> listAll(){
        return service.listAll();
    }

    @GetMapping("/findConsulta/{id}")
    public ConsultaDTO getConsulta(@PathVariable long id){
        return service.findConsulta(id);
    }

    @PostMapping(value = "/addConsulta", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ConsultaDTO addConsulta(@RequestBody ConsultaDTO consulta){
        return service.saveConsulta(consulta);
    }

    @PatchMapping("/updateConsulta/{id}")
    public ConsultaDTO updateConsulta(@PathVariable long id, @RequestBody ConsultaDTO newConsulta){
        return service.updateConsulta(id, newConsulta);
    }

    @DeleteMapping("/deleteConsulta/{id}")
    public void deleteConsulta(@PathVariable long id){
        service.deleteConsulta(id);
    }
}
