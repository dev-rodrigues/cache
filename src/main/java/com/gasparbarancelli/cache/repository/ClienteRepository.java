package com.gasparbarancelli.cache.repository;

import com.gasparbarancelli.cache.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
