package Controller;

import Model.ConexaoBancoDeDados;
import Model.Licoes;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LicoesController {

    // Método para carregar todas as lições
    public List<Licoes> carregarTodasLicoes(int cat) {
        List<Licoes> listaLicoes = new ArrayList<>();
        String query = "SELECT id, PalavraPt, PalavraEn, Imagem, Audio FROM Licoes WHERE CategoriasID=?";

        try (Connection connection = ConexaoBancoDeDados.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, cat);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Licoes licao = new Licoes();
                licao.setId(resultSet.getInt("id"));
                licao.setPalavraPt(resultSet.getString("PalavraPt"));
                licao.setPalavraEn(resultSet.getString("PalavraEn"));
                
                // Salvar imagem temporária
                File imagemTemp = salvarArquivoTemporario(resultSet.getBinaryStream("Imagem"), "imagem_" + licao.getId() + ".jpg");
                File audioTemp = salvarArquivoTemporario(resultSet.getBinaryStream("Audio"), "audio_" + licao.getId() + ".mp3");
                
                if (imagemTemp != null) {
                    licao.setImagem(imagemTemp.getAbsolutePath());
                }
                if (audioTemp != null) {
                    licao.setAudio(audioTemp.getAbsolutePath());
                }
                
                listaLicoes.add(licao);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao carregar lições: " + e.getMessage());
            e.printStackTrace();
        }
        return listaLicoes;
    }

    // Método para salvar blob como arquivo temporário
    private File salvarArquivoTemporario(InputStream inputStream, String nomeArquivo) {
        if (inputStream == null) {
            return null;
        }
        try {
            // Cria arquivo temporário
            File arquivoTemp = File.createTempFile(nomeArquivo, null);
            arquivoTemp.deleteOnExit(); // Garante que o arquivo seja excluído quando o programa terminar

            try (FileOutputStream outputStream = new FileOutputStream(arquivoTemp)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            } catch (Exception e) {
                System.err.println("Erro ao gravar arquivo temporário: " + e.getMessage());
                e.printStackTrace();
            }
            return arquivoTemp;
        } catch (Exception e) {
            System.err.println("Erro ao criar arquivo temporário: " + e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            try {
                inputStream.close();
            } catch (Exception e) {
                System.err.println("Erro ao fechar InputStream: " + e.getMessage());
            }
        }
    }
}
