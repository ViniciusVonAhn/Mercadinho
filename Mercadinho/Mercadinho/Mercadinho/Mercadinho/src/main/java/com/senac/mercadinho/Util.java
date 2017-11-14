/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.mercadinho;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

/**
 *
 * @author LordFabricio
 */
public class Util {

    // Variaveis de Confirmação
    private Integer btSalvar;
    private Integer janela;
    private Integer lupa;
    private boolean cadeado;
    private boolean caixa;
    private boolean user;
    
    // Imagens dos Icones
    private final ImageIcon cadeadoAberto = new ImageIcon(getClass().getResource("/img/cadverde.png"));
    private final ImageIcon cadeadoFechado = new ImageIcon(getClass().getResource("/img/cadvermelho.png"));
    private final ImageIcon lupaNormal = new ImageIcon(getClass().getResource("/img/lupa.png"));
    private final ImageIcon lupaLaranjaF = new ImageIcon(getClass().getResource("/img/lupas.png"));
    private final ImageIcon lupaLaranja = new ImageIcon(getClass().getResource("/img/lupae.png"));
    private final ImageIcon pesquisaNormal = new ImageIcon(getClass().getResource("/img/fundopesquisa.png"));
    private final ImageIcon pesquisaSobre = new ImageIcon(getClass().getResource("/img/fundopesquisae.png"));
    private final ImageIcon fundoClaro = new ImageIcon(getClass().getResource("/img/fundoclaro.png"));
    private final ImageIcon fundoEscuro = new ImageIcon(getClass().getResource("/img/fundoescuro.png"));
    private final ImageIcon certoSobre = new ImageIcon(getClass().getResource("/img/certos.png"));
    private final ImageIcon certoNormal = new ImageIcon(getClass().getResource("/img/certo.png"));
    private final ImageIcon erradoSobre = new ImageIcon(getClass().getResource("/img/errados.png"));
    private final ImageIcon erradoNormal = new ImageIcon(getClass().getResource("/img/errado.png"));
    private final ImageIcon salva = new ImageIcon(getClass().getResource("/img/salva.png"));

    public Util() {
        btSalvar = 0;
        cadeado = true;
        caixa = true;
        user = false;
        janela = 0;
        lupa = 0;
    }
    
    //Metodo Troca Virgula pra Ponto - Recebe String Envia Double
    public double convertePonto(String pString){
        String p = new String();
        int t = pString.length();
        for (int i = 0; i < t; i++) {
            if(pString.charAt(i) == ','){
                p += '.';
            } else {
                p += pString.charAt(i);
            }
        }
        return Double.parseDouble(p);
    }
    
    //Metodo Troca Ponto pra Virgula - Recebe String Envia String
    public String converteVirgula(String vString){
        String v = new String();
        int t = vString.length();
        for (int i = 0; i < t; i++) {
            if(vString.charAt(i) == '.'){
                v += ',';
            } else {
                v += vString.charAt(i);
            }
        }
        return v;
    }
    
    public void mouseMove(MouseEvent m){
        try {
            Robot r = new Robot();
            r.mouseMove(600, 400);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
   
    //Metodos de Troca de Imagem
    public ImageIcon cadeadoAberto() {
        return cadeadoAberto;
    }
    
    public ImageIcon cadeadoFechado(){
        return cadeadoFechado;
    }

    public ImageIcon lupaIcon() {
        return lupaNormal;
    }
    
    public ImageIcon lupaLaranjaF(){
        return lupaLaranjaF;
    }
    
    public ImageIcon lupaLaranja(){
        return lupaLaranja;
    }

    public ImageIcon pesquisaIcon() {
        return pesquisaNormal;
    }
    
    public ImageIcon pesquisaSobre(){
        return pesquisaSobre;
    }
    
    public ImageIcon fundoIcon(){
        return fundoClaro;
    }
    
    public ImageIcon fundoEscuro(){
        return fundoEscuro;
    }
    
    public ImageIcon certoSobre(){
        return certoSobre;
    }
    
    public ImageIcon certoIcon(){
        return certoNormal;
    }
    
    public ImageIcon erradoSobre(){
        return erradoSobre;
    }
    
    public ImageIcon erradoIcon(){
        return erradoNormal;
    }
    
    public ImageIcon salvaIcon(){
        return salva;
    }
    
    // Getters e Setters
    public boolean isCadeado() {
        return cadeado;
    }

    public void setCadeado(boolean cadeado) {
        this.cadeado = cadeado;
    }

    public boolean isCaixa() {
        return caixa;
    }

    public void setCaixa(boolean caixa) {
        this.caixa = caixa;
    }

    public boolean isUser() {
        return user;
    }

    public void setUser(boolean user) {
        this.user = user;
    }

    public Integer getJanela() {
        return janela;
    }

    public void setJanela(Integer janela) {
        this.janela = janela;
    }

    public Integer getLupa() {
        return lupa;
    }

    public void setLupa(Integer lupa) {
        this.lupa = lupa;
    }

    public Integer getBtSalvar() {
        return btSalvar;
    }

    public void setBtSalvar(Integer btSalvar) {
        this.btSalvar = btSalvar;
    }
    
}
