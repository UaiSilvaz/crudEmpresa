package com.example.crudSpring.projetoCRUD.REPOSITORY;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.crudSpring.projetoCRUD.ENTITY.Adm;

@Repository
public interface AdmRepository extends JpaRepository<Adm, Long> {
    List<Adm> findByNomeContainingIgnoreCase(String nome);

    Adm findByEmail(String email);

    Adm findByLogin(String login);
}
