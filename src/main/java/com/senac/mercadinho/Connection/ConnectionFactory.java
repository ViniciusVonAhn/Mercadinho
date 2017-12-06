package com.senac.mercadinho.Connection;
import com.mysql.jdbc.Statement;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * 
 * @author Vinny
 */
public class ConnectionFactory {
    
    private static final String URL = "jdbc:mysql://localhost:3306/mercadinho?autoReconnect=true&useSSL=false";
    private static final String USER = "root";
    private static final String PASS = "root";
    private boolean status = false;
    private com.mysql.jdbc.Connection con = null;
    private Statement statement;
    private ResultSet resultSet;
    private String server = "localhost:3306";
    private String banco = "mercadinho";
    
    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(URL, "root", "root");
            
        } catch (SQLException ex) {
          throw new RuntimeException("Erro na conexão: ",ex);
        }
    }
    
    public com.mysql.jdbc.Connection conectar() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String url = "jdbc:mysql://" + server + "/" + banco;
            this.setCon((com.mysql.jdbc.Connection) DriverManager.getConnection(url, USER, PASS));
            this.status = true;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return this.getCon();
    }
    
    public boolean desconectar() {
        try {
            if ((this.getResultSet() != null) && (this.statement != null)) {
                this.getResultSet().close();
                this.statement.close();
            }
            this.getCon().close();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return false;
    }
    
    public boolean executarMysql(String pMysql) {
        try {
            this.setStatement((Statement) getCon().createStatement());
            this.setResultSet(getStatement().executeQuery(pMysql));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    
    public static void closeConnection(Connection con){
        try {
        if(con != null){
        con.close();
            }
            } catch (SQLException ex) {
                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public static void closeConnection(Connection con, PreparedStatement stmt){
        closeConnection(con);
        try {
            if(stmt != null){
                stmt.close();
            }
        
            } catch (SQLException ex) {
                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs){
        closeConnection(con, stmt);
        try {
            if(rs !=null){
                rs.close();
            }
        
            } catch (SQLException ex) {
                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    public com.mysql.jdbc.Connection getCon() {
        return con;
    }

    public void setCon(com.mysql.jdbc.Connection con) {
        this.con = con;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }
    
    
}