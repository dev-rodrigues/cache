package com.gasparbarancelli.cache;

import com.gasparbarancelli.cache.model.Cargo;
import com.gasparbarancelli.cache.model.Cliente;
import com.gasparbarancelli.cache.model.Funcionario;
import com.gasparbarancelli.cache.service.ClienteService;
import com.gasparbarancelli.cache.service.FuncionarioService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@SpringBootApplication
@EnableCaching
public class CacheApplication implements ApplicationRunner {

    private final ClienteService clienteService;
    private final FuncionarioService funcionarioService;

    public CacheApplication(ClienteService clienteService, FuncionarioService funcionarioService) {
        this.clienteService = clienteService;
        this.funcionarioService = funcionarioService;
    }

    public static void main(String[] args) {
        SpringApplication.run(CacheApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        var clienteGaspar = new Cliente("Gaspar");
        var clienteAna = new Cliente("Ana");
        clienteService.save(clienteGaspar);
        clienteService.save(clienteAna);

        printOptional.accept(clienteService.findById(1L));
        printOptional.accept(clienteService.findById(1L));
        printOptional.accept(clienteService.findById(2L));
        printOptional.accept(clienteService.findById(2L));
        printList.accept(clienteService.findAll());
        printList.accept(clienteService.findAll());

        clienteService.removeById(1L);
        printList.accept(clienteService.findAll());
        printOptional.accept(clienteService.findById(2L));
        printOptional.accept(clienteService.findById(2L));
        printList.accept(clienteService.findAll());

        var clienteRodrigo = new Cliente("Rodrigo");
        clienteService.save(clienteRodrigo);

        printOptional.accept(clienteService.findById(2L));
        printOptional.accept(clienteService.findById(2L));
        printList.accept(clienteService.findAll());
        printList.accept(clienteService.findAll());

        Thread.sleep(3000);
        printOptional.accept(clienteService.findById(2L));
        printList.accept(clienteService.findAll());

        var funcionarioGaspar = new Funcionario("Gaspar", Cargo.VENDEDOR);
        var funcionarioAna = new Funcionario("Ana", Cargo.DIRETOR);
        funcionarioService.saveAll(funcionarioGaspar, funcionarioAna);

        printOptional.accept(funcionarioService.findById(1L));
        printOptional.accept(funcionarioService.findById(1L));
        printOptional.accept(funcionarioService.findById(2L));
        printOptional.accept(funcionarioService.findById(2L));
        printOptional.accept(funcionarioService.findById(3L));
        printList.accept(funcionarioService.findAll());
        printList.accept(funcionarioService.findAll());

        var funcionarioRodrigo = new Funcionario("Rodrigo", Cargo.GERENTE);
        funcionarioService.save(funcionarioRodrigo);
        printOptional.accept(funcionarioService.findById(3L));
        printList.accept(funcionarioService.findAll());
        printList.accept(funcionarioService.findAll());
        printOptional.accept(funcionarioService.findById(3L));

        Thread.sleep(3000);
        printOptional.accept(funcionarioService.findById(3L));
        printList.accept(funcionarioService.findAll());
    }

    private final Consumer<Optional<?>> printOptional = cliente -> cliente.ifPresent(System.out::println);

    private final Consumer<List<?>> printList = list -> list.forEach(System.out::println);

}
