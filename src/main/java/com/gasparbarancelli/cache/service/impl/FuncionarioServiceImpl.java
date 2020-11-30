package com.gasparbarancelli.cache.service.impl;

import com.gasparbarancelli.cache.framework.CrudServiceImpl;
import com.gasparbarancelli.cache.model.Funcionario;
import com.gasparbarancelli.cache.repository.FuncionarioRepository;
import com.gasparbarancelli.cache.service.FuncionarioService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioServiceImpl extends CrudServiceImpl<Funcionario, Long> implements FuncionarioService {

    private final FuncionarioRepository repository;

    public FuncionarioServiceImpl(FuncionarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public JpaRepository<Funcionario, Long> getRepository() {
        return repository;
    }

}
