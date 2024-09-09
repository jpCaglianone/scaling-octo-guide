package com.example.projeto_java.Service;


import com.example.projeto_java.Domain.Item;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface ItemCommandService {
    public CompletableFuture<String> cadastrarItem(Item novoItem);
    public CompletableFuture<String> atualizarQuantidade(UUID id, int quantidade);
    public CompletableFuture<String> atualizarValor(UUID id, double valor);
}