package com.adriano.crudsProjeto.repository;

import com.adriano.crudsProjeto.data.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    public Medico findBycrm(String crm);
}
