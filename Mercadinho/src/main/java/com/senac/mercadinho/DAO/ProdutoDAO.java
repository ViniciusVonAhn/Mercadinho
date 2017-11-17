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
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author viniciusvonahn
 */
public class ProdutoDAO extends ConnectionFactory {

    public ResultSet result;

    public void createKg(Produto p) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = (PreparedStatement) con.prepareStatement("INSERT INTO produtos (produtosid, codigo, codigo_de_barras,"
                    + " descricao, quantidade_kg, unidade, valor)VALUES(?,?,?,?,?,?,?)");
            stmt.setInt(2, p.getCodigo());
            stmt.setInt(1, p.getCodigo());
            stmt.setString(3, p.getCodigoDeBarras());
            stmt.setString(4, p.getDescricao());  
            stmt.setDouble(5, p.getQuantidadeKg());
            stmt.setString(6, p.getUnidade());
            stmt.setDouble(7, p.getValor());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }
    
    public void createUn(Produto p) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = (PreparedStatement) con.prepareStatement("INSERT INTO produtos (produtosid, codigo, codigo_de_barras,"
                    + " descricao, quantidade_un, unidade, valor)VALUES(?,?,?,?,?,?,?)");
            stmt.setInt(2, p.getCodigo());
            stmt.setInt(1, p.getCodigo());
            stmt.setString(3, p.getCodigoDeBarras());
            stmt.setString(4, p.getDescricao());  
            stmt.setDouble(5, p.getQuantidadeUn());
            stmt.setString(6, p.getUnidade());
            stmt.setDouble(7, p.getValor());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }
    
    public List<Produto> read() {
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
                produto.setDescricao(rs.getString("descricao"));
                produto.setUnidade(rs.getString("unidade"));
                if("UN".equals(produto.getUnidade())){
                produto.setQuantidadeUn(rs.getInt("quantidade_un"));    
                }else{
                produto.setQuantidadeKg(rs.getInt("quantidade_kg"));    
                }
                produto.setValor(rs.getDouble("valor"));

                produtos.add(produto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return produtos;

    }

    public void updateKg(Produto p) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = (PreparedStatement) con.prepareStatement("UPDATE produtos SET codigo = ?, codigo_de_barras = ?,"
                    + " descricao = ?, quantidade_kg = ?, unidade = ?, valor = ? WHERE produtosid = ?");
            stmt.setInt(1, p.getCodigo());
            stmt.setString(2, p.getCodigoDeBarras());
            stmt.setString(3, p.getDescricao());
            stmt.setDouble(4, p.getQuantidadeKg());
            stmt.setString(5, p.getUnidade());
            stmt.setDouble(6, p.getValor());
            stmt.setInt(7, p.getProdutosid());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }
    
    public void updateUn(Produto p) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = (PreparedStatement) con.prepareStatement("UPDATE produtos SET codigo = ?, codigo_de_barras = ?,"
                    + " descricao = ?, quantidade_un = ?, unidade = ?, valor = ? WHERE produtosid = ?");
            stmt.setInt(1, p.getCodigo());
            stmt.setString(2, p.getCodigoDeBarras());
            stmt.setString(3, p.getDescricao());
            stmt.setDouble(4, p.getQuantidadeUn());
            stmt.setString(5, p.getUnidade());
            stmt.setDouble(6, p.getValor());
            stmt.setInt(7, p.getProdutosid());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

    public Vector pesquisar(String pesq) throws Exception {
        Vector tb = new Vector();
        String url = "select * from produtos where codigo_de_barras like '" + pesq + "%'";
        PreparedStatement stmt = (PreparedStatement) getConnection().prepareStatement(url);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Vector nl = new Vector();
            nl.add(rs.getString("descricao"));
            nl.add(rs.getDouble("quantidade_kg"));
            nl.add(rs.getDouble("valor"));
            tb.add(nl);
        }
        return tb;
    }
    
    public DefaultTableModel venda(String pesq) throws Exception{
        Connection con = ConnectionFactory.getConnection();
		DefaultTableModel dtm = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		String sql = "select * from produtos where codigo_de_barras like '" + pesq + "'";
		PreparedStatement ps = (PreparedStatement) getConnection().prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
                //adiciona as colunas
		dtm.addColumn("descricao");
		dtm.addColumn("valor");
                dtm.addColumn("quantidade");
		while (rs.next()) {
                //pega os valores do bd para popular tabela
			dtm.addRow(new String[] {rs.getString("descricao"), rs.getString("valor")});
		}
		ConnectionFactory.closeConnection(con, ps);
                return dtm;
	}
    
    public Produto pegaP(String pId) {
        Produto p = new Produto();
        try {
            this.conectar();
            this.executarMysql(
                    "SELECT "
                    + "produtosid, "     
                    + "codigo_de_barras, "
                    + "codigo, "        
                    + "descricao, "
                    + "unidade, "
                    + "valor, "        
                    + "quantidade_un, "
                    + "quantidade_kg "
                    + " FROM produtos WHERE codigo_de_barras = '" + pId + "'"
            );
            while (this.getResultSet().next()){
                p.setProdutosid(this.getResultSet().getInt(1));
                p.setCodigoDeBarras(this.getResultSet().getString(2));
                p.setCodigo(this.getResultSet().getInt(3));
                p.setDescricao(this.getResultSet().getString(4));
                p.setUnidade(this.getResultSet().getString(5));
                p.setValor(this.getResultSet().getDouble(6));
                p.setQuantidadeUn(this.getResultSet().getInt(7));
                p.setQuantidadeKg(this.getResultSet().getDouble(8));
                p.setQuantidade(this.getResultSet().getDouble(9));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
        return p;
    }
    
}
