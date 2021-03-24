package com.ufc.poo.sorveteria.services;

import javax.management.BadAttributeValueExpException;

import com.ufc.poo.sorveteria.exceptions.NotFoundException;
import com.ufc.poo.sorveteria.model.Produto;

public interface ProdutoService {
    Produto cadastrar(Produto produto) throws NotFoundException, BadAttributeValueExpException;
    void remover(Integer id) throws NotFoundException;
    Produto editar(Produto produto) throws NotFoundException, BadAttributeValueExpException;
    Produto buscar(Integer id);
}
