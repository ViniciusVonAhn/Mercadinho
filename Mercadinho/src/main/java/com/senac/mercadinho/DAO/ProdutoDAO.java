/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.mercadinho.DAO;

import com.mysql.jdbc.PreparedStatement;
import com.senac.mercadinho.Connection.ConnectionFactory;
import com.senac.mercadinho.model.bean.Produto;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author viniciusvonahn
 */
public class ProdutoDAO {
    public ResultSet result;

    public void create(Produto p){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = (PreparedStatement) con.prepareStatement("INSERT INTO produtos (codigo, codigo_de_barras,"
                    + " categoria_id, descricao, quantidade, valor)VALUES(?,?,?,?,?,?)");
            stmt.setInt(1, p.getCodigo());
            stmt.setString(2, p.getCodigoDeBarras());
            stmt.setString(3, p.getCategoriaId());
            stmt.setString(4, p.getDescricao());
            stmt.setDouble(5, p.getQuantidade());
            stmt.setDouble(6, p.getValor());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso");
         
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
    public List<Produto> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Produto> produtos = new ArrayList<>();
        
        try {
            stmt = (PreparedStatement) con.prepareStatement("SELECT * FROM produtos");
           rs = stmt.executeQuery();
            while (rs.next()) {                
                
                Produto produto = new Produto();
                produto.setCodigo(rs.getInt("codigo"));
                produto.setCodigoDeBarras(rs.getString("codigo_de_barras"));
                produto.setCategoriaId(rs.getString("categoria_id"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produto.setValor(rs.getDouble("valor"));
             
                produtos.add(produto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return produtos;
        
    }
    
    public void update(Produto p){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = (PreparedStatement) con.prepareStatement("UPDATE produtos SET codigo = ?, codigo_de_barras = ?,"
                    + " categoria_id = ?, descricao = ?, quantidade = ?, valor = ? WHERE produtosid = ?");
            stmt.setInt(1, p.getCodigo());
            stmt.setString(2, p.getCodigoDeBarras());
            stmt.setString(3, p.getCategoriaId());
            stmt.setString(4, p.getDescricao());
            stmt.setDouble(5, p.getQuantidade());
            stmt.setDouble(6, p.getValor());
            stmt.setInt(7, p.getProdutosid());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso");
         
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
}
