package com.example.projeto_java.Controller;

import com.example.projeto_java.Domain.Item;
import com.example.projeto_java.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itens")
public class ItemController {

    @Autowired
    ItemService itemService;

    @GetMapping("/status")
    public String status() {
        return "OK";
    }

    @GetMapping("/listarTodos")
    public ResponseEntity<List<Item>> listarItens() {
        List<Item> itens = itemService.listarTodosItens();

        if (itens.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(itens);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?> inserirItem(@RequestBody Item novoItem) {

        if (itemService.rastrearItemCadastrado(novoItem)){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Item já cadastrado!");
        }
        try {
            Item itemInserido = itemService.salvarItem(novoItem);
            return ResponseEntity.status(HttpStatus.CREATED).body(itemInserido);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao salvar o item: " + e.getMessage());
        }
    }
}
