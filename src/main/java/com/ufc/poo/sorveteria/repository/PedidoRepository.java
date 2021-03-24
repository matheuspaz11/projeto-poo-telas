/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufc.poo.sorveteria.repository;

import com.ufc.poo.sorveteria.model.Pedido;
import com.ufc.poo.sorveteria.exceptions.NotFoundException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.management.BadAttributeValueExpException;

/**
 *
 * @author cristiano
 */

public class PedidoRepository{
    private static List<Pedido> pedidos;//no banco seria tabela pedidos

    public PedidoRepository() {
        if (pedidos == null) {
            pedidos = new ArrayList<>();
        }
    }

    
    public void save(Pedido pedido) throws NotFoundException, BadAttributeValueExpException {
        if (pedido == null) {
            throw new NotFoundException("Pedido é nulo");
        }else if(findById(pedido.getId()) != null){
            throw new BadAttributeValueExpException("Já existe um pedido com esse ID '"+pedido.getId()+"'");
        }

        pedido.verificarPedido();
        pedido.setCreatedAt(new Timestamp(new Date().getTime()));

        pedidos.add(pedido);
    }

    
    public Pedido findById(Integer id) {
        if(pedidos == null || pedidos.size() <= 0){
            return null;
        }

        try{
            return pedidos.stream().filter(pedido -> pedido.getId().equals(id)).findFirst().get();
        }catch(Exception e){
            return null;
        }
    }

    
    public void edit(Pedido pedido) throws NotFoundException, BadAttributeValueExpException {
        Pedido pedidosEdit = this.findById(pedido.getId());
        if (pedidosEdit == null) {
            throw new NotFoundException("Produto não encontrado.");
        }

        pedido.verificarPedido();

        pedidosEdit.setQuantidadeDesejada(pedido.getQuantidadeDesejada());
        pedidosEdit.setProduto(pedido.getProduto());
        

        pedidosEdit.setUpdatedAt(new Timestamp(new Date().getTime()));
        this.remove(pedidosEdit.getId());// vai remover o valor antigo do array
        
        pedidos.add(pedidosEdit);// atualiza o array
    }

    
    public void remove(Integer id) throws NotFoundException {
        if (this.findById(id) == null) {
            throw new NotFoundException("Pedido não encontrado");
        }

        pedidos = pedidos.stream().filter(pedidosSalvo -> !pedidosSalvo.getId().equals(id))
                .collect(Collectors.toList());
    }
}
