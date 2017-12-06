/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.mercadinho.DAO;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mysql.jdbc.PreparedStatement;
import com.senac.mercadinho.Connection.ConnectionFactory;
import com.senac.mercadinho.model.bean.Produto;
import com.senac.mercadinho.model.bean.Venda;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
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
    boolean ok;
    public static boolean unidade;
    public static ArrayList<String> codigos = new ArrayList();
    public static ArrayList<Integer> quantidades = new ArrayList();
    

    Venda venda = new Venda();

   /*
    public void validarQuantidade(String codigodebarras, int quantidade) {
        System.out.println("CODIGO DE BARRAS VALIDAR QUANTIDADE " + codigodebarras);
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        for (Produto p : read()) {
            if (p.getCodigoDeBarras().equalsIgnoreCase(codigodebarras)) {

                try {
                    stmt = (PreparedStatement) con.prepareStatement("SELECT produtos.unidade"
                            + " FROM produtos WHERE " + codigodebarras + " = " + p.getCodigoDeBarras() + ";");
                    rs = stmt.executeQuery();

                    while (rs.next()) {
                        if (codigodebarras.equals(rs.getString("codigo_de_barras"))) {
                            String codigo = (rs.getString("unidade"));
                            System.out.println("UN OU KG??? " + codigo);
                            if (codigo.equals("UN")) {
                                unidade = true;
                            } else if (codigo.equals("KG")) {
                                unidade = false;
                            }
                        }
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(ProdutoDAO.class
                            .getName()).log(Level.SEVERE, null, ex);
                } finally {
                    ConnectionFactory.closeConnection(con, stmt, rs);
                }

                /*if (p.getQuantidadeUn() >= quantidade) {
                    ok = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Não há quantidade " + quantidade + " de " + p.getDescricao() + " em estoque!");
                    JOptionPane.showMessageDialog(null, "Há quantidade " + p.getQuantidadeUn() + " de " + p.getDescricao() + " em estoque!");
                    ok = false;
                }*//*
            }
        }
    } */


    public void percorrerVenda(int id) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = (PreparedStatement) con.prepareStatement("SELECT produtos.codigo_de_barras, produtos.unidade, produtos.quantidade_un, produtos.quantidade_kg"
                    + " FROM produtos INNER JOIN venda ON"
                    + "(produtos.produtosid=venda.produtosid) WHERE venda.id = " + (id + 1) + ";");
            rs = stmt.executeQuery();
            while (rs.next()) {
                String cod = (rs.getString("codigo_de_barras"));
                codigos.add(cod);
                if (rs.getString("unidade").equals("UN")) {
                    quantidades.add(rs.getInt("quantidade_un"));
                    unidade = true;
                } else if (rs.getString("unidade").equals("KG")) {
                    quantidades.add(rs.getInt("quantidade_kg"));
                    unidade = false;
                }
                

            }
            System.out.println("IMPRIMINDO ARRAY CODIGOS "+codigos);
            System.out.println("IMPRIMINDO ARRAY UN "+quantidades);
            

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }

    public void removerQuantidadeEstoque(String codigodebarras, int quantidade, int quantidadeEstoque, boolean unid) {
        int recalculandoQuantidade = (quantidadeEstoque - quantidade);
        System.out.println("CODIGO DE BARRAS LOCAO " + codigodebarras);

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            if (unid) {
                stmt = (PreparedStatement) con.prepareStatement("UPDATE produtos SET quantidade_un = ? WHERE produtos.codigo_de_barras = " + codigodebarras + ";");
                stmt.setInt(1, recalculandoQuantidade);
                System.out.println("RECALCULANDO QUANTIDADE " + recalculandoQuantidade);
                System.out.println("ENTROU EM QUANTIDADE UNID");
                stmt.executeUpdate();
            } else {
                stmt = (PreparedStatement) con.prepareStatement("UPDATE produtos SET quantidade_kg = ? WHERE produtos.codigo_de_barras = " + codigodebarras + ";");
                stmt.setInt(1, recalculandoQuantidade);
                System.out.println("RECALCULANDO QUANTIDADE " + recalculandoQuantidade);
                System.out.println("ENTROU EM QUANTIDADE KG");
                stmt.executeUpdate();
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

    public double recalcularProdutosVenda(int id) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = (PreparedStatement) con.prepareStatement("SELECT venda.id, produtos.descricao,"
                    + " produtos.valor, venda.quantidade FROM produtos INNER JOIN venda ON"
                    + "(produtos.produtosid=venda.produtosid);");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Produto produto = new Produto();
                produto.setCodigo(rs.getInt("id"));

                if (produto.getCodigo() == (id + 1)) {
                    produto.setValor(rs.getDouble("valor"));
                    System.out.println(produto.getValor() + "PRODUTO GET VALOR RECALCULAR PRODUTOS VENDA");

                    produto.setQuantidadeUn((rs.getInt("quantidade")));

                    double calculo = (produto.getValor() * produto.getQuantidadeUn());
                    System.out.println("CALCULO" + calculo);

                    venda.recalcularTotal(calculo);
                    System.out.println("NOVO TOTAL RECALCULAR TOTAL" + venda.getValortotal());

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return venda.getValortotal();
    }

    public void limparJtable() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
//            recalcularProdutosVenda();
            stmt = (PreparedStatement) con.prepareStatement("TRUNCATE TABLE venda;");
            stmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public void finalizarCompra() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            //          recalcularProdutosVenda();
            stmt = (PreparedStatement) con.prepareStatement("TRUNCATE TABLE venda;");//NAO FAZ SENTIDO LIMPAR A TABELA
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Venda concluída"); //acho que nao ta no lugar certo

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);

        }
    }

    public void excluirProdutoCompra(int id) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = (PreparedStatement) con.prepareStatement("DELETE FROM venda WHERE id = " + (id + 1) + ";");
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Item excluído com sucesso");

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);

        }
    }

    //aplicar polimorfismo depois
    public List<Produto> readVenda() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Produto> produtos = new ArrayList<>();

        try {
            stmt = (PreparedStatement) con.prepareStatement("SELECT produtos.descricao,"
                    + " produtos.valor, venda.quantidade FROM produtos INNER JOIN venda ON"
                    + "(produtos.produtosid=venda.produtosid);");
            rs = stmt.executeQuery();
            while (rs.next()) {

                Produto produto = new Produto();

                produto.setDescricao(rs.getString("descricao"));
                produto.setValor(rs.getDouble("valor"));
                produto.setQuantidadeUn(rs.getInt("quantidade"));

                produtos.add(produto);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return produtos;
    }

    public double addVenda(int quantidade, String cod) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Produto p = new Produto();
        try {
            stmt = (PreparedStatement) con.prepareStatement("SELECT produtosid, valor, descricao FROM produtos WHERE codigo_de_barras=" + cod + ";");
            rs = stmt.executeQuery();

            while (rs.next()) {
                p.setCodigo(rs.getInt("produtosid"));
                p.setValor(rs.getDouble("valor"));
                p.setDescricao(rs.getString("descricao"));
            }

            stmt = (PreparedStatement) con.prepareStatement("INSERT INTO venda (produtosid, quantidade, valortotal) VALUES (?,?,?)");
            stmt.setInt(1, p.getCodigo());
            stmt.setInt(2, quantidade);

            double total = quantidade * p.getValor();
            venda.calculaTotal(total);

            stmt.setDouble(3, total);

            stmt.executeUpdate();
            //JOptionPane.showMessageDialog(null, "Salvo com sucesso");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return venda.getValortotal();
    }

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
                if ("UN".equals(produto.getUnidade())) {
                    produto.setQuantidadeUn(rs.getInt("quantidade_un"));
                } else {
                    produto.setQuantidadeKg(rs.getInt("quantidade_kg"));
                }
                produto.setValor(rs.getDouble("valor"));

                produtos.add(produto);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
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

    public DefaultTableModel venda(String pesq) throws Exception {
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
            dtm.addRow(new String[]{rs.getString("descricao"), rs.getString("valor")});
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
            while (this.getResultSet().next()) {
                p.setProdutosid(this.getResultSet().getInt(1));
                p.setCodigoDeBarras(this.getResultSet().getString(2));
                p.setCodigo(this.getResultSet().getInt(3));
                p.setDescricao(this.getResultSet().getString(4));
                p.setUnidade(this.getResultSet().getString(5));
                p.setValor(this.getResultSet().getDouble(6));
                p.setQuantidadeUn(this.getResultSet().getInt(7));
                p.setQuantidadeKg(this.getResultSet().getDouble(8));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
        return p;
    }

    public void gerarPDF() {
        Connection con = ConnectionFactory.getConnection();

        Document doc = new Document();
        List<Produto> listp = read();
        String arquivoPdf = "relatorio.pdf";

        try {
            PdfWriter.getInstance(doc, new FileOutputStream(arquivoPdf));
            doc.open();

            Paragraph p = new Paragraph("Relatório de Estoque");
            p.setAlignment(1);
            doc.add(p);
            p = new Paragraph("  ");
            doc.add(p);

            PdfPTable table = new PdfPTable(4);

            PdfPCell cel1 = new PdfPCell(new Paragraph("Codigo"));
            PdfPCell cel2 = new PdfPCell(new Paragraph("Descrição"));
            PdfPCell cel3 = new PdfPCell(new Paragraph("Quantidade"));
            PdfPCell cel4 = new PdfPCell(new Paragraph("Valor"));

            table.addCell(cel1);
            table.addCell(cel2);
            table.addCell(cel3);
            table.addCell(cel4);

            for (Produto produto : listp) {
                cel1 = new PdfPCell(new Paragraph(produto.getCodigoDeBarras() + ""));
                cel2 = new PdfPCell(new Paragraph(produto.getDescricao() + ""));
                if ("UN".equals(produto.getUnidade())) {
                    cel3 = new PdfPCell(new Paragraph(produto.getQuantidadeUn() + ""));
                } else {
                    cel3 = new PdfPCell(new Paragraph(produto.getQuantidadeKg() + ""));
                }

                cel4 = new PdfPCell(new Paragraph(produto.getValor() + ""));

                table.addCell(cel1);
                table.addCell(cel2);
                table.addCell(cel3);
                table.addCell(cel4);
            }
            doc.add(table);
            doc.close();
            Desktop.getDesktop().open(new File(arquivoPdf));

        } catch (Exception e) {
        }
    }
}
