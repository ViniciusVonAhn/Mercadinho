/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.mercadinho;

import javax.swing.ImageIcon;

/**
 *
 * @author LordFabricio
 */
public class EventosPrincipal {

    // Variaveis de Confirmação
    private String usuario;
    private String senha;
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

    public EventosPrincipal() {
        cadeado = true;
        caixa = false;
        user = false;
        janela = 0;
        lupa = 0;
    }
    
    //Metodo Login
    public boolean login(){
        boolean r;
        if (senha.equals("ADMIN") && usuario.equals("ADMIN")){
            return r = true;
        } else {
            return r = false;
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
    
    public ImageIcon CertoIcon(){
        return certoNormal;
    }
    
    
    // Getters e Setters
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

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
    
}
