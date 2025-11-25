package com.example.crudSpring.projetoCRUD.CONTROLLER;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
