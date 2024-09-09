package com.example.projeto_java.Domain;

import com.example.projeto_java.Command.AtualizarValorCommand;
import com.example.projeto_java.Command.RegistrarItemCommand;
import com.example.projeto_java.Events.ItemRegistradoEvent;
import com.example.projeto_java.Events.ValorAtualizadoEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.util.UUID;

@Entity
@Aggregate
public class Item {

    private static final Logger logger = LoggerFactory.getLogger(Item.class);

    @AggregateIdentifier
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String nome;
    private String descricao;
    private int quantidade;
    private Double valorUnitario;
    private String modelo;
    private String marca;

    public Item() {}

    @CommandHandler
    public Item(RegistrarItemCommand itemCommand) {

        if (!valida(itemCommand)) {
            throw new IllegalArgumentException("Comando inválido");
        }
        AggregateLifecycle.apply(new ItemRegistradoEvent(
                itemCommand.id,
                itemCommand.nome,
                itemCommand.descricao,
                itemCommand.quantidade,
                itemCommand.valorUnitario,
                itemCommand.modelo,
                itemCommand.marca
        ));
    }

    @EventSourcingHandler
    protected void on(ItemRegistradoEvent itemEvent) {
        this.id = itemEvent.id;
        this.nome = itemEvent.nome;
        this.descricao = itemEvent.descricao;
        this.quantidade = itemEvent.quantidade;
        this.valorUnitario = itemEvent.valorUnitario;
        this.modelo = itemEvent.modelo;
        this.marca = itemEvent.marca;
    }

    @CommandHandler
    public void handle(AtualizarValorCommand atualizarValorCommand) {
        if (atualizarValorCommand.valorUnitario < 0) {
            throw new IllegalArgumentException("Valor unitário não pode ser negativos");
        }
        AggregateLifecycle.apply(new ValorAtualizadoEvent(
                atualizarValorCommand.id,
                atualizarValorCommand.valorUnitario
        ));
    }

    @EventSourcingHandler
    protected void on(ValorAtualizadoEvent evento) {
        this.valorUnitario = evento.valorUnitario;
    }

    // Métodos getters e setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public boolean valida(RegistrarItemCommand itemCommand) {
        boolean valido = true;

        if (itemCommand.nome == null || itemCommand.nome.isEmpty()) {
            logger.error("Nome não pode ser nulo ou vazio");
            valido = false;
        }
        if (itemCommand.descricao == null || itemCommand.descricao.isEmpty()) {
            logger.error("Descrição não pode ser nula ou vazia");
            valido = false;
        }
        if (itemCommand.quantidade < 0) {
            logger.error("Quantidade não pode ser negativa");
            valido = false;
        }
        if (itemCommand.valorUnitario < 0) {
            logger.error("Valor Unitário não pode ser negativo");
            valido = false;
        }
        if (itemCommand.modelo == null || itemCommand.modelo.isEmpty()) {
            logger.error("Modelo não pode ser nulo ou vazio");
            valido = false;
        }
        if (itemCommand.marca == null || itemCommand.marca.isEmpty()) {
            logger.error("Marca não pode ser nula ou vazia");
            valido = false;
        }

        return valido;
    }

    public boolean rastrearObjetoIgual(String modelo, String marca, Item novoItem) {
        if (novoItem.marca.equalsIgnoreCase(marca) &&
                novoItem.modelo.equalsIgnoreCase(modelo)) {
            logger.info("Item já existente!");
            return true;
        } else {
            return false;
        }
    }

    public String apresentar() {
        StringBuilder sb = new StringBuilder();
        sb.append("Item [");
        sb.append("ID=").append(id).append(", ");
        sb.append("Nome='").append(nome).append("', ");
        sb.append("Descrição='").append(descricao).append("', ");
        sb.append("Quantidade=").append(quantidade).append(", ");
        sb.append("Valor Unitário=").append(valorUnitario);
        sb.append("]");
        return sb.toString();
    }

    public boolean verificaQuantidade() {
        return this.quantidade >= 0;
    }

    public boolean verificaValorUnitario() {
        return this.valorUnitario >= 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        return id.equals(item.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
