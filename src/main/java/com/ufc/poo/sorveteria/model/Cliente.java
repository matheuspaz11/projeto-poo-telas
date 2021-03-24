/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufc.poo.sorveteria.model;

import java.sql.Timestamp;
import java.util.InputMismatchException;

import javax.management.BadAttributeValueExpException;

/**
 *
 * @author cristiano
 */
public class Cliente extends Pessoa {

    private Integer id;
    private Timestamp createdAt;
    private Timestamp updatedAt;

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

    public Boolean verificarCliente() throws BadAttributeValueExpException {
        if (nome.isBlank() || nome.isEmpty()) {
            throw new BadAttributeValueExpException("Campo nome é obrigátorio");
        }

        if (!validarCpf()) {
            throw new BadAttributeValueExpException("Campo CPF vazio ou inválido");
        }

        return true;
    }

    /**
     * Validar o cpf do cliente, verificando se ele é valido
     */
    private Boolean validarCpf() {
        if (!this.cpf.isBlank() && !this.cpf.isEmpty() && isCPF(this.cpf)) {// verifica se o cpf está preenchido e se é
                                                                            // válido
            return true;
        }

        return false;
    }

    private boolean isCPF(String CPF) {
        // considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (CPF.equals("00000000000") || CPF.equals("11111111111") || CPF.equals("22222222222")
                || CPF.equals("33333333333") || CPF.equals("44444444444") || CPF.equals("55555555555")
                || CPF.equals("66666666666") || CPF.equals("77777777777") || CPF.equals("88888888888")
                || CPF.equals("99999999999") || (CPF.length() != 11))
            return (false);

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                // converte o i-esimo caractere do CPF em um numero:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posicao de '0' na tabela ASCII)
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else
                dig10 = (char) (r + 48); // converte no respectivo caractere numerico

            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else
                dig11 = (char) (r + 48);

            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
                return (true);
            else
                return (false);
        } catch (InputMismatchException erro) {
            return (false);
        }
    }

    @Override
    public String toString() {
        return "---- Cliente ---- \nID: " + this.id + "\nNome: " + this.nome + "\nTelefone: " + this.telefone
                + "\nCPF: " + this.cpf + "\n-----------------";
    }
}
