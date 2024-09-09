package com.example.projeto_java.Events;

import java.util.UUID;

public class ItemRegistradoEvent extends BaseEvent<String> {
    public final String nome;
    public final String descricao;
    public final int quantidade;
    public final double valorUnitario;
    public final String modelo;
    public final String marca;

    public ItemRegistradoEvent(UUID id, String nome, String descricao, int quantidade, double valorUnitario, String modelo, String marca) {
        super(id);
        this.nome = nome;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
        this.modelo = modelo;
        this.marca = marca;
    }
}
