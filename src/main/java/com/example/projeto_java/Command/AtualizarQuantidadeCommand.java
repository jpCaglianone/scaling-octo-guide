package com.example.projeto_java.Command;

import java.util.UUID;

public class AtualizarQuantidadeCommand extends BaseCommand<UUID> {

    public final int quantidade;

    public AtualizarQuantidadeCommand(UUID id, int quantidade) {
        super(id);
        this.quantidade = quantidade;
    }
}
