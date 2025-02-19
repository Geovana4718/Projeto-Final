/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.projeto_final;

import static Model.ConexaoBancoDeDados.getConnection;
import View.TelaLogin;
import java.sql.Connection;

/**
 *
 * @author devmat
 */
public class Projeto_Final {

    public static void main(String[] args) {
       TelaLogin telalogin = new TelaLogin();
       //chamando o objeto e deixando a tela visível 
       telalogin.setVisible(true);
       
       
       
       Connection conexao =(Connection) getConnection();
       if(conexao!=null){
           System.out.println("Conexão deu certo!");
           
       }
    }
}

