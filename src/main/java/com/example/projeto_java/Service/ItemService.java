package com.example.projeto_java.Service;

import com.example.projeto_java.Domain.Item;
import com.example.projeto_java.Repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class ItemService {

    private static final Logger logger = LoggerFactory.getLogger(Item.class);

    @Autowired
    ItemRepository itemRepository;

    public Item salvarItem(Item novoItem) {
        try {
            return itemRepository.save(novoItem);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar o item", e);
        }
    }

    public boolean rastrearItemCadastrado(Item novoItem) {
        for (Item item : itemRepository.findAll()) {
            if (item.rastrearObjetoIgual(item.getModelo(), item.getMarca(), novoItem)) {
                return true;
            }
        }
        return false;
    }

    public List<Item> listarTodosItens() {
        return itemRepository.findAll();
    }
}
