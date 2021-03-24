/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufc.poo.sorveteria.repository;

import com.ufc.poo.sorveteria.model.Cliente;
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
public class ClienteRepository {
    private static List<Cliente> clientes;//no banco seria tabela clientes

    public ClienteRepository() {
        if (clientes == null) {
            clientes = new ArrayList<>();
        }
    }

    
    public void save(Cliente cliente) throws NotFoundException, BadAttributeValueExpException {
        if (cliente == null) {
            throw new NotFoundException("Cliente é nulo");
        }else if(findById(cliente.getId()) != null){
            throw  new BadAttributeValueExpException("Cliente com esse id '"+cliente.getId()+"' já cadastrado");
        }

        cliente.verificarCliente();//caso o cliente esteja ok ele segue adiante, se não ele joga uma excessão

        cliente.setCreatedAt(new Timestamp(new Date().getTime()));

        clientes.add(cliente);
    }

    
    public Cliente findById(Integer id){
        if(clientes == null || clientes.size() <= 0){
            return null;   
        }

        try{
            return clientes.stream().filter(cliente -> cliente.getId().equals(id)).findFirst().get();
        }catch(Exception e){
            return null;//caso não existe nenhum valor, ele irá retornar um erro, por isso o return null aqui
        }
    }

    
    public void edit(Cliente cliente) throws NotFoundException, BadAttributeValueExpException {
        Cliente clienteEdit = this.findById(cliente.getId());
        if (clienteEdit == null) {
            throw new NotFoundException("Cliente não encontrado.");
        }

        cliente.verificarCliente();

        clienteEdit.setCpf(cliente.getCpf());
        clienteEdit.setNome(cliente.getNome());
        clienteEdit.setTelefone(cliente.getTelefone());
        
        clienteEdit.setUpdatedAt(new Timestamp(new Date().getTime()));
        this.remove(clienteEdit.getId());// vai remover o valor antigo do array

        clientes.add(clienteEdit);// atualiza o array
    }

    
    public void remove(Integer id) throws NotFoundException {
        if (this.findById(id) == null) {
            throw new NotFoundException("Cliente não encontrado");
        }

        clientes = clientes.stream().filter(clienteSalvo -> !clienteSalvo.getId().equals(id))
                .collect(Collectors.toList());
    }

}
