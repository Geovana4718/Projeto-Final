/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;


import Controller.LicoesController;
import Model.Licoes;
import Model.Usuario;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


import javax.swing.ImageIcon;
import javax.swing.JOptionPane;



public class TelaLicao extends javax.swing.JFrame {
    private LicoesController licoesController;
    private int indice = 1;  // Começa com a primeira lição
    private int categoria = 1;
    private String caminhoAudio;
    private Clip currentClip;  // Variável para armazenar o Clip atual

    
     Usuario usuario; 

    public TelaLicao(Usuario use) {
    initComponents();
    usuario = use;
    licoesController = new LicoesController();  // Inicializa o controller
    categoria = 1; // Inicializa com a categoria 1
    listandoLicoes(indice);  // Carrega a primeira lição
  // Carrega a primeira lição
  
    play.addActionListener(e -> tocarAudio());
}

     
     
    
   private void proximaLicao() {
    // Carrega as lições da categoria atual (aba)
    List<Licoes> listaLicoes = licoesController.carregarTodasLicoes(categoria);

    // Verifica se a lista de lições não está vazia
    if (listaLicoes == null || listaLicoes.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Não há lições disponíveis para esta categoria!", "Erro", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Incrementa o índice da lição
    indice++; 

    // Se o índice ultrapassar o número de lições disponíveis, reinicia para a primeira lição
    if (indice > listaLicoes.size()) {
        indice = 1;  // Retorna à primeira lição
    }

    // Atualiza as lições de acordo com o novo índice
    listandoLicoes(indice);
}



      
public void listandoLicoes(int posicao) {
    List<Licoes> listaLicoes = licoesController.carregarTodasLicoes(categoria);

    if (listaLicoes == null || listaLicoes.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Não há lições disponíveis para esta categoria!", "Erro", JOptionPane.ERROR_MESSAGE);
        return;
    }

    limparCampos(); // Limpar campos de texto

    if (posicao >= 1 && posicao <= listaLicoes.size()) {
        Licoes licao = listaLicoes.get(posicao - 1);  // Obtém a lição correspondente ao posicao

        // Preenche campos de texto com as palavras
        preencherCampos(categoria, licao);

        // Exibir a imagem
        exibirImagem(licao.getImagem());

        // Atualizar o caminho do áudio para a lição atual
        caminhoAudio = licao.getAudio();

        // Definir o caminho de áudio no botão de play
        play.setActionCommand(licao.getAudio());
          play1.setActionCommand(licao.getAudio());
            play2.setActionCommand(licao.getAudio());
              play3.setActionCommand(licao.getAudio());
    } else {
        JOptionPane.showMessageDialog(this, "Lição não encontrada para o índice especificado.", "Erro", JOptionPane.ERROR_MESSAGE);
    }
}


private void exibirImagem(String caminhoImagem) {
    if (caminhoImagem != null && !caminhoImagem.isEmpty()) {
        File imagemFile = new File(caminhoImagem);
        if (imagemFile.exists()) {
            // Carregar a imagem
            ImageIcon imagemIcon = new ImageIcon(caminhoImagem);
            Image imagem = imagemIcon.getImage();  // Obtém a imagem de forma bruta

            // Redimensiona a imagem para 300x300
            Image imagemRedimensionada = imagem.getScaledInstance(300, 300, Image.SCALE_SMOOTH);

            // Atualiza o JLabel com a imagem redimensionada
            imagemLicao.setIcon(new ImageIcon(imagemRedimensionada));
            imagemlicao2.setIcon(new ImageIcon(imagemRedimensionada));
            imagemlicao3.setIcon(new ImageIcon(imagemRedimensionada));
            imagemlicao4.setIcon(new ImageIcon(imagemRedimensionada));
            
        } else {
            JOptionPane.showMessageDialog(this, "Imagem não encontrada!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(this, "Caminho da imagem está vazio!", "Erro", JOptionPane.ERROR_MESSAGE);
    }
}

public void tocarAudio() {
    try {
        // Se houver um clip em execução, pare-o e feche-o
        if (currentClip != null && currentClip.isRunning()) {
            currentClip.stop();
            currentClip.close();
        }

        // Carrega o arquivo de áudio da lição atual
        File audioFile = new File(caminhoAudio);
        AudioInputStream audioInput = AudioSystem.getAudioInputStream(audioFile);
        
        // Cria um novo Clip para a lição atual
        currentClip = AudioSystem.getClip();
        currentClip.open(audioInput);

        // Toca o áudio da lição
        currentClip.loop(0);  // Não repete o áudio
        currentClip.start();

    } catch (Exception e) {
        e.printStackTrace();
    }
}


// Método para limpar os campos
private void limparCampos() {
    // Limpa todos os campos de texto
    ingles.setText("");
    portugues.setText("");
    inglestab2.setText("");
    portuguestab2.setText("");
    inglestab3.setText("");
    portuguestab3.setText("");
    inglestab4.setText("");
    portuguestab4.setText("");
}

// Método para preencher os campos de acordo com a aba e a lição
private void preencherCampos(int cat, Licoes licao) {
    switch (cat) {
        case 1:
            ingles.setText(licao.getPalavraEn());
            portugues.setText(licao.getPalavraPt());
            break;
        case 2:
            inglestab2.setText(licao.getPalavraEn());
            portuguestab2.setText(licao.getPalavraPt());
            break;
        case 3:
            inglestab3.setText(licao.getPalavraEn());
            portuguestab3.setText(licao.getPalavraPt());
            break;
        case 4:
            inglestab4.setText(licao.getPalavraEn());
            portuguestab4.setText(licao.getPalavraPt());
            break;
        default:
            // Caso contrário, não faz nada
            break;
    }
}


    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        ingles = new javax.swing.JTextField();
        portugues = new javax.swing.JTextField();
        btnProximo = new javax.swing.JButton();
        voltarTL = new javax.swing.JButton();
        play = new javax.swing.JButton();
        imagemLicao = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        inglestab2 = new javax.swing.JTextField();
        portuguestab2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        play1 = new javax.swing.JButton();
        imagemlicao2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        inglestab3 = new javax.swing.JTextField();
        portuguestab3 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        play2 = new javax.swing.JButton();
        imagemlicao3 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        inglestab4 = new javax.swing.JTextField();
        portuguestab4 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        play3 = new javax.swing.JButton();
        imagemlicao4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setPreferredSize(new java.awt.Dimension(700, 500));

        jPanel2.setBackground(new java.awt.Color(102, 0, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 42, Short.MAX_VALUE)
        );

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(153, 51, 0));
        jPanel3.setPreferredSize(new java.awt.Dimension(500, 400));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ingles.setEditable(false);
        ingles.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        ingles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inglesActionPerformed(evt);
            }
        });
        jPanel3.add(ingles, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 266, 268, -1));

        portugues.setEditable(false);
        portugues.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        jPanel3.add(portugues, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 340, 268, -1));

        btnProximo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnProximo.setText("next");
        btnProximo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProximoActionPerformed(evt);
            }
        });
        jPanel3.add(btnProximo, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 380, -1, -1));

        voltarTL.setText("VOLTAR");
        voltarTL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarTLActionPerformed(evt);
            }
        });
        jPanel3.add(voltarTL, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 6, -1, -1));

        play.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        play.setText("play");
        play.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playActionPerformed(evt);
            }
        });
        jPanel3.add(play, new org.netbeans.lib.awtextra.AbsoluteConstraints(286, 266, 86, -1));
        jPanel3.add(imagemLicao, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 330, 170));

        jTabbedPane1.addTab("Restaurantes", jPanel3);

        jPanel4.setBackground(new java.awt.Color(153, 51, 0));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        inglestab2.setEditable(false);
        inglestab2.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        inglestab2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inglestab2ActionPerformed(evt);
            }
        });
        jPanel4.add(inglestab2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 204, 269, -1));

        portuguestab2.setEditable(false);
        portuguestab2.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        jPanel4.add(portuguestab2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 294, 269, -1));

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setText("next");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(609, 370, -1, -1));

        play1.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        play1.setText("play");
        play1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                play1ActionPerformed(evt);
            }
        });
        jPanel4.add(play1, new org.netbeans.lib.awtextra.AbsoluteConstraints(287, 204, 86, -1));
        jPanel4.add(imagemlicao2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 320, 121));

        jTabbedPane1.addTab("Hospedagem", jPanel4);

        jPanel5.setBackground(new java.awt.Color(153, 51, 0));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        inglestab3.setEditable(false);
        inglestab3.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        jPanel5.add(inglestab3, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 190, 265, -1));

        portuguestab3.setEditable(false);
        portuguestab3.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        jPanel5.add(portuguestab3, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 292, 265, -1));

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setText("next");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(612, 369, -1, -1));

        play2.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        play2.setText("play");
        play2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                play2ActionPerformed(evt);
            }
        });
        jPanel5.add(play2, new org.netbeans.lib.awtextra.AbsoluteConstraints(288, 190, 86, -1));
        jPanel5.add(imagemlicao3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 171, 139));

        jTabbedPane1.addTab("Transporte ", jPanel5);

        jPanel7.setBackground(new java.awt.Color(153, 51, 0));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        inglestab4.setEditable(false);
        inglestab4.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        jPanel7.add(inglestab4, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 202, 260, -1));

        portuguestab4.setEditable(false);
        portuguestab4.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        jPanel7.add(portuguestab4, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 318, 260, -1));

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton3.setText("next");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(598, 365, -1, -1));

        play3.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        play3.setText("play");
        play3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                play3ActionPerformed(evt);
            }
        });
        jPanel7.add(play3, new org.netbeans.lib.awtextra.AbsoluteConstraints(284, 202, 86, -1));
        jPanel7.add(imagemlicao4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 191, 144));

        jTabbedPane1.addTab("Dinheiro", jPanel7);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 701, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        // TODO add your handling code here:
        // Verifica qual aba foi clicada e atualiza a categoria
        int abaSelecionada = jTabbedPane1.getSelectedIndex(); // Obtém o índice da aba selecionada

        // Atribui o valor da categoria conforme a aba clicada
        switch (abaSelecionada) {
            case 0:
            categoria = 1; // Aba 1 - Categoria 1
            break;
            case 1:
            categoria = 2; // Aba 2 - Categoria 2
            break;
            case 2:
            categoria = 3; // Aba 3 - Categoria 3
            break;
            case 3:
            categoria = 4; // Aba 4 - Categoria 4
            break;
            default:
            categoria = 1; // Valor padrão, caso não corresponda a nenhuma aba
            break;
        }

        indice = 1;

        listandoLicoes(indice);
        System.out.println("Categoria selecionada: " + categoria);

        // Chama o método para carregar as lições de acordo com a categoria

    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        proximaLicao();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void play2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_play2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_play2ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:

        proximaLicao();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void play1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_play1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_play1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        proximaLicao();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void inglestab2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inglestab2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inglestab2ActionPerformed

    private void playActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_playActionPerformed

    private void voltarTLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarTLActionPerformed
        TelaHome telahome = new TelaHome(this.usuario);
        telahome.setVisible(true);
        dispose();

    }//GEN-LAST:event_voltarTLActionPerformed

    private void btnProximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProximoActionPerformed
        // TODO add your handling code here:
        proximaLicao();

    }//GEN-LAST:event_btnProximoActionPerformed

    private void inglesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inglesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inglesActionPerformed

    private void play3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_play3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_play3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaLicao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaLicao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaLicao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaLicao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Usuario use =  null;
                new TelaLicao(use).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnProximo;
    private javax.swing.JLabel imagemLicao;
    private javax.swing.JLabel imagemlicao2;
    private javax.swing.JLabel imagemlicao3;
    private javax.swing.JLabel imagemlicao4;
    private javax.swing.JTextField ingles;
    private javax.swing.JTextField inglestab2;
    private javax.swing.JTextField inglestab3;
    private javax.swing.JTextField inglestab4;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton play;
    private javax.swing.JButton play1;
    private javax.swing.JButton play2;
    private javax.swing.JButton play3;
    private javax.swing.JTextField portugues;
    private javax.swing.JTextField portuguestab2;
    private javax.swing.JTextField portuguestab3;
    private javax.swing.JTextField portuguestab4;
    private javax.swing.JButton voltarTL;
    // End of variables declaration//GEN-END:variables
}
