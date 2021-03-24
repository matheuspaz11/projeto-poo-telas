package com.ufc.poo.sorveteria.services;

import javax.management.BadAttributeValueExpException;

import com.ufc.poo.sorveteria.exceptions.NotFoundException;
import com.ufc.poo.sorveteria.model.Pedido;

public interface PedidoService{
    Pedido cadastrar(Pedido pedido) throws NotFoundException, BadAttributeValueExpException;
    void remover(Integer id) throws NotFoundException;
    Pedido editar(Pedido pedido) throws NotFoundException, BadAttributeValueExpException;
    Pedido buscar(Integer id);
}