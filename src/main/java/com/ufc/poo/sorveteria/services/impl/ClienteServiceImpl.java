/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufc.poo.sorveteria.services.impl;

import com.ufc.poo.sorveteria.model.Cliente;
import com.ufc.poo.sorveteria.repository.ClienteRepository;
import com.ufc.poo.sorveteria.services.ClienteService;
import com.ufc.poo.sorveteria.exceptions.NotFoundException;
import java.util.NoSuchElementException;

import javax.management.BadAttributeValueExpException;

/**
 *
 * @author cristiano
 */
public class ClienteServiceImpl implements ClienteService{    
    private static ClienteRepository clienteRepository;
    
    public ClienteServiceImpl(){
        clienteRepository = new ClienteRepository();
    }
    
    @Override
    public Cliente cadastrar(Cliente cliente) throws NotFoundException, BadAttributeValueExpException{        
        try{
            clienteRepository.save(cliente);
            System.out.println("Cliente salvo com sucesso.\n");
            return cliente;
        }catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage());
        } catch ( BadAttributeValueExpException e){
            throw new BadAttributeValueExpException(e);
        }
    }
    
    @Override
    public void remover(Integer id) throws NotFoundException{
        try{
             clienteRepository.remove(id);
             System.out.println("Cliente removido com sucesso.\n");
        }catch(NotFoundException e){
            throw new NotFoundException(e.getMessage());
        }
    }
    
    @Override
    public Cliente editar(Cliente cliente) throws NotFoundException, BadAttributeValueExpException{
        try{
            clienteRepository.edit(cliente);
            System.out.println("Cliente editado com sucesso.\n");
            return cliente;
        }catch(NotFoundException e){
            throw new NotFoundException(e.getMessage());
        }catch(BadAttributeValueExpException e){
            throw new BadAttributeValueExpException(e);
        }
    }
    
    @Override
    public Cliente buscar(Integer id){
        try{
            return clienteRepository.findById(id);
        }catch(NoSuchElementException e){
            throw new NoSuchElementException("Nenhum cliente encontrado com o id '"+id+"'");
        }
    }
        
}
