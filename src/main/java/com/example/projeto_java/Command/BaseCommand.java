package com.example.projeto_java.Command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

public class BaseCommand<T> {

    @TargetAggregateIdentifier
    public final UUID id;

    public BaseCommand(UUID id) {
        this.id = id;
    }

}
