package com.senac.mercadinho.model.bean;

import java.util.Objects;

/**
 *
 * @author Vinny
 */
public class Produto {
    
    private String codigo_de_barras;
    private int codigo;
    private String descricao;
    private String unidade;
    private double valor;
    private int quantidade;
    private int quantidade_un;
    private double quantidade_kg;
    private String categoria_id;

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.codigo_de_barras);
        hash = 59 * hash + this.codigo;
        hash = 59 * hash + Objects.hashCode(this.descricao);
        hash = 59 * hash + Objects.hashCode(this.unidade);
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.valor) ^ (Double.doubleToLongBits(this.valor) >>> 32));
        hash = 59 * hash + this.quantidade;
        hash = 59 * hash + this.quantidade_un;
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.quantidade_kg) ^ (Double.doubleToLongBits(this.quantidade_kg) >>> 32));
        hash = 59 * hash + Objects.hashCode(this.categoria_id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Produto other = (Produto) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        if (Double.doubleToLongBits(this.valor) != Double.doubleToLongBits(other.valor)) {
            return false;
        }
        if (this.quantidade != other.quantidade) {
            return false;
        }
        if (this.quantidade_un != other.quantidade_un) {
            return false;
        }
        if (Double.doubleToLongBits(this.quantidade_kg) != Double.doubleToLongBits(other.quantidade_kg)) {
            return false;
        }
        if (!Objects.equals(this.codigo_de_barras, other.codigo_de_barras)) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (!Objects.equals(this.unidade, other.unidade)) {
            return false;
        }
        if (!Objects.equals(this.categoria_id, other.categoria_id)) {
            return false;
        }
        return true;
    }
    
    

    public String getCodigo_de_barras() {
        return codigo_de_barras;
    }

    public void setCodigo_de_barras(String codigo_de_barras) {
        this.codigo_de_barras = codigo_de_barras;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getQuantidade_un() {
        return quantidade_un;
    }

    public void setQuantidade_un(int quantidade_un) {
        this.quantidade_un = quantidade_un;
    }

    public double getQuantidade_kg() {
        return quantidade_kg;
    }

    public void setQuantidade_kg(double quantidade_kg) {
        this.quantidade_kg = quantidade_kg;
    }

    public String getCategoria_id() {
        return categoria_id;
    }

    public void setCategoria_id(String categoria_id) {
        this.categoria_id = categoria_id;
    }
    
    
}
