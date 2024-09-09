package com.example.projeto_java.Command;

import java.util.UUID;

public class AtualizarValorCommand extends BaseCommand<UUID> {

    public final double valorUnitario;

    public AtualizarValorCommand(UUID id, double valorUnitario) {
        super(id);
        this.valorUnitario = valorUnitario;
    }
}
