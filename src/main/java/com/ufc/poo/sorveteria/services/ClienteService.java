package com.ufc.poo.sorveteria.services;

import javax.management.BadAttributeValueExpException;

import com.ufc.poo.sorveteria.exceptions.NotFoundException;
import com.ufc.poo.sorveteria.model.Cliente;

public interface ClienteService {
    Cliente cadastrar(Cliente cliente) throws NotFoundException, BadAttributeValueExpException;
    void remover(Integer id) throws NotFoundException;
    Cliente editar(Cliente cliente) throws NotFoundException, BadAttributeValueExpException;
    Cliente buscar(Integer id);
}
