package com.ufc.poo.sorveteria.services;

import javax.management.BadAttributeValueExpException;

import com.ufc.poo.sorveteria.exceptions.NotFoundException;
import com.ufc.poo.sorveteria.model.Venda;

public interface VendaService {
    Venda cadastrar(Venda venda) throws NotFoundException, BadAttributeValueExpException;
    void remover(Integer id) throws NotFoundException;
    Venda editar(Venda venda) throws NotFoundException, BadAttributeValueExpException;
    Venda buscar(Integer id);
}
