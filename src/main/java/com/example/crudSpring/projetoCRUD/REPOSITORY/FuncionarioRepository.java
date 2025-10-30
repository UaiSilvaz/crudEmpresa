package com.example.crudSpring.projetoCRUD.REPOSITORY;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.crudSpring.projetoCRUD.ENTITY.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    List<Funcionario> findByNomeContainingIgnoreCase(String nome_funcionario);

    // Após a repository ser criada, é possível utilizá-la na camada de serviço
    // (Service) para realizar operações de CRUD (Create, Read, Update, Delete) e
    // outras consultas personalizadas no banco de dados relacionadas à entidade
    // Funcionario.
}
