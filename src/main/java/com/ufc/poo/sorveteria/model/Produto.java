/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufc.poo.sorveteria.model;

import com.ufc.poo.sorveteria.model.enums.Tipo;
import java.sql.Timestamp;

import javax.management.BadAttributeValueExpException;

/**
 *
 * @author cristiano
 */
public class Produto {

    private Integer id;
    private String nome;
    private Tipo tipo;
    private Double preco;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Integer quantidadeDisponivel;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the tipo
     */
    public Tipo getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the preco
     */
    public Double getPreco() {
        return preco;
    }

    /**
     * @param preco the preco to set
     */
    public void setPreco(Double preco) {
        this.preco = preco;
    }

    /**
     * @return the createdAt
     */
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt the createdAt to set
     */
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @return the updatedAt
     */
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    /**
     * @param updatedAt the updatedAt to set
     */
    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * @return the quantidadeDisponivel
     */
    public Integer getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    /**
     * @param quantidadeDisponivel the quantidadeDisponivel to set
     */
    public void setQuantidadeDisponivel(Integer quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public Boolean verificarProduto() throws BadAttributeValueExpException {
        if (this.nome == null || this.nome.isEmpty() || this.nome.isBlank()) {
            throw new BadAttributeValueExpException("Campo NOME em produto está inválido");
        } else if (this.tipo == null) {
            throw new BadAttributeValueExpException("Campo TIPO em produto está inválido");
        } else if (this.preco == null || this.preco.isNaN()) {
            throw new BadAttributeValueExpException("Campo PREÇO em produto está inválido.");
        } else if (this.quantidadeDisponivel == null || this.quantidadeDisponivel < 0) {
            throw new BadAttributeValueExpException("Campo QUANTIDADE DISPONÍVEL em produto está inválido.");
        }

        return true;
    }

    @Override
    public String toString() {
        return "---- Produto ---- \nID: " + this.id + "\nNome: " + this.nome + "\nPreço: " + this.preco + "\nEstoque: "
                + this.quantidadeDisponivel + "\n-----------------";
    }
}