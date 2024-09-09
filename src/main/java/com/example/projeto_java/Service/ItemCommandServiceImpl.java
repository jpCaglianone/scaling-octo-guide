package com.example.projeto_java.Service;

import com.example.projeto_java.Command.AtualizarQuantidadeCommand;
import com.example.projeto_java.Command.AtualizarValorCommand;
import com.example.projeto_java.Command.RegistrarItemCommand;
import com.example.projeto_java.Domain.Item;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class ItemCommandServiceImpl implements ItemCommandService {

    private static final Logger logger = LoggerFactory.getLogger(Item.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private final CommandGateway commandGateway;

    public ItemCommandServiceImpl(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    public boolean valida(RegistrarItemCommand itemCommand) {
        boolean valido = true;

        if (itemCommand.getNome() == null || itemCommand.getNome().isEmpty()) {
            logger.error("Nome não pode ser nulo ou vazio");
            valido = false;
        }
        if (itemCommand.getDescricao() == null || itemCommand.getDescricao().isEmpty()) {
            logger.error("Descrição não pode ser nula ou vazia");
            valido = false;
        }
        if (itemCommand.getQuantidade() < 0) {
            logger.error("Quantidade não pode ser negativa");
            valido = false;
        }
        if (itemCommand.getValorUnitario() < 0) {
            logger.error("Valor Unitário não pode ser negativo");
            valido = false;
        }
        if (itemCommand.getModelo() == null || itemCommand.getModelo().isEmpty()) {
            logger.error("Modelo não pode ser nulo ou vazio");
            valido = false;
        }
        if (itemCommand.getMarca() == null || itemCommand.getMarca().isEmpty()) {
            logger.error("Marca não pode ser nula ou vazia");
            valido = false;
        }

        return valido;
    }


    @Transactional
    @Override
    public CompletableFuture<String> cadastrarItem(Item novoItem) {


        if (novoItem.getId() != null && entityManager.find(Item.class, novoItem.getId()) != null) {

            entityManager.merge(novoItem);
            return commandGateway.send(new RegistrarItemCommand(
                    UUID.randomUUID(),
                    novoItem.getNome(),
                    novoItem.getDescricao(),
                    novoItem.getQuantidade(),
                    novoItem.getValorUnitario(),
                    novoItem.getModelo(),
                    novoItem.getMarca()
            ));

        } else {
            entityManager.persist(novoItem);
            return CompletableFuture.completedFuture("Item cadastrado com sucesso");
        }


    }

    @Transactional
    @Override
    public CompletableFuture<String> atualizarQuantidade(UUID id, int quantidade) {
        return commandGateway.send(new AtualizarQuantidadeCommand(
                id,
                quantidade
        ));
    }

    @Transactional
    @Override
    public CompletableFuture<String> atualizarValor(UUID id, double valor) {
        return commandGateway.send(new AtualizarValorCommand(
                id,
                valor
        ));
    }
}
