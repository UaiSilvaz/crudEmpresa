package com.example.crudSpring.projetoCRUD.CONTROLLER;

import org.springframework.stereotype.Controller;

import com.example.crudSpring.projetoCRUD.SERVICE.EmpresaService;

@Controller
public class EmpresaController {

    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService ligacaoEmpresaService) {
        empresaService = ligacaoEmpresaService;
    }
    //ligação com a classe service,chama a classe, Processe de instanciação de uma classe. POO

    //Front = pag web + timlifht -> CTR -> SER -> REP -> BANCO
    //                  chamadas/metodos da ctr


    




}
