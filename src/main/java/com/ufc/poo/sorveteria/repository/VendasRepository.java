/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufc.poo.sorveteria.repository;

import com.ufc.poo.sorveteria.model.Venda;
import com.ufc.poo.sorveteria.exceptions.NotFoundException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.management.BadAttributeValueExpException;

/**
 *
 * @author cristiano Simulando um banco de dados
 */

public class VendasRepository{
    private static List<Venda> vendas;//no banco seria tabela vendas

    public VendasRepository() {
        if (vendas == null) {
            vendas = new ArrayList<>();
        }
    }

    
    public void save(Venda venda) throws NotFoundException, BadAttributeValueExpException {
        if (venda == null) {
            throw new NotFoundException("Venda é nula");
        }else if(findById(venda.getId()) != null){
            throw new BadAttributeValueExpException("Venda com esse ID '"+venda.getId()+"' já cadastrada");
        }

        venda.verificarVenda();
        venda.setCreatedAt(new Timestamp(new Date().getTime()));

        vendas.add(venda);
    }

    
    public Venda findById(Integer id) {
        if(vendas == null || vendas.size() <=0 )
            return null;

        try{
            return vendas.stream().filter(vendas -> vendas.getId().equals(id)).findFirst().get();
        }catch(Exception e){
            return null;
        }
    }

    
    public void edit(Venda venda) throws NotFoundException, BadAttributeValueExpException {
        Venda vendasEdit = this.findById(venda.getId());
        if (vendasEdit == null) {
            throw new NotFoundException("Venda não encontrado.");
        }

        venda.verificarVenda();

        vendasEdit.setPedidos(venda.getPedidos());
        vendasEdit.setCliente(venda.getCliente());
        vendasEdit.setUpdatedAt(new Timestamp(new Date().getTime()));
        this.remove(vendasEdit.getId());// vai remover o valor antigo do array

        vendas.add(vendasEdit);// atualiza o array
    }

    
    public void remove(Integer id) throws NotFoundException {
        if (this.findById(id) == null) {
            throw new NotFoundException("Cliente não encontrado");
        }

        vendas = vendas.stream().filter(vendasSalvo -> !vendasSalvo.getId().equals(id)).collect(Collectors.toList());
    }
}
