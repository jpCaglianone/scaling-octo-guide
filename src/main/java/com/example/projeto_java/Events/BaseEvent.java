package com.example.projeto_java.Events;

import java.util.UUID;

public class BaseEvent<T> {

    public final UUID id;

    public BaseEvent(UUID id) {
        this.id = id;
    }
}
