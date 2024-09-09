package com.example.projeto_java.Controller;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import com.example.projeto_java.Domain.Item;
import com.example.projeto_java.Service.ItemCommandService;
import com.example.projeto_java.Service.ItemCommandServiceImpl;
import com.example.projeto_java.Service.ItemService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/itensCommand")
public class ItemCommandController {

    private final ItemCommandService itemCommandService;

    public ItemCommandController(ItemCommandService service) {
        this.itemCommandService = service;
    }

    @PostMapping("/cadastrar")
    public CompletableFuture<String> cadastrarItem(@RequestBody Item novoItem) {


        return itemCommandService.cadastrarItem(novoItem);
    }

    @PutMapping(value = "/atualizarQuantidade")
    public CompletableFuture<String> atualizarQuantidade(@RequestBody Map<String, Object> atualizacao) {
        return itemCommandService.atualizarQuantidade(
                UUID.fromString((String) atualizacao.get("id")),
                (Integer) atualizacao.get("quantidade")
        );
    }

    @PutMapping(value = "/atualizarValor")
    public CompletableFuture<String> atualizarValor(@RequestBody Map<String, Object> atualizacao) {
        return itemCommandService.atualizarValor(
                UUID.fromString((String) atualizacao.get("id")),
                (Double) atualizacao.get("valor")
        );
    }
}
