package com.example.projeto_java.Events;

import java.util.UUID;

public class ValorAtualizadoEvent extends BaseEvent<String> {

    public final double valorUnitario;

    public ValorAtualizadoEvent(UUID id, double valorUnitario) {
        super(id);
        this.valorUnitario = valorUnitario;
    }
}