package com.senac.mercadinho.model.bean;

import java.util.Objects;

/**
 *
 * @author Vinny
 */
public class Produto {

    private int produtosid;
    private String codigoDeBarras;
    private int codigo;
    private String descricao;
    private String unidade;
    private double valor;
    private double quantidade;
    private int quantidadeUn;
    private double quantidadeKg;
    private String categoriaId;

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.codigoDeBarras);
        hash = 59 * hash + this.codigo;
        hash = 59 * hash + Objects.hashCode(this.descricao);
        hash = 59 * hash + Objects.hashCode(this.unidade);
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.valor) ^ (Double.doubleToLongBits(this.valor) >>> 32));
        hash = (int) (59 * hash + this.quantidade);
        hash = 59 * hash + this.quantidadeUn;
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.quantidadeKg) ^ (Double.doubleToLongBits(this.quantidadeKg) >>> 32));
        hash = 59 * hash + Objects.hashCode(this.categoriaId);
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
        if (this.quantidadeUn != other.quantidadeUn) {
            return false;
        }
        if (Double.doubleToLongBits(this.quantidadeKg) != Double.doubleToLongBits(other.quantidadeKg)) {
            return false;
        }
        if (!Objects.equals(this.codigoDeBarras, other.codigoDeBarras)) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (!Objects.equals(this.unidade, other.unidade)) {
            return false;
        }
        if (!Objects.equals(this.categoriaId, other.categoriaId)) {
            return false;
        }
        return true;
    }

    public int getProdutosid() {
        return produtosid;
    }

    public void setProdutosid(int produtosid) {
        this.produtosid = produtosid;
    }

    
    
    

    public String getCodigoDeBarras() {
        return codigoDeBarras;
    }

    public void setCodigoDeBarras(String codigoDeBarras) {
        this.codigoDeBarras = codigoDeBarras;
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

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public int getQuantidadeUn() {
        return quantidadeUn;
    }

    public void setQuantidadeUn(int quantidadeUn) {
        this.quantidadeUn = quantidadeUn;
    }

    public double getQuantidadeKg() {
        return quantidadeKg;
    }

    public void setQuantidadeKg(double quantidadeKg) {
        this.quantidadeKg = quantidadeKg;
    }

    public String getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(String categoriaId) {
        this.categoriaId = categoriaId;
    }

}
