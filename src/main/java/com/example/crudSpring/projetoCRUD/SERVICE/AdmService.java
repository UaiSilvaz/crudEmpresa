package com.example.crudSpring.projetoCRUD.SERVICE;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.crudSpring.projetoCRUD.ENTITY.Adm;
import com.example.crudSpring.projetoCRUD.REPOSITORY.AdmRepository;

@Service
public class AdmService {

    private final AdmRepository admRepository;

    public AdmService(AdmRepository admRepository) {
        this.admRepository = admRepository;
    }

    public List<Adm> findAll() {
        return admRepository.findAll();
    }

    public Adm cadastrarAdm(Adm dadosAdm) {
        return admRepository.save(dadosAdm);
    }

    public void deletarAdm(Long id) {
        admRepository.deleteById(id);
    }

    public Optional<Adm> buscarPorId(Long id) {
        return admRepository.findById(id);
    }

    public Adm editarAdm(Long id, Adm dadosAtualizados) {
        Adm admBuscada = buscarPorId(id).orElseThrow(() -> new IllegalArgumentException("Adm não encontrada"));
        admBuscada.setNome(dadosAtualizados.getNome());
        admBuscada.setEmail(dadosAtualizados.getEmail());
        admBuscada.setLogin(dadosAtualizados.getLogin());
        admBuscada.setSenha(dadosAtualizados.getSenha());
        admBuscada.setTelefone(dadosAtualizados.getTelefone());
        return admRepository.save(admBuscada);
    }

    public List<Adm> buscarAdmPorNome(String nome) {
        return admRepository.findByNomeContainingIgnoreCase(nome);
    }
}
