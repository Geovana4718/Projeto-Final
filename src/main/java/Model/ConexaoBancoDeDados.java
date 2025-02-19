/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author devmat
 */
public class ConexaoBancoDeDados {
    private static final String URL =
             
            "jdbc:sqlserver://localhost:1433;databaseName=ProjetoFinal;integratedSecurity=true;trustServerCertificate=true";

    public static Connection getConnection() {
        // Criando um objeto do tipo Connection
        Connection connection = null;
        try {
            // Estabelecendo a conexão com o banco de dados
            connection = (Connection) DriverManager.getConnection(URL);
            System.out.println("Conexão bem sucedida!");
        } catch (SQLException e) {
            System.out.println("Erro de conexão: " + e.getMessage());
            e.printStackTrace();  // Exibe o stack trace para ajudar na depuração
        }
        return connection;
    }

    public static Connection getConection() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
