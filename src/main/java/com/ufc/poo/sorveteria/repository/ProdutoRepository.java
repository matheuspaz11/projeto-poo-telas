/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufc.poo.sorveteria.repository;

import com.ufc.poo.sorveteria.model.Produto;
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
public class ProdutoRepository{
    private static List<Produto> produtos;//no banco seria tabela produtos

    public ProdutoRepository() {
        if (produtos == null) {
            produtos = new ArrayList<>();
        }
    }

    
    public void save(Produto produto) throws NotFoundException, BadAttributeValueExpException {
        if (produto == null) {
            throw new NotFoundException("Produto é nulo");
        }else if(findById(produto.getId()) != null){
            throw new BadAttributeValueExpException("Produto com esse id '"+produto.getId()+"' já cadastrado");
        }

        produto.verificarProduto();

        produto.setCreatedAt(new Timestamp(new Date().getTime()));

        produtos.add(produto);
    }

    
    public Produto findById(Integer id) {
        if(produtos == null || produtos.size() <= 0)
            return null;

        try{
            return produtos.stream().filter(produto -> produto.getId().equals(id)).findFirst().get();
        }catch(Exception e){
            return null;
        }
    }

    
    public void edit(Produto produto) throws NotFoundException, BadAttributeValueExpException {
        Produto produtoEdit = this.findById(produto.getId());
        if (produtoEdit == null) {
            throw new NotFoundException("Produto não encontrado.");
        }

        produto.verificarProduto();

        produtoEdit.setNome(produto.getNome());
        produtoEdit.setTipo(produto.getTipo());
        produtoEdit.setPreco(produto.getPreco());
        produtoEdit.setQuantidadeDisponivel(produto.getQuantidadeDisponivel());
        
        produtoEdit.setUpdatedAt(new Timestamp(new Date().getTime()));
        
        this.remove(produtoEdit.getId());// vai remover o valor antigo do array
        produtos.add(produtoEdit);// atualiza o array
    }

    
    public void remove(Integer id) throws NotFoundException {
        if (this.findById(id) == null) {
            throw new NotFoundException("Cliente não encontrado");
        }
        
        produtos = produtos.stream().filter(produtoSalvo -> !produtoSalvo.getId().equals(id))
                .collect(Collectors.toList());
    }
}
