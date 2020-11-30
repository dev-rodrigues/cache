package com.gasparbarancelli.cache.repository;

import com.gasparbarancelli.cache.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}
