package com.example.projeto_java.Command;


import java.util.UUID;

public class RegistrarItemCommand extends BaseCommand<String> {

    public final String nome;
    public final String descricao;
    public final int quantidade;
    public final Double valorUnitario;
    public final String modelo;
    public final String marca;


    public RegistrarItemCommand(UUID id, String nome, String descricao, int quantidade, Double valorUnitario, String modelo, String marca) {
        super(id);
        this.nome = nome;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
        this.modelo = modelo;
        this.marca = marca;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Double getValorUnitario() {
        return valorUnitario;
    }

    public String getModelo() {
        return modelo;
    }

    public String getMarca() {
        return marca;
    }
}