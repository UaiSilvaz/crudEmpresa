package com.example.crudSpring.projetoCRUD.CONTROLLER;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.crudSpring.projetoCRUD.ENTITY.Empresa;
import com.example.crudSpring.projetoCRUD.SERVICE.EmpresaService;

@Controller
@RequestMapping("/empresaCTR") // localhost:8080/empresa
public class EmpresaController {

    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService ligacaoEmpresaService) {
        empresaService = ligacaoEmpresaService;
    }

    // chamada para listar todas as empresas

    @GetMapping("/listarTodasEmpresas")
    public String listarEmpresas(Model oModel) {
        oModel.addAttribute("empresas", empresaService.findAll());
        return "listarEmpresas"; // nome do html
    }

    // ligação com a classe service,chama a classe, Processe de instanciação de uma
    // classe. POO

    // Front = pag web + timlifht -> CTR -> SER -> REP -> BANCO
    // chamadas/metodos da ctr
    // metodo de chamar formulario de cadastro
    // mapeamento http
    @GetMapping("/viewCadEmpresa") // class //nome de acesso
    public String mostrarFormCadastro(Model oModel) {
        oModel.addAttribute("empresa", new Empresa());
        return "cadastroEmpresa"; // nome do html
    }

    // model = é classe que se comunica com o html, recebe os dados do frontend/
    // gerencia
    // OModel = nome do objeto que instancia a classe model

    @PostMapping("/salvarEmpresa") // parametro
    public String salvarEmpresa(@ModelAttribute Empresa objEmpresa) {
        // chamando o método cadastrar e passando o objeto empresa com os dados que
        // precisam ser salvos
        empresaService.cadastrarEmpresa(objEmpresa);

        return "redirect:/empresaCTR/listarTodasEmpresas"; // redireciona para o formulario

    }

    @GetMapping("/editar/{id}")
    public String formEditar(@PathVariable Long id, Model oModel) {
        Empresa objEmpresa = empresaService.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        oModel.addAttribute("empresaEditar", objEmpresa);
        return "editarEmpresa";

    }

    @PostMapping("/atualizarEmpresa/{id}")
    public String atualizarEmpresa(@PathVariable("id") Long id,
            @ModelAttribute Empresa objEmpresaAtualizada) {
        empresaService.editarDadoEmpresa(id, objEmpresaAtualizada);
        return "redirect:/empresaCTR/listarTodasEmpresas";
    }

    @GetMapping("/deletarEmpresa/{id}")
    public String deletarEmpresa(@PathVariable("id") Long id) {
        empresaService.deletarEmpresa(id);

        return "redirect:/empresaCTR/listarTodasEmpresas";

    }
}

// metodos post, get, put, delete
// notations