/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufc.poo.sorveteria.exceptions;

/**
 *
 * @author cristiano
 */
public class NotFoundException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String message;

    public NotFoundException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
