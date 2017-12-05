/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.mercadinho.model.bean;

import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class Venda {
    private double valortotal;
    private double valorpago;
    private double troco;

    public Venda() {
        
    }

    public Venda(double valortotal) {
        this.valortotal = 0;
    }

    public void setTroco(double troco) {
        this.troco = troco;
    }

    public double getTroco() {
        return troco;
    }

    

    public double getValortotal() {
        return valortotal;
    }

    public void setValortotal(double valortotal) {
        this.valortotal = valortotal;
    }

    public double getValorpago() {
        return valorpago;
    }

    public void setValorpago(double valorpago) {
        this.valorpago = valorpago;
    }
    
    public void calculaTroco(){
        System.out.println("VALOR PAGOOOOOOOOOo"+getValorpago());
        System.out.println("VALOR TOTALL"+getValortotal());
        double troco = ((getValorpago())-(getValortotal()));
        setTroco(troco);
    }
    
    public void calculaTotal(double valorProduto){
        setValortotal((getValortotal()+(valorProduto)));
    }
    
    public void recalcularTotal(double novototal){
        double novototal2 = getValortotal()-novototal;
        setValortotal(novototal2);
    }
}
