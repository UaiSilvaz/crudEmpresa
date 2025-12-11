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

import com.example.crudSpring.projetoCRUD.ENTITY.Adm;
import com.example.crudSpring.projetoCRUD.SERVICE.AdmService;

@Controller
@RequestMapping("/admCTR")
public class AdmController {

    @Autowired
    private AdmService admService;

    @GetMapping("/listarAdm")
    public String listarTodosAdm(Model oModel) {
        oModel.addAttribute("adms", admService.findAll());
        return "listarAdm"; // nome do html a criar se necessário
    }

    @GetMapping("/formAdm")
    public String mostrarFormCadastro(Model oModel) {
        oModel.addAttribute("adm", new Adm());
        return "cadastroAdm"; // nome do html
    }

    @PostMapping("/salvarAdm")
    public String cadastrarAdm(@ModelAttribute Adm objAdm) {
        admService.cadastrarAdm(objAdm);
        return "redirect:/admCTR/listarAdm";
    }

    @GetMapping("/deletarAdm/{id}")
    public String deletarAdm(@PathVariable("id") Long id) {
        admService.deletarAdm(id);
        return "redirect:/admCTR/listarAdm";
    }

    @GetMapping("/editarAdm/{id}")
    public String formEditarAdm(@PathVariable Long id, Model oModel) {
        Adm objAdm = admService.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        oModel.addAttribute("admEditar", objAdm);
        return "editarAdm";
    }

    @PostMapping("/atualizarAdm/{id}")
    public String atualizarAdm(@PathVariable("id") Long id,
            @ModelAttribute Adm objAdmAtualizada) {
        admService.editarAdm(id, objAdmAtualizada);
        return "redirect:/admCTR/listarAdm";
    }

    @GetMapping("/formBuscarNome")
    public String mostrarFormBuscaAdm(Model oModel) {
        return "buscarAdm";
    }

    @GetMapping("/buscarAdmPorNome")
    public String executarBuscaPorNome(@RequestParam("nome") String nome, Model oModel) {
        if (nome != null && !nome.isEmpty()) {
            oModel.addAttribute("admNome", admService.buscarAdmPorNome(nome));
        }
        return "buscarAdm";
    }
}