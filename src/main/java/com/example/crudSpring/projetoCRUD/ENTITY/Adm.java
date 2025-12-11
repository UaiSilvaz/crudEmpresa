package com.example.crudSpring.projetoCRUD.ENTITY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_adm")
@NoArgsConstructor
@Getter
@Setter
public class Adm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_adm", nullable = false, unique = true)
    private Long id_adm;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "email", nullable = false, length = 100, unique = true)
    private String email;

    @Column(name = "login", nullable = false, length = 100, unique = true)
    private String login;

    @Column(name = "senha", nullable = false, length = 100)
    private String senha;

    @Column(name = "telefone", nullable = false, length = 100)
    private String telefone;

    public Adm(String nome, String email, String login, String senha, String telefone) {
        this.nome = nome;
        this.email = email;
        this.login = login;
        this.senha = senha;
        this.telefone = telefone;
    }
}
