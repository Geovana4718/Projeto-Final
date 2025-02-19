
package Model;

/**
 *
 * @author devmat
 */

    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


public class Usuario {
    private int Id;
    private String nome;
    private String senha;

        public Usuario(int Id, String nome, String senha) {
            this.Id = Id;
            this.nome = nome;
            this.senha = senha;
        }

        public Usuario(String nome, String senha) {
            this.nome = nome;
            this.senha = senha;
        }

        public int getId() {
            return Id;
        }

        public String getNome() {
            return nome;
        }

        public String getSenha() {
            return senha;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public void setSenha(String senha) {
            this.senha = senha;
        }
    

        
    



    
}
