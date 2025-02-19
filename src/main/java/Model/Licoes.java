/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author devmat
 */
public class Licoes {
  
    private int id;
    private String palavraPt;
    private String palavraEn;
    private String imagem;  // Usando byte[] para armazenar a imagem
    private String audio;  // Usando byte[] para armazenar o áudio

    public Licoes() {
    }

    public Licoes(int id, String palavraPt, String palavraEn, String imagem, String audio) {
        this.id = id;
        this.palavraPt = palavraPt;
        this.palavraEn = palavraEn;
        this.imagem = imagem;
        this.audio = audio;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPalavraPt() {
        return palavraPt;
    }

    public void setPalavraPt(String palavraPt) {
        this.palavraPt = palavraPt;
    }

    public String getPalavraEn() {
        return palavraEn;
    }

    public void setPalavraEn(String palavraEn) {
        this.palavraEn = palavraEn;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

   

   
   
   
    
    
}
