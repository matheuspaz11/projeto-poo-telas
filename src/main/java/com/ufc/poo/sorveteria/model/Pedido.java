/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufc.poo.sorveteria.model;

import java.sql.Timestamp;

import javax.management.BadAttributeValueExpException;

/**
 *
 * @author cristiano
 */
public class Pedido {

    private Integer id;
    private Produto produto;
    private Integer quantidadeDesejada;
    private Double valorTotal;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Object cliente;

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
     * @return the produto
     */
    public Produto getProduto() {
        return produto;
    }

    /**
     * @param produto the produto to set
     */
    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    /**
     * @return the quantidadeDesejada
     */
    public Integer getQuantidadeDesejada() {
        return quantidadeDesejada;
    }

    /**
     * @param quantidadeDesejada the quantidadeDesejada to set
     */
    public void setQuantidadeDesejada(Integer quantidadeDesejada) {
        this.quantidadeDesejada = quantidadeDesejada;
        this.valorTotal = this.calcularValorTotal();
    }

    /**
     * @return the valorTotal
     */
    public Double getValorTotal() {
        return valorTotal;
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
     * Faz o calculo do valor total do produto, podendo ser colocado taxas ou coisas
     * parecidas
     */
    private Double calcularValorTotal() {
        return this.getProduto().getPreco() * this.quantidadeDesejada;
    }

    public Boolean verificarPedido() throws BadAttributeValueExpException {
        if (this.produto == null) {
            throw new BadAttributeValueExpException("Campo PRODUTO é obrigatório para o pedido.");
        } else if (this.quantidadeDesejada == null) {
            throw new BadAttributeValueExpException("Campo QUANTIDADE DESEJADA é obrigatório para o pedido.");
        }

        return true;
    }

    @Override
    public String toString() {
        return "---- Pedido ---- \nID: " + this.id + "\nProduto: " + this.produto.getNome() + "\nCliente: "
                + ((Produto) this.cliente).getNome() + "\nPreço: " + this.valorTotal + "\n----------------";
    }
}