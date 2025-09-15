package com.example.crudSpring.projetoCRUD.SERVICE;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.crudSpring.projetoCRUD.ENTITY.Empresa;
import com.example.crudSpring.projetoCRUD.REPOSITORY.EmpresaRepository;

@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    // metodo construtor da classe EmpresaService
    // criando uma ligação cp, a Classe EmpresaRepository

    public EmpresaService(EmpresaRepository ligacaoEmpresaRepository) {
        empresaRepository = ligacaoEmpresaRepository;
    }

    public List<Empresa> findAll() {
        return empresaRepository.findAll();
    }// select * from empresa
     // seleciona todas as empresas do banco de dados
     // em formato de lista

    // empresaRepository: É uma referência a uma instância da classe
    // EmpresaRepository. Esta instância é injetada automaticamente pelo Spring,
    // geralmente no construtor da classe de serviço

    // Object //nome do parametro (formulario dentro da controller)
    public Empresa cadastrarEmpresa(Empresa dadosEmpresa) {
        return empresaRepository.save(dadosEmpresa);
    }// metodo cadastrarEmpresa

    // sem retorno para ver retorno executar novo listar -> frontend
    public void deletarEmpresa(Empresa dadosEmepresa) {
        empresaRepository.delete(dadosEmepresa);
    }// metodo deletarEmpresa

    public Optional<Empresa> buscarPorId(Long id) {
        return empresaRepository.findById(id);

    }

    public Empresa editarDadoEmpresa(Long id, Empresa dadosAtualizados) {
        return null;
    }
}

// BANCO
// SELECTS OU INSERTS OU ALTER TABLE OU DELETE
// SELECT * FROM EMPRESA (SELECT REALIZA LISTAGEM DE DADOS)
// -> RETORNA TODOS OS DADOS DA EMPRESA (* == TUDO DA TABELA)

// 1° 2° FROM 3°
// SELECT (QUAIS OS DADOS QUE EU QUERO RETORNAR?) (ONDE DESEJA EXECUTAR O
// COMANDO?)

// WHERE nome_empresa = 'Nome da Empresa'; ????????????????????
