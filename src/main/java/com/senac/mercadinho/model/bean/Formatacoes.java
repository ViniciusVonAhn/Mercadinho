/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.mercadinho.model.bean;

import java.text.DecimalFormat;
import javax.swing.JFormattedTextField;

/**
 *
 * @author User
 */
public class Formatacoes {

    public Formatacoes() {
    }
    
    public double virgulaParaPonto(JFormattedTextField x){
        String valor = x.getText();
        valor = valor.replace(",", ".");
        Double valorDouble = Double.parseDouble(valor);
        return valorDouble;
    }
    
    public double pontoParaVirgula(JFormattedTextField x){
        String valor = x.getText();
        valor = valor.replace(".", ",");
        Double valorDouble = Double.parseDouble(valor);
        return valorDouble;
    }
    
    public String pontoParaVirgula(String x){
        String valor = ""+x;
        valor = valor.replace(".", ",");
        String valorDouble = valor;
        return valorDouble;
    }
    
    public String limitarCasasDecimais(double valor){
        DecimalFormat df = new DecimalFormat("0.00");
        String resultado = (df.format(valor)); 
        System.out.println(resultado);
        return resultado;
    }
}
