package com.example.crudSpring.projetoCRUD.SERVICE;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.crudSpring.projetoCRUD.ENTITY.Funcionario;
import com.example.crudSpring.projetoCRUD.REPOSITORY.FuncionarioRepository;

@Service
public class FuncionarioService {
    //// Injeta automaticamente o repositório de funcionários para acessar o banco
    //// de dados
    // Indica ao Spring que ele deve injetar automaticamente o repositório de
    // funcionários
    @Autowired
    private FuncionarioRepository ligacaoFuncionarioRepository;

    // Método que retorna uma lista com todos os funcionários cadastrados no banco
    // de dados
    public List<Funcionario> listarTodosFuncionarios() {
        // Usa o método findAll() do repositório para buscar todos os registros
        return ligacaoFuncionarioRepository.findAll();
    }

    // Método que cadastra (salva) um novo funcionário no banco de dados
    public Funcionario cadastrarFuncionario(Funcionario dadosFuncioario) {
        // Usa o método save() do repositório para gravar o novo funcionário
        return ligacaoFuncionarioRepository.save(dadosFuncioario);
    }

    public Optional<Funcionario> buscarFuncionarioPorId(Long id) {
        return ligacaoFuncionarioRepository.findById(id);
    }

    // metodo void não retorna nada apenas ira deletar o id do funcionario
    public void deletarFuncionario(Long id) {
        ligacaoFuncionarioRepository.deleteById(id);
    }

    // fala ao springframework
    @Transactional
    public void atualizarFuncionario(
            Long id, Funcionario dadosAtualizados) {
        Funcionario objFuncionario = buscarFuncionarioPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Funcionário não encontrado"));

        objFuncionario.setNome(dadosAtualizados.getNome());
        objFuncionario.setCargo(dadosAtualizados.getCargo());
        objFuncionario.setSalario(dadosAtualizados.getSalario());
        objFuncionario.setIdentificadorEmpresa(dadosAtualizados.getIdentificadorEmpresa());

    }
}