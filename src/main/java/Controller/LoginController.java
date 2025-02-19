/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;


import Model.ConexaoBancoDeDados;
import Model.Usuario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author devmat
 */

public class LoginController {

    public Usuario authenticate(String nome, String senha) {
       
        
   
        String query = "SELECT * FROM Usuarios WHERE nome = ? AND senha = ?";
        
        try (Connection connection =  ConexaoBancoDeDados.getConnection();// conexão com banco de dados
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {// mandar o comando select para ser executado no BD
            
            // substitui a ? pela variavel que o usuario informou
            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, senha);
            
            // resultset recebe o retorno do comando sql 
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if(resultSet.next()){
                
                // captura do banco de dados os dados do usuário
                int id = resultSet.getInt("id");
                String nomeusuario = resultSet.getString("nome");
                String senhausuario = resultSet.getString("senha");
               
              
                // mandando um usuário para a tela de login
                return new Usuario ( id, nomeusuario, senhausuario);
                }// fim do if
                
            }
        } catch (SQLException e) {
            System.err.println("Erro ao autenticar o usuário: " + e.getMessage());
            return null; // retorna falso caso não ache o usuário
        }
        return null;
    }
}

    
