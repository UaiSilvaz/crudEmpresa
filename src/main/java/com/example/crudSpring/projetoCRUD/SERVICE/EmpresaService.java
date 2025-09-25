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
        // .orElseThrow() //lança uma exceção se o valor não estiver presente

        Empresa empresaBuscada = buscarPorId(id).orElseThrow(
                () -> new IllegalArgumentException("Empresa não encontrada"));
        // Set e Get setar os novos dados
        // O método getter (get) retorna o valor de um atributo, enquanto o método
        // setter (set) permite atribuir um novo valor a ele
        empresaBuscada.setNome(dadosAtualizados.getNome());
        empresaBuscada.setCnpj(dadosAtualizados.getCnpj());
        empresaBuscada.setRamo(dadosAtualizados.getRamo());

        return empresaRepository.save(empresaBuscada);
        // save -> salva as alterações no banco de dados

    }

    public List<Empresa> buscarEmpresaPorNome(String nome_empresa) {
        return empresaRepository.findByNomeContainingIgnoreCase(nome_empresa);
    }
}
// PESQUISE PROGRAMAÇÃO ESTRUTURADA
// BANCO
// SELECTS OU INSERTS OU ALTER TABLE OU DELETE
// SELECT * FROM EMPRESA (SELECT REALIZA LISTAGEM DE DADOS)
// -> RETORNA TODOS OS DADOS DA EMPRESA (* == TUDO DA TABELA)

// 1° 2° FROM 3°
// SELECT (QUAIS OS DADOS QUE EU QUERO RETORNAR?) (ONDE DESEJA EXECUTAR O
// COMANDO?)

// WHERE nome_empresa = 'Nome da Empresa'; ????????????????????
