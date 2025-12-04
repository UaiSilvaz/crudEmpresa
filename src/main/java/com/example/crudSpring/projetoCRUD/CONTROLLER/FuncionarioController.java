package com.example.crudSpring.projetoCRUD.CONTROLLER;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.crudSpring.projetoCRUD.ENTITY.Funcionario;
import com.example.crudSpring.projetoCRUD.SERVICE.EmpresaService;
import com.example.crudSpring.projetoCRUD.SERVICE.FuncionarioService;

@Controller
@RequestMapping("/funcionarioCTR") // localhost:8080/funcionario
public class FuncionarioController {

    @Autowired
    private FuncionarioService ligacaoFuncionarioService;
    @Autowired
    private EmpresaService ligacaoEmpresaService;

    @GetMapping("/listarFunc")
    public String listarTodosFuncionario(Model oModel) {

        oModel.addAttribute("funcionarios", ligacaoFuncionarioService.listarTodosFuncionarios());

        oModel.addAttribute("empresa", ligacaoEmpresaService.findAll());

        return "listarFuncionarios"; // nome do html
    }

    @GetMapping("/formFuncionario")
    public String mostrarFormCadastro(Model oModel) {

        oModel.addAttribute("funcionario", new Funcionario());
        oModel.addAttribute("empresas", ligacaoEmpresaService.findAll());
        return "cadastrarFuncionario"; // nome do html
    }

    @PostMapping("/salvarFuncionario")
    public String cadastrarFuncionario(@ModelAttribute Funcionario objFuncionario) {
        // TODO: process POST request

        ligacaoFuncionarioService.cadastrarFuncionario(objFuncionario);
        return "redirect:/funcionarioCTR/listarFunc";
    }

    @GetMapping("/deletarFuncionario/{id}")
    public String deletarFuncionario(@PathVariable("id") Long id) {
        ligacaoFuncionarioService.deletarFuncionario(id);

        return "redirect:/funcionarioCTR/listarFunc";

    }

    @GetMapping("/editarFunc/{id}")
    public String formEditarFunc(@PathVariable Long id, Model oModel) {
        Funcionario objFuncionario = ligacaoFuncionarioService.buscarFuncionarioPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        oModel.addAttribute("funcionarioEditar", objFuncionario);
        oModel.addAttribute("empresas", ligacaoEmpresaService.findAll());
        return "editarFuncionario";

    }

    @PostMapping("/atualizarFuncionario/{id}")
    public String atualizarFuncionario(@PathVariable("id") Long id,
            @ModelAttribute Funcionario objFuncionarioAtualizada) {
        ligacaoFuncionarioService.atualizarFuncionario(id, objFuncionarioAtualizada);
        return "redirect:/funcionarioCTR/listarFunc";
    }

    @GetMapping("/formBuscarNome")
    public String mostrarFormBuscaFuncionario(Model oModel) {
        return "buscarFuncionario";
    }

    @GetMapping("/buscarFuncionarioPorNome")
    public String executarBuscaPorNome(@RequestParam("nome") String nome_funcionario, Model oModel) {
        // O método .isEmpty() retorna true se a string estiver vazia ("").
        // O ! (negação) inverte isso, então a condição significa:
        // "A string não está vazia".
        // Mas se nome_funcionario fosse null ou "vazio" = " ", o bloco não seria
        // executado.
        if (nome_funcionario != null && !nome_funcionario.isEmpty()) {
            oModel.addAttribute("funcionarioNome",
                    ligacaoFuncionarioService.buscarFuncionarioPorNome(nome_funcionario));
            // ligacaoFuncionarioService.buscarFuncionarioPorNome(nome_funcionario) → busca
            // o funcionário no banco
            // de dados pelo nome.

            // oModel.addAttribute(...) → envia o resultado da busca para a página.

            // RESUMO
            // "Coloca no modelo o resultado da busca do funcionário pelo nome, para mostrar
            // na
            // tela."
        }

        return "buscarFuncionario";
    }
}